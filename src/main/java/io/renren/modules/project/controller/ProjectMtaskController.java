package io.renren.modules.project.controller;

import java.util.Arrays;
import java.util.Map;
import java.util.List;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import io.renren.common.annotation.SysLog;
import io.renren.modules.project.vo.ProjectInfoVoEntity;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.renren.modules.project.entity.ProjectMtaskEntity;
import io.renren.modules.project.service.ProjectMtaskService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * 作业任务分工表
 *
 * @author ygg
 * @date 2020-12-29 15:41:51
 */
@RestController
@RequestMapping("project/mtask")
public class ProjectMtaskController {
    @Autowired
    private ProjectMtaskService projectMtaskService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        List<ProjectMtaskEntity> list = projectMtaskService.queryList(params);

        return R.ok().put("list", list);
    }

    /**
     * 信息
     */
    @RequestMapping("/info")
    public R info(@RequestParam Map<String, Object> params){
		ProjectMtaskEntity projectMtask = projectMtaskService.queryByNo(params);

        return R.ok().put("projectMtask", projectMtask);
    }

    /**
     * 保存
     */
    @SysLog("保存岗位职责表")
    @RequestMapping("/save")
    public R save(@RequestBody ProjectMtaskEntity projectMtask){
		projectMtaskService.save(projectMtask);

        return R.ok();
    }

    /**
     * 修改
     */
    @SysLog("修改岗位职责表")
    @RequestMapping("/update")
    public R update(@RequestBody ProjectMtaskEntity projectMtask){
		projectMtaskService.updateById(projectMtask);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("project:mtask:delete")
    public R delete(@RequestBody Long[] ids){
		projectMtaskService.deleteBatch(ids);

        return R.ok();
    }

}
