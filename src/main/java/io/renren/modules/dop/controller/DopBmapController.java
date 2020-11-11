package io.renren.modules.dop.controller;

import java.util.Arrays;
import java.util.Date;
import java.util.Map;
import java.util.List;

import io.renren.common.annotation.SysLog;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.renren.modules.dop.entity.DopBmapEntity;
import io.renren.modules.dop.service.DopBmapService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * 地图标注表
 *
 * @author ygg
 * @date 2020-09-24 10:26:01
 */
@RestController
@RequestMapping("dop/bmap")
public class DopBmapController {
    @Autowired
    private DopBmapService dopBmapService;

    /**
     * 分页查询
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params){
        PageUtils page = dopBmapService.queryPage(params);

        return R.ok().put("page", page);
    }

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        List<DopBmapEntity> list = dopBmapService.queryList(params);

        return R.ok().put("list", list);
    }

    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
		DopBmapEntity dopBmap = dopBmapService.selectById(id);

        return R.ok().put("dopBmap", dopBmap);
    }

    /**
     * 保存
     */
    @SysLog("保存地图标注")
    @RequestMapping("/save")
    @RequiresPermissions("dop:bmap:save")
    public R save(@RequestBody DopBmapEntity dopBmap){
		dopBmapService.save(dopBmap);

        return R.ok();
    }

    /**
     * 修改
     */
    @SysLog("修改地图标注")
    @RequestMapping("/update")
    @RequiresPermissions("dop:bmap:update")
    public R update(@RequestBody DopBmapEntity dopBmap){
		dopBmapService.updateById(dopBmap);

        return R.ok();
    }

    /**
     * 删除
     */
    @SysLog("删除地图标注")
    @RequestMapping("/delete/{id}")
    @RequiresPermissions("dop:bmap:delete")
    public R delete(@PathVariable("id") Long id){
        dopBmapService.deleteBatch(new Long[] {id});

        return R.ok();
    }

}
