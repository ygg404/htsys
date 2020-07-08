package io.renren.modules.project.controller;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import io.renren.common.annotation.SysLog;
import io.renren.modules.project.entity.ProjectSituationEntity;
import io.renren.modules.project.entity.ProjectWorkEntity;
import io.renren.modules.project.service.ProjectSituationService;
import io.renren.modules.project.service.ProjectWorkService;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.renren.modules.project.entity.ProjectPlanEntity;
import io.renren.modules.project.service.ProjectPlanService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * 项目安排表
 *
 * @author ygg
 * @date 2019-11-01 16:46:10
 */
@RestController
@RequestMapping("project/plan")
public class ProjectPlanController {
    @Autowired
    private ProjectPlanService projectPlanService;
    @Autowired
    private ProjectWorkService projectWorkService;
    @Autowired
    private ProjectSituationService projectSituationService;

    /**
     * 列表
     */
//    @SysLog("查看工作安排列表")
    @RequestMapping("/list")
//    @RequiresPermissions("project:plan:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = projectPlanService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
//    @SysLog("查看工作安排")
    @RequestMapping("/info/{projectNo}")
//    @RequiresPermissions("project:plan:info")
    public R info(@PathVariable("projectNo") String projectNo){
		ProjectPlanEntity projectPlan = projectPlanService.queryByProjectNo(projectNo);

        return R.ok().put("projectPlan", projectPlan);
    }

    /**
     * 保存
     */
    @SysLog("保存工作安排列表")
    @RequestMapping("/save")
//    @RequiresPermissions("project:plan:save")
    public R save(@RequestBody ProjectPlanEntity projectPlan){
        if( !StringUtils.isNotBlank(projectPlan.getprojectNo()) ){
            return R.error("项目编号无效");
        }
        ProjectPlanEntity pEntity = projectPlanService.selectOne(
                new EntityWrapper<ProjectPlanEntity>().eq("project_no", projectPlan.getprojectNo())
        );
        if(pEntity != null)projectPlan.setId(pEntity.getId());
		projectPlanService.insertOrUpdate(projectPlan);
        // 保存后 把项目状态设置为启动
        ProjectWorkEntity workEntity = projectWorkService.selectOne(
                new EntityWrapper<ProjectWorkEntity>().eq("project_no", projectPlan.getprojectNo())
        );
        if( workEntity == null ) {
            workEntity = new ProjectWorkEntity();
            workEntity.setprojectNo(projectPlan.getprojectNo());
        }
        workEntity.setprojectStatus(0);
        workEntity.setprojectNo(projectPlan.getprojectNo());
        // 求出计划完成项目时间
        Calendar c = Calendar.getInstance();
        c.setTime(projectPlan.getprojectBegunDateTime());
        c.add(Calendar.DAY_OF_MONTH, (int)Math.ceil(projectPlan.getprojectWorkDate()));
        workEntity.setplanWorkDate( c.getTime() );
		projectWorkService.insertOrUpdate(workEntity);

        //保存安排后 设置项目情况已经安排
        ProjectSituationEntity situationEntity = projectSituationService.queryByProjectNo(projectPlan.getprojectNo());
        situationEntity.setisPlan(1L);
        projectSituationService.insertOrUpdate(situationEntity);
        return R.ok();
    }

    /**
     * 临时保存（保存不设置项目状态）
     */
    @SysLog("保存工作安排（临时）")
    @RequestMapping("/saveTemp")
    public R saveTemp(@RequestBody ProjectPlanEntity projectPlan){
        if( !StringUtils.isNotBlank(projectPlan.getprojectNo()) ){
            return R.error("项目编号无效");
        }
        ProjectPlanEntity pEntity = projectPlanService.selectOne(
                new EntityWrapper<ProjectPlanEntity>().eq("project_no", projectPlan.getprojectNo())
        );
        if(pEntity != null)projectPlan.setId(pEntity.getId());
        projectPlanService.insertOrUpdate(projectPlan);
        return R.ok();
    }

    /**
     * 删除
     */
    @SysLog("删除工作安排")
    @RequestMapping("/delete")
//    @RequiresPermissions("project:plan:delete")
    public R delete(@RequestBody Long[] ids){
		projectPlanService.deleteBatch(ids);

        return R.ok();
    }

}
