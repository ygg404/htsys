package io.renren.modules.set.controller;

import java.util.Arrays;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.renren.modules.set.entity.WorkProjectTypeEntity;
import io.renren.modules.set.service.WorkProjectTypeService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * 作业类型ID项目类型ID关联表
 *
 * @author ygg
 * @date 2019-10-31 16:56:32
 */
@RestController
@RequestMapping("set/workprojecttype")
public class WorkProjectTypeController {
    @Autowired
    private WorkProjectTypeService workProjectTypeService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("set:workprojecttype:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = workProjectTypeService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("set:workprojecttype:info")
    public R info(@PathVariable("id") Integer id){
		WorkProjectTypeEntity workProjectType = workProjectTypeService.selectById(id);

        return R.ok().put("workProjectType", workProjectType);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("set:workprojecttype:save")
    public R save(@RequestBody WorkProjectTypeEntity workProjectType){
		workProjectTypeService.save(workProjectType);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("set:workprojecttype:update")
    public R update(@RequestBody WorkProjectTypeEntity workProjectType){
		workProjectTypeService.updateById(workProjectType);

        return R.ok();
    }

    /**
     * 批量删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("set:workprojecttype:delete")
    public R delete(@RequestBody Long[] ids){
		workProjectTypeService.deleteBatch(ids);

        return R.ok();
    }




}
