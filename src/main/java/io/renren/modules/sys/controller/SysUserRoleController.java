package io.renren.modules.sys.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.renren.modules.sys.entity.SysUserRoleEntity;
import io.renren.modules.sys.service.SysUserRoleService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * 用户与角色对应关系
 *
 * @author ygg
 * @date 2020-05-13 16:40:04
 */
@RestController
@RequestMapping("sys/userrole")
public class SysUserRoleController {
    @Autowired
    private SysUserRoleService sysUserRoleService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        List<SysUserRoleEntity> list = sysUserRoleService.queryList(params);

        return R.ok().put("list", list);
    }

    @RequestMapping("/update")
    public R update(@RequestBody SysUserRoleEntity sysUserRoleEntity){
        Long[] uId = {sysUserRoleEntity.getUserId()};

        sysUserRoleService.deleteBatchByUId(uId);
        if(sysUserRoleEntity.getRoleId() != 0)sysUserRoleService.insert(sysUserRoleEntity);

        return R.ok();
    }
}
