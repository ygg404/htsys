package io.renren.modules.set.controller;

import java.util.Arrays;
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

import io.renren.modules.set.entity.WpShortcutEntity;
import io.renren.modules.set.service.WpShortcutService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * 快捷短语输入
 *
 * @author ygg
 * @date 2019-10-22 15:09:03
 */
@RestController
@RequestMapping("/set/wpshortcut")
public class WpShortcutController {
    @Autowired
    private WpShortcutService wpShortcutService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("set:wpshortcut:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = wpShortcutService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("set:wpshortcut:info")
    public R info(@PathVariable("id") Integer id){
		WpShortcutEntity wpShortcut = wpShortcutService.selectById(id);

        return R.ok().put("wpShortcut", wpShortcut);
    }

    /**
     * 通过短语类型ID 获取短语列表
     * @param shortTypeId
     * @return
     */
    @RequestMapping("/getListByShortTypeId/{shortTypeId}")
    public R getListByShortTypeId(@PathVariable("shortTypeId") Long shortTypeId){
        List<WpShortcutEntity> list = wpShortcutService.getListByShortTypeId(shortTypeId);

        return R.ok().put("list", list);
    }

    /**
     * 保存
     */
    @SysLog("保存短语")
    @RequestMapping("/save")
    @RequiresPermissions("set:wpshortcut:save")
    public R save(@RequestBody WpShortcutEntity wpShortcut){
		wpShortcutService.save(wpShortcut);

        return R.ok();
    }

    /**
     * 修改
     */
    @SysLog("修改短语")
    @RequestMapping("/update")
    @RequiresPermissions("set:wpshortcut:update")
    public R update(@RequestBody WpShortcutEntity wpShortcut){
		wpShortcutService.updateById(wpShortcut);

        return R.ok();
    }

    /**
     * 删除
     */
    @SysLog("删除短语")
    @RequestMapping("/delete")
    @RequiresPermissions("set:wpshortcut:delete")
    public R delete(@RequestBody Long[] ids){
		wpShortcutService.deleteBatch(ids);

        return R.ok();
    }

}
