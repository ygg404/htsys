package io.renren.modules.set.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import io.renren.common.annotation.SysLog;
import org.apache.ibatis.annotations.Param;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.renren.modules.set.entity.ProjectTypeEntity;
import io.renren.modules.set.service.ProjectTypeService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * 项目类型表
 *
 * @author ygg
 * @date 2019-10-29 10:39:04
 */
@RestController
@RequestMapping("set/projecttype")
public class ProjectTypeController {
    @Autowired
    private ProjectTypeService projectTypeService;

    /**
     * 列表(分页)
     */
//    @SysLog("输出项目类型列表")
    @RequestMapping("/page")
    @RequiresPermissions("set:projecttype:list")
    public R page(@RequestParam Map<String, Object> params){
        PageUtils page = projectTypeService.queryPage(params);

        return R.ok().put("page", page);
    }

    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("set:projecttype:info")
    public R info(@PathVariable("id") Integer id){
        ProjectTypeEntity projectType = projectTypeService.selectById(id);

        return R.ok().put("projectType", projectType);
    }

    /**
     * 根据项目类型 获取 信息
     */
    @RequestMapping("/getByName")
    public R getByName(@RequestParam("typeName") String typeName){
        ProjectTypeEntity projectType = projectTypeService.getByName(typeName);

        return R.ok().put("projectType", projectType);
    }

    /**
     * 获取项目类型列表（全部）
     */
    @RequestMapping("/getProjectTypelist")
    public R getProjectTypelist(){
        List<ProjectTypeEntity> list = projectTypeService.selectList(new EntityWrapper<ProjectTypeEntity>());

        return R.ok().put("list", list);
    }

    /**
     * 保存
     */
    @SysLog("保存项目类型")
    @RequestMapping("/save")
    @RequiresPermissions("set:projecttype:save")
    public R save(@RequestBody ProjectTypeEntity projectType){
		projectTypeService.save(projectType);

        return R.ok();
    }

    /**
     * 修改
     */
    @SysLog("修改项目类型")
    @RequestMapping("/update")
    @RequiresPermissions("set:projecttype:update")
    public R update(@RequestBody ProjectTypeEntity projectType){
		projectTypeService.updateById(projectType);

        return R.ok();
    }

    /**
     * 删除
     */
    @SysLog("删除项目类型")
    @RequestMapping("/delete")
    @RequiresPermissions("set:projecttype:delete")
    public R delete(@RequestBody Long[] ids){
		projectTypeService.deleteBatch(ids);

        return R.ok();
    }

//    @SysLog("选项下拉列表(项目类型)")
    @RequestMapping("/selectprojecttype")
    public R SelectProjectType(){

        List list = projectTypeService.ToSelectqueryList();
        return R.ok().put("list", list);
    }
}
