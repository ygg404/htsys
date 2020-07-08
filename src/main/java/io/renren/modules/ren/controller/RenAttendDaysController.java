package io.renren.modules.ren.controller;

import java.util.Arrays;
import java.util.Date;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.renren.modules.ren.entity.RenAttendDaysEntity;
import io.renren.modules.ren.service.RenAttendDaysService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * 应出勤天数表
 *
 * @author ygg
 * @date 2020-03-10 15:32:12
 */
@RestController
@RequestMapping("ren/attenddays")
public class RenAttendDaysController {

    @Autowired
    private RenAttendDaysService renAttendDaysService;


    /**
     * 信息( 获取应出勤天数)
     */
    @RequestMapping("/info/{date}")
//    @RequiresPermissions("ren:attenddays:info")
    public R info(@PathVariable("date") String date){
		RenAttendDaysEntity attendDays = renAttendDaysService.getByDate(date);

        return R.ok().put("attendDays", attendDays);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
//    @RequiresPermissions("ren:attenddays:save")
    public R save(@RequestBody RenAttendDaysEntity renAttendDays){
        renAttendDaysService.deleteByDate(renAttendDays.getdate());
		renAttendDaysService.save(renAttendDays);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("ren:attenddays:update")
    public R update(@RequestBody RenAttendDaysEntity renAttendDays){
		renAttendDaysService.updateById(renAttendDays);

        return R.ok();
    }


}
