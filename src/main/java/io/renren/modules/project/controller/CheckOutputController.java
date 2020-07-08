package io.renren.modules.project.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import io.renren.common.annotation.SysLog;
import io.renren.common.utils.MapUtils;
import io.renren.modules.project.entity.*;
import io.renren.modules.project.service.*;
import io.renren.modules.project.vo.CheckOutputVoEntity;
import io.renren.modules.project.vo.GroupRequestVoEntity;
import io.renren.modules.project.vo.ProjectGroupVoEntity;
import io.renren.modules.set.entity.WorkTypeEntity;
import io.renren.modules.set.service.WorkTypeService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * 产值核算表
 *
 * @author ygg
 * @date 2019-11-18 15:04:00
 */
@RestController
@RequestMapping("project/checkoutput")
public class CheckOutputController {
    @Autowired
    private CheckOutputService checkOutputService;
    @Autowired
    private CheckOutputTempService checkOutputTempService;
    @Autowired
    private ProjectGroupService projectGroupService;
    @Autowired
    private ProjectSituationService projectSituationService;
    @Autowired
    private ProjectPlanService projectPlanService;
    @Autowired
    private WorkTypeService workTypeService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("project:checkoutput:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = checkOutputService.queryPage(params);

        return R.ok().put("page", page);
    }

    /**
     * 工作组和产值核算列表
     */
    @RequestMapping("/getOutPutGroup")
    public R getOutPutGroup(@RequestParam Map<String, Object> params){
        String projectNo = (String)params.get("projectNo");
        List<ProjectGroupVoEntity> volist = projectGroupService.getListByProjectNo(params);
        // 获取每个工作组对应的产值核算表
        boolean isChecked = false;
        for(ProjectGroupVoEntity projectGroupVoEntity : volist){
            if(projectGroupVoEntity.getchecked()){
                List<CheckOutputVoEntity> checkOutputVoEntityList = new ArrayList<>();
                List<CheckOutputEntity> checkOutputList =  checkOutputService.selectList(new EntityWrapper<CheckOutputEntity>().eq("project_no", projectNo).eq("group_id",projectGroupVoEntity.getgroupId()));
                List<WorkTypeEntity> workTypeList = workTypeService.queryList();
                for(WorkTypeEntity wtEntity : workTypeList){
                    CheckOutputVoEntity vo = new CheckOutputVoEntity();
                    vo.setChecked(false);
                    vo.settypeId(wtEntity.getId());
                    vo.setTypeName(wtEntity.getTypeName());
                    vo.setUnit(wtEntity.getUnit());
                    vo.setUnitOutput(wtEntity.getUnitOutput());
                    for(CheckOutputEntity entity : checkOutputList){
                        if(entity.gettypeId() == wtEntity.getId()){
                            isChecked = true;
                            vo.setChecked(true);
                            vo.setprojectRatio(entity.getprojectRatio());
                            vo.setworkLoad(entity.getworkLoad());
                            vo.setgroupId(entity.getgroupId());
                        }
                    }
                    // 工作组对应的工作产值
                    checkOutputVoEntityList.add(vo);
                }
                ProjectGroupEntity groupEntity = projectGroupService.selectOne(
                        new EntityWrapper<ProjectGroupEntity>().eq("project_no",projectNo).eq("group_id",projectGroupVoEntity.getgroupId())
                );

                projectGroupVoEntity.setprojectActuallyOutput(groupEntity.getprojectActuallyOutput() != null?groupEntity.getprojectActuallyOutput():0f);
                projectGroupVoEntity.setcheckOutputList(checkOutputVoEntityList);
            }
        }
        // 如果当前项目没有核算但是预先有产值明细预计，则把作业类型勾选
        if(!isChecked){
            for(ProjectGroupVoEntity projectGroupVoEntity : volist) {
                if (projectGroupVoEntity.getchecked()) {
                    List<CheckOutputTempEntity> checkOutputTempList = checkOutputTempService.queryList(new MapUtils().put("projectNo", projectNo));
                    for (CheckOutputTempEntity tempEntity : checkOutputTempList) {
                        for (CheckOutputVoEntity entity : projectGroupVoEntity.getCheckOutputVoList()) {
                            if (entity.gettypeId() == tempEntity.gettypeId()) {
                                entity.setChecked(true);
                            }
                        }
                    }
                }
            }
        }

        return R.ok().put("list", volist);
    }

    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("project:checkoutput:info")
    public R info(@PathVariable("id") Long id){
		CheckOutputEntity checkOutput = checkOutputService.selectById(id);

        return R.ok().put("checkOutput", checkOutput);
    }

    /**
     * 保存各组产值核算
     */
    @SysLog("保存核算产值")
    @RequestMapping("/save")
    @RequiresPermissions("project:checkoutput:save")
    public R save(@RequestBody GroupRequestVoEntity groupRequestVoEntity){
        //保存前 先根据项目编号 删除产值明细
        Map<String, Object> params = new HashMap<>();
        params.put("project_no", groupRequestVoEntity.getProjectNo());
        checkOutputService.deleteByMap(params);

        for(ProjectGroupVoEntity pgvoEntity : groupRequestVoEntity.getPgroupList()) {
            // 项目安排时 组队有被勾选
            if(pgvoEntity.getchecked()) {
                ProjectGroupEntity projectGroupEntity = projectGroupService.selectOne(
                        new EntityWrapper<ProjectGroupEntity>()
                                .eq("project_no", groupRequestVoEntity.getProjectNo())
                                .eq("group_id", pgvoEntity.getgroupId())
                );
                projectGroupEntity.setprojectActuallyOutput(pgvoEntity.getprojectActuallyOutput());
                //保存各组 实际总产值
                projectGroupService.update(projectGroupEntity);
                //保存产值明细
                List<CheckOutputEntity> outputList = new ArrayList<>();
                for (CheckOutputVoEntity checkOutputVoEntity : pgvoEntity.getCheckOutputVoList()) {
                    // 工作类型 有被勾选
                    if( checkOutputVoEntity.getChecked()){
                        CheckOutputEntity entity = new CheckOutputEntity();
                        entity.setgroupId(pgvoEntity.getgroupId());
                        entity.setprojectNo(groupRequestVoEntity.getProjectNo());
                        entity.setprojectRatio(checkOutputVoEntity.getprojectRatio());
                        entity.setworkLoad(checkOutputVoEntity.getworkLoad());
                        entity.settypeId(checkOutputVoEntity.gettypeId());
                        outputList.add(entity);
                    }
                }
                if(outputList.size() != 0) checkOutputService.insertBatch(outputList);
            }
        }
        // 保留所有组的总实际产值
        ProjectPlanEntity plan = projectPlanService.queryByProjectNo(groupRequestVoEntity.getProjectNo());
        plan.setprojectActuallyOutput(groupRequestVoEntity.getProjectActuallyOutput());
        projectPlanService.update(plan);

        //保存核算后 设置项目情况已经核算
        ProjectSituationEntity situationEntity = projectSituationService.queryByProjectNo(groupRequestVoEntity.getProjectNo());
        situationEntity.setisOutput(1L);
        projectSituationService.insertOrUpdate(situationEntity);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("project:checkoutput:update")
    public R update(@RequestBody CheckOutputEntity checkOutput){
		checkOutputService.updateById(checkOutput);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("project:checkoutput:delete")
    public R delete(@RequestBody Long[] ids){
		checkOutputService.deleteBatch(ids);

        return R.ok();
    }

}
