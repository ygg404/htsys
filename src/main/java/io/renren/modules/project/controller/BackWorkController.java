package io.renren.modules.project.controller;


import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.google.gson.Gson;
import io.renren.common.annotation.SysLog;
import io.renren.common.utils.MapUtils;
import io.renren.modules.project.entity.BackWorkEntity;
import io.renren.modules.project.entity.CheckQualityEntity;
import io.renren.modules.project.entity.ProjectSituationEntity;
import io.renren.modules.project.entity.ProjectWorkEntity;
import io.renren.modules.project.service.BackWorkService;
import io.renren.modules.project.service.CheckQualityService;
import io.renren.modules.project.service.ProjectSituationService;
import io.renren.modules.project.service.ProjectWorkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;

import javax.servlet.http.HttpServletResponse;


/**
 * 返修表
 *
 * @author ygg
 * @date 2019-11-28 11:52:47
 */
@RestController
@RequestMapping("project/backwork")
public class BackWorkController {
    @Autowired
    private BackWorkService backWorkService;
    @Autowired
    private ProjectSituationService projectSituationService;
    @Autowired
    private CheckQualityService checkQualityService;
    @Autowired
    private ProjectWorkService projectWorkService;

    /**
     * 列表
     */
    @RequestMapping("/list/{projectNo}")
//    @RequiresPermissions("project:backwork:list")
    public R list(HttpServletResponse response, @PathVariable("projectNo") String projectNo){
        List<BackWorkEntity> list = backWorkService.queryByProjectNo(projectNo);

        return R.ok().put("list", list);
    }


    /**
     * 信息
     */
    @RequestMapping("/info")
//    @RequiresPermissions("project:backwork:info")
    public R info(@RequestParam("id") Long id){
		BackWorkEntity backwork = backWorkService.selectById(id);

        return R.ok().put("backwork", backwork);
    }

    /**
     * 保存
     */
    @SysLog("返修")
    @RequestMapping("/save")
//    @RequiresPermissions("project:backwork:save")
    public R save(@RequestBody BackWorkEntity backwork){
        backwork.setbackCreateTime(new Date());
        backWorkService.save(backwork);

        //把项目工作情况设置为返修状态
        ProjectSituationEntity sitEntity = projectSituationService.queryByProjectNo(backwork.getprojectNo());
        sitEntity.setisWork(2L);
        sitEntity.setisCheck(2L);
        projectSituationService.update(sitEntity);

        //更新质检表里面的质检报告
        if( backwork.getprojectNo() == null || backwork.getprojectNo() == ""){
            return  R.error("项目编号为空");
        }
        CheckQualityEntity entity = checkQualityService.selectOne(
                new EntityWrapper<CheckQualityEntity>().eq("project_no", backwork.getprojectNo())
        );
        if(entity == null) {
            entity = new CheckQualityEntity();
            entity.setprojectNo(backwork.getprojectNo());
        }
        entity.setqualityReport(backwork.getbackNote());
        checkQualityService.insertOrUpdate(entity);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
//    @RequiresPermissions("project:backwork:update")
    public R update(@RequestBody BackWorkEntity backwork){
        BackWorkEntity backEntity = backWorkService.selectById(backwork.getid());
        if(backEntity == null){
            return R.error("该项目的返修记录不存在，请刷新后重试！");
        }
        // 根据项目编号获取返修列表 并获取最大的返修天数
        List<BackWorkEntity> list = backWorkService.queryByProjectNo(backEntity.getprojectNo());
        Long daysMax = 0L;
        for(BackWorkEntity bEntity : list){
            if(bEntity.getbackDateNum() != null && bEntity.getbackDateNum() > daysMax) {
                daysMax = bEntity.getbackDateNum();
            }
        }

        // 更新质检报告
        CheckQualityEntity qualityEntity = checkQualityService.queryByMap(new MapUtils().put("projectNo",backEntity.getprojectNo()));
        qualityEntity.setqualityReport(backwork.getsubmitNote());
        checkQualityService.update(qualityEntity);

        // 计算返修天数
        Long days = (Long) ((new Date().getTime() - backEntity.getbackCreateTime().getTime()) / (1000*3600*24)) + daysMax;
        backEntity.setbackDateNum(days);
        backEntity.setsubmitNote("已修改！");
        if(backwork.getsubmitNote() != null) backEntity.setsubmitCreateTime(new Date());
		backWorkService.updateById(backEntity);


        ProjectWorkEntity workEntity = projectWorkService.queryByProjectNo(backEntity.getprojectNo());
		//返修提交内容后 根据有作业完成时间标记已经作业完成
        ProjectSituationEntity situationEntity = projectSituationService.queryByProjectNo(backwork.getprojectNo());
        if(workEntity.getfinishDateTime() == null){
            situationEntity.setisWork(0L);
        } else {
            situationEntity.setisWork(1L);
        }

        situationEntity.setisCheck(3L);
        situationEntity.setprojectNo(backwork.getprojectNo());
        projectSituationService.update(situationEntity);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
//    @RequiresPermissions("project:backwork:delete")
    public R delete(@RequestBody String projectNo){
        List<BackWorkEntity> backList = backWorkService.queryByProjectNo(projectNo);
        for(BackWorkEntity backEntity : backList) {
            if(backEntity.getsubmitNote() == null) {
                backWorkService.deleteById(backEntity.getid());
            }
        }
        ProjectWorkEntity workEntity = projectWorkService.queryByProjectNo(projectNo);
        //返修删除记录后 质检标志为未完成
        ProjectSituationEntity situationEntity = projectSituationService.queryByProjectNo(projectNo);
        situationEntity.setisCheck(0L);
        // 返修是作业有完成时间标志为已完成
        if(workEntity.getfinishDateTime() == null) {
            situationEntity.setisWork(0L);
        } else{
            situationEntity.setisWork(1L);
        }
        situationEntity.setprojectNo(projectNo);
        projectSituationService.update(situationEntity);

        // 删除后
        return R.ok();
    }

}
