package io.renren.modules.sys.controller;

import java.util.Arrays;
import java.util.Map;

import io.renren.modules.sys.entity.SysUserEntity;
import io.renren.modules.sys.vo.UserDetailVoEntity;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.renren.modules.sys.entity.SysUserDetailEntity;
import io.renren.modules.sys.service.SysUserDetailService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * 用户明细表
 *
 * @author ygg
 * @date 2019-11-30 11:04:55
 */
@RestController
@RequestMapping("sys/userdetail")
public class SysUserDetailController {
    @Autowired
    private SysUserDetailService sysUserDetailService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("project:sysuserdetail:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = sysUserDetailService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{userId}")
    @RequiresPermissions("sys:userdetail:info")
    public R info(@PathVariable("userId") Long userId){
		SysUserDetailEntity sysUserDetail = sysUserDetailService.selectById(userId);

        return R.ok().put("sysUserDetail", sysUserDetail);
    }

    /**
     * 获取当前用户信息
     */
    @RequestMapping("/info")
//    @RequiresPermissions("sys:userdetail:info")
    public R info(){
        Long userId = ((SysUserEntity) SecurityUtils.getSubject().getPrincipal()).getUserId();
        UserDetailVoEntity userDetailVo = sysUserDetailService.queryUserDetail(userId);

        return R.ok().put("userDetailVo", userDetailVo);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("project:sysuserdetail:save")
    public R save(@RequestBody SysUserDetailEntity sysUserDetail){
		sysUserDetailService.save(sysUserDetail);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("project:sysuserdetail:update")
    public R update(@RequestBody SysUserDetailEntity sysUserDetail){
		sysUserDetailService.updateById(sysUserDetail);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("project:sysuserdetail:delete")
    public R delete(@RequestBody Long[] userIds){
		sysUserDetailService.deleteBatch(userIds);

        return R.ok();
    }

}
