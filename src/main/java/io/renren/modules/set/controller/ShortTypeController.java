package io.renren.modules.set.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import io.renren.common.annotation.SysLog;
import io.renren.modules.set.dao.ShortTypeDao;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.renren.modules.set.entity.ShortTypeEntity;
import io.renren.modules.set.service.ShortTypeService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;


/**
 * 类型名称
 *
 * @author ygg
 * @date 2019-10-23 09:28:19
 */
@RestController
@RequestMapping("/set/shorttype")
public class ShortTypeController {
    @Autowired
    private ShortTypeService shortTypeService;

    /**
     * 列表(分页)
     */
    @RequestMapping("/page")
//    @RequiresPermissions("set:shorttype:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = shortTypeService.queryPage(params);

        return R.ok().put("page", page);
    }

    /**
     * 列表
     */
//    @SysLog("输出短语类型列表")
    @RequestMapping("/list")
//    @RequiresPermissions("set:shorttype:list")
    public R list(){
        List<ShortTypeEntity> list = shortTypeService.queryList();

        return R.ok().put("list", list);
    }

    /**
     * 信息
     */
//    @SysLog("输出指定的单条短语类型信息")
    @RequestMapping("/info/{id}")
//    @RequiresPermissions("set:shorttype:info")
    public R info(@PathVariable("id") Integer id){
		ShortTypeEntity shortType = shortTypeService.selectById(id);

        return R.ok().put("shortType", shortType);
    }

    /**
     * 保存
     */
    @SysLog("保存短语类型")
    @RequestMapping("/save")
//    @RequiresPermissions("set:shorttype:save")
    public R save(@RequestBody ShortTypeEntity shortType){
		shortTypeService.save(shortType);

        return R.ok();
    }

    /**
     * 修改
     */
    @SysLog("修改短语类型")
    @RequestMapping("/update")
    @RequiresPermissions("set:shorttype:update")
    public R update(@RequestBody ShortTypeEntity shortType){
		shortTypeService.updateById(shortType);

        return R.ok();
    }

    /**
     * 删除
     */
    @SysLog("删除短语类型")
    @RequestMapping("/delete")
    @RequiresPermissions("set:shorttype:delete")
    public R delete(@RequestBody int[] ids){
		shortTypeService.deleteBatch(ids);

        return R.ok();
    }

}
