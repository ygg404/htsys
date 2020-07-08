package io.renren.modules.set.controller;

import java.util.Arrays;
import java.util.Map;
import java.util.List;

import io.renren.common.annotation.SysLog;
import io.renren.modules.set.entity.SetScoreKbiEntity;
import io.renren.modules.set.entity.SetScoreKbiTitleEntity;
import io.renren.modules.set.service.SetScoreKbiDutyVoService;
import io.renren.modules.set.vo.ScoreKbiDutyVoEntity;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.renren.modules.set.entity.SetScoreKbiDutyEntity;
import io.renren.modules.set.service.SetScoreKbiDutyService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * 职务效能分计算方法表
 *
 * @author ygg
 * @date 2020-06-29 14:17:35
 */
@RestController
@RequestMapping("set/scorekbiduty")
public class SetScoreKbiDutyController {
    @Autowired
    private SetScoreKbiDutyService setScoreKbiDutyService;
    @Autowired
    private SetScoreKbiDutyVoService setScoreKbiDutyVoService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        List<ScoreKbiDutyVoEntity> list = setScoreKbiDutyVoService.queryList(params);

        return R.ok().put("list", list);
    }

    /**
     * 信息
     */
    @RequestMapping("/info/{dutyId}")
    public R info(@PathVariable("dutyId") Long dutyId){
		ScoreKbiDutyVoEntity scoreKbiDuty = setScoreKbiDutyVoService.queryByDutyId(dutyId);

        return R.ok().put("scoreKbiDuty", scoreKbiDuty);
    }

    /**
     * 保存
     */
    @SysLog("保存职务效能分设置")
    @RequestMapping("/save")
    public R save(@RequestBody SetScoreKbiDutyEntity setScoreKbi){
        setScoreKbiDutyService.insertOrUpdate(setScoreKbi);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] dutyIds){
		setScoreKbiDutyService.deleteBatch(dutyIds);

        return R.ok();
    }

}
