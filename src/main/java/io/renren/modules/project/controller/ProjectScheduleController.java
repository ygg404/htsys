package io.renren.modules.project.controller;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import io.renren.common.annotation.SysLog;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.renren.modules.project.entity.ProjectScheduleEntity;
import io.renren.modules.project.service.ProjectScheduleService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * 进度表
 *
 * @author ygg
 * @date 2019-11-01 16:46:10
 */
@RestController
@RequestMapping("project/schedule")
public class ProjectScheduleController {
    @Autowired
    private ProjectScheduleService projectScheduleService;

    /**
     * 列表
     */
//    @SysLog("查看进度列表")
    @RequestMapping("/list")
//    @RequiresPermissions("project:schedule:list")
    public R list(@RequestParam Map<String, Object> params){
        List<ProjectScheduleEntity> list = projectScheduleService.queryList(params);

        return R.ok().put("list", list);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("project:schedule:info")
    public R info(@PathVariable("id") Integer id){
		ProjectScheduleEntity projectSchedule = projectScheduleService.selectById(id);

        return R.ok().put("projectSchedule", projectSchedule);
    }

    /**
     * 保存
     */
    @SysLog("提交进度")
    @RequestMapping("/save")
    @RequiresPermissions("project:schedule:save")
    public R save(@RequestBody ProjectScheduleEntity projectSchedule){
        projectSchedule.setcreateTime(new Date());
		projectScheduleService.save(projectSchedule);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("project:schedule:update")
    public R update(@RequestBody ProjectScheduleEntity projectSchedule){
		projectScheduleService.updateById(projectSchedule);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("project:schedule:delete")
    public R delete(@RequestBody Long[] ids){
		projectScheduleService.deleteBatch(ids);

        return R.ok();
    }

}
