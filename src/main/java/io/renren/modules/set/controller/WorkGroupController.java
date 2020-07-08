package io.renren.modules.set.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import io.renren.common.annotation.SysLog;
import io.renren.modules.set.entity.BranchEntity;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.renren.modules.set.entity.WorkGroupEntity;
import io.renren.modules.set.service.WorkGroupService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * 部门列表
 *
 * @author ygg
 * @date 2019-10-23 17:08:31
 */
@RestController
@RequestMapping("/set/workgroup")
public class WorkGroupController {
    @Autowired
    private WorkGroupService workGroupService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        List<WorkGroupEntity> list = workGroupService.queryList(params);

        return R.ok().put("list", list);
    }

    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        WorkGroupEntity workGroup = workGroupService.selectById(id);

        return R.ok().put("workGroup", workGroup);
    }

    /**
     * 保存
     */
    @SysLog("保存工作组")
    @RequestMapping("/save")
    @RequiresPermissions("set:workgroup:save")
    public R save(@RequestBody WorkGroupEntity workGroup){
        workGroupService.save(workGroup);

        return R.ok();
    }

    /**
     * 修改
     */
    @SysLog("修改工作组")
    @RequestMapping("/update")
    @RequiresPermissions("set:workgroup:update")
    public R update(@RequestBody WorkGroupEntity workGroup){
        workGroupService.updateAllColumnById(workGroup);

        return R.ok();
    }

    /**
     * 排序
     */
    @SysLog("修改部门排序")
    @RequestMapping("/sort")
    @RequiresPermissions("set:workgroup:update")
    public R sort(@RequestBody WorkGroupEntity workGroup){
        workGroupService.setSort(workGroup);

        return R.ok();
    }

    /**
     * 删除
     */
    @SysLog("删除工作组")
    @RequestMapping("/delete")
    @RequiresPermissions("set:workgroup:delete")
    public R delete(@RequestBody Long[] ids){
        workGroupService.deleteBatch(ids);

        return R.ok();
    }


}
