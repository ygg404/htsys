package io.renren.modules.sys.controller;

import java.util.Arrays;
import java.util.Map;
import java.util.List;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import io.renren.modules.sys.vo.RoleKBIVoEntity;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.renren.modules.sys.entity.SysRoleKbiEntity;
import io.renren.modules.sys.service.SysRoleKbiService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * 效能考核项与岗位关系设置
 *
 * @author ygg
 * @date 2020-05-15 15:55:24
 */
@RestController
@RequestMapping("sys/rolekbi")
public class SysRoleKbiController {
    @Autowired
    private SysRoleKbiService sysRoleKbiService;


    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        List<SysRoleKbiEntity> list = sysRoleKbiService.queryList();

        return R.ok().put("list", list);
    }

    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("sys:rolekbi:info")
    public R info(@PathVariable("id") Long id){
		SysRoleKbiEntity sysRoleKbi = sysRoleKbiService.selectById(id);

        return R.ok().put("sysRoleKbi", sysRoleKbi);
    }

    /**
     * 保存
     */
    @RequestMapping("/saveList")
    public R save(@RequestBody RoleKBIVoEntity roleKbi){
        List<SysRoleKbiEntity> list = roleKbi.getRkList();
        sysRoleKbiService.delete(new EntityWrapper<>());
		sysRoleKbiService.insertOrUpdateBatch(list);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("ren:sysrolekbi:update")
    public R update(@RequestBody SysRoleKbiEntity sysRoleKbi){
		sysRoleKbiService.updateById(sysRoleKbi);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("ren:sysrolekbi:delete")
    public R delete(@RequestBody Long[] ids){
		sysRoleKbiService.deleteBatch(ids);

        return R.ok();
    }

}
