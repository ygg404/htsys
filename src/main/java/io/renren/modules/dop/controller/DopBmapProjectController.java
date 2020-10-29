package io.renren.modules.dop.controller;

import java.util.Arrays;
import java.util.Map;
import java.util.List;

import io.renren.modules.dop.entity.DopBmapProjectEntity;
import io.renren.modules.dop.service.DopBmapProjectService;
import io.renren.modules.dop.service.DopBmapService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * 百度地图项目名称
 *
 * @author ygg
 * @date 2020-10-22 10:56:31
 */
@RestController
@RequestMapping("dop/bmapproject")
public class DopBmapProjectController {
    @Autowired
    private DopBmapProjectService dopBmapProjectService;
    @Autowired
    private DopBmapService dopBmapService;

    /**
     * 分页查询
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params){
        PageUtils page = dopBmapProjectService.queryPage(params);

        return R.ok().put("page", page);
    }

    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
		DopBmapProjectEntity dopBmapProject = dopBmapProjectService.selectById(id);

        return R.ok().put("dopBmapProject", dopBmapProject);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("dop:bmap:save")
    public R save(@RequestBody DopBmapProjectEntity dopBmapProject){
		dopBmapProjectService.save(dopBmapProject);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("dop:bmap:update")
    public R update(@RequestBody DopBmapProjectEntity dopBmapProject){
		dopBmapProjectService.updateById(dopBmapProject);

        return R.ok();
    }

    /**
     * 删除(项目及其标注信息)
     */
    @RequestMapping("/delete/{id}")
    @RequiresPermissions("dop:bmap:delete")
    public R delete(@PathVariable("id") Long id){
		dopBmapProjectService.deleteBatch(new Long[] {id});
        dopBmapService.deleteByProId(id);
        return R.ok();
    }

}
