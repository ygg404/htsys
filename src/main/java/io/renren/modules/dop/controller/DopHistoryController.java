package io.renren.modules.dop.controller;

import java.util.*;

import io.renren.common.utils.UuidUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.renren.modules.dop.entity.DopHistoryEntity;
import io.renren.modules.dop.service.DopHistoryService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * 仪器租借情况表
 *
 * @author ygg
 * @date 2020-09-28 10:38:16
 */
@RestController
@RequestMapping("dop/history")
public class DopHistoryController {
    @Autowired
    private DopHistoryService dopHistoryService;

    /**
     * 分页查询
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params){
        PageUtils page = dopHistoryService.queryPage(params);

        return R.ok().put("page", page);
    }

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        List<DopHistoryEntity> list = dopHistoryService.queryList(params);

        return R.ok().put("list", list);
    }

    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
		DopHistoryEntity dopHistory = dopHistoryService.selectById(id);

        return R.ok().put("dopHistory", dopHistory);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("dop:history:save")
    public R save(@RequestBody DopHistoryEntity dopHistory){
        try {
            dopHistoryService.save(dopHistory);
        } catch (Exception ex) {
            return R.error(ex.getMessage());
        }
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("dop:history:update")
    public R update(@RequestBody DopHistoryEntity dopHistory){
		dopHistoryService.updateById(dopHistory);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("dop:history:delete")
    public R delete(@RequestBody Long[] ids){
		dopHistoryService.deleteBatch(ids);

        return R.ok();
    }


}
