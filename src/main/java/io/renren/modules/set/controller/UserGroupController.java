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

import io.renren.modules.set.entity.UserGroupEntity;
import io.renren.modules.set.service.UserGroupService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * 用户ID项目ID表
 *
 * @author ygg
 * @date 2019-11-07 10:55:09
 */
@RestController
@RequestMapping("set/usergroup")
public class UserGroupController {
    @Autowired
    private UserGroupService userGroupService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("set:usergroup:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = userGroupService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("set:usergroup:info")
    public R info(@PathVariable("id") Integer id){
		UserGroupEntity userGroup = userGroupService.selectById(id);

        return R.ok().put("userGroup", userGroup);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("set:usergroup:save")
    public R save(@RequestBody UserGroupEntity userGroup){
		userGroupService.save(userGroup);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("set:usergroup:update")
    public R update(@RequestBody UserGroupEntity userGroup){
		userGroupService.updateById(userGroup);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("set:usergroup:delete")
    public R delete(@RequestBody Long[] ids){
		userGroupService.deleteBatch(ids);

        return R.ok();
    }

}
