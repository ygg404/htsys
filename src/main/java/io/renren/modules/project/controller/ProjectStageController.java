package io.renren.modules.project.controller;

import java.util.Arrays;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.renren.modules.project.entity.ProjectStageEntity;
import io.renren.modules.project.service.ProjectStageService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * 项目阶段表
 *
 * @author ygg
 * @date 2019-11-01 16:46:10
 */
@RestController
@RequestMapping("project/projectstage")
public class ProjectStageController {
    @Autowired
    private ProjectStageService projectStageService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("project:projectstage:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = projectStageService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("project:projectstage:info")
    public R info(@PathVariable("id") Integer id){
		ProjectStageEntity projectStage = projectStageService.selectById(id);

        return R.ok().put("projectStage", projectStage);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("project:projectstage:save")
    public R save(@RequestBody ProjectStageEntity projectStage){
		projectStageService.save(projectStage);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("project:projectstage:update")
    public R update(@RequestBody ProjectStageEntity projectStage){
		projectStageService.updateById(projectStage);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("project:projectstage:delete")
    public R delete(@RequestBody Long[] ids){
		projectStageService.deleteBatch(ids);

        return R.ok();
    }

}
