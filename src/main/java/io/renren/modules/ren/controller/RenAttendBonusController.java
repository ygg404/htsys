package io.renren.modules.ren.controller;

import java.util.Arrays;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.renren.modules.ren.entity.RenAttendBonusEntity;
import io.renren.modules.ren.service.RenAttendBonusService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * 年度全勤奖天数
 *
 * @author ygg
 * @date 2020-03-21 14:24:14
 */
@RestController
@RequestMapping("ren/attendbonus")
public class RenAttendBonusController {
    @Autowired
    private RenAttendBonusService renAttendBonusService;


    /**
     * 信息
     */
    @RequestMapping("/info/{year}")
    public R info(@PathVariable("year") Long year){
		RenAttendBonusEntity renAttendBonus = renAttendBonusService.selectById(year);

        return R.ok().put("renAttendBonus", renAttendBonus);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody RenAttendBonusEntity renAttendBonus){
		renAttendBonusService.insertOrUpdate(renAttendBonus);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody RenAttendBonusEntity renAttendBonus){
		renAttendBonusService.updateById(renAttendBonus);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] years){
		renAttendBonusService.deleteBatch(years);

        return R.ok();
    }

}
