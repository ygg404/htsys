package io.renren.modules.project.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import io.renren.common.annotation.SysLog;
import io.renren.common.utils.MapUtils;
import io.renren.modules.project.entity.ProjectPlanEntity;
import io.renren.modules.project.service.ProjectPlanService;
import io.renren.modules.project.vo.GroupRequestVoEntity;
import io.renren.modules.project.vo.ProjectGroupVoEntity;
import io.renren.modules.project.vo.WorkGroupVoEntity;
import io.renren.modules.sys.entity.SysUserEntity;
import io.renren.modules.sys.service.SysUserService;
import io.renren.modules.sys.vo.UserVoEntity;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.renren.modules.project.entity.ProjectGroupEntity;
import io.renren.modules.project.service.ProjectGroupService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * 项目安排分组
 *
 * @author ygg
 * @date 2019-11-01 16:46:10
 */
@RestController
@RequestMapping("project/group")
public class ProjectGroupController {
    @Autowired
    private ProjectGroupService projectGroupService;

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private ProjectPlanService projectPlanService;

    /**
     * 列表
     */
//    @SysLog("查看工作组")
    @RequestMapping("/list")
//    @RequiresPermissions("project:group:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = projectGroupService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
//    @SysLog("查看工作组信息")
    @RequestMapping("/info/{id}")
//    @RequiresPermissions("project:group:info")
    public R info(@PathVariable("id") Integer id){
		ProjectGroupEntity projectGroup = projectGroupService.selectById(id);

        return R.ok().put("projectGroup", projectGroup);
    }

    /**
     * 获取项目负责人列表
     * @return
     */
    @RequestMapping("/getChargeList")
    public R getBusinessList(){
        // 角色Id 为4是 项目负责人
        List<UserVoEntity> chargeList = sysUserService.getUserList(4L);
        return R.ok().put("list", chargeList);
    }

    /**
     * 根据项目编号获取信息
     */
//    @SysLog("获取项目分组信息")
    @RequestMapping("/getListByProjectNo")
//    @RequiresPermissions("project:group:info")
    public R getListByProjectNo(@RequestParam Map<String, Object> params){
        List<ProjectGroupVoEntity> list = projectGroupService.getListByProjectNo(params);

        return R.ok().put("list", list);
    }

    /**
     * 获取项目安排时 项目进度和产值预览
     */
    @RequestMapping("/getProjectDataCoe")
//    @RequiresPermissions("project:group:info")
    public R getProjectDataCoe(Map<String, Object> params){
        List<WorkGroupVoEntity> list = projectGroupService.getProjectDataCoe(params);


        return R.ok().put("list", list);
    }

    /**
     * 保存
     */
    @SysLog("保存工作组信息")
    @RequestMapping("/save")
    @RequiresPermissions("project:group:save")
    public R save(@RequestBody ProjectGroupEntity projectGroup){
		projectGroupService.save(projectGroup);

        return R.ok();
    }
    /**
     * 保存
     */
    @SysLog("保存工作组信息")
    @RequestMapping("/saveList")
    public R saveList(@RequestBody GroupRequestVoEntity pgroupList) {
        if( pgroupList.getProjectNo() == null || pgroupList.getProjectNo() == ""){
            return  R.error("项目编号为空");
        }
        projectGroupService.deleteByMap(new MapUtils().put("project_no",pgroupList.getProjectNo()));
        for(ProjectGroupVoEntity entityvo : pgroupList.getPgroupList()){
            if(entityvo.getchecked()){
                ProjectGroupEntity entity = new ProjectGroupEntity();
                entity.setgroupId(entityvo.getgroupId());
                entity.setprojectNo(pgroupList.getProjectNo());
                entity.setoutputRate(entityvo.getoutputRate());
                entity.setprojectOutput(entityvo.getprojectOutput());
                entity.setshortDateTime(entityvo.getshortDateTime());
                entity.setlastDateTime(entityvo.getlastDateTime());
                projectGroupService.save(entity);
            }
        }
        //保存项目负责人
        SysUserEntity userEntity = sysUserService.selectById(pgroupList.getHeadId());
        ProjectPlanEntity planEntity = projectPlanService.queryByProjectNo(pgroupList.getProjectNo());
        if(planEntity == null) planEntity = new ProjectPlanEntity();
        planEntity.setprojectCharge(userEntity.getUsername());
        planEntity.setprojectChargeAccount(userEntity.getUseraccount());
        planEntity.setprojectNo(pgroupList.getProjectNo());
        projectPlanService.insertOrUpdate(planEntity);
        return R.ok();
    }
    /**
     * 修改
     */
    @SysLog("修改工作组信息")
    @RequestMapping("/update")
    @RequiresPermissions("project:group:update")
    public R update(@RequestBody ProjectGroupEntity projectGroup){
		projectGroupService.updateById(projectGroup);

        return R.ok();
    }

    /**
     * 删除
     */
    @SysLog("删除工作组信息")
    @RequestMapping("/delete")
    @RequiresPermissions("project:group:delete")
    public R delete(@RequestBody Long[] ids){
		projectGroupService.deleteBatch(ids);

        return R.ok();
    }

}
