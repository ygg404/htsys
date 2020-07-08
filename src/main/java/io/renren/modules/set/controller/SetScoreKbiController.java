package io.renren.modules.set.controller;

import java.util.Arrays;
import java.util.Map;
import java.util.List;

import io.renren.modules.set.service.SetScoreKbiVoService;
import io.renren.modules.set.vo.ScoreKbiVoEntity;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.renren.modules.set.entity.SetScoreKbiEntity;
import io.renren.modules.set.service.SetScoreKbiService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * 效能分计算方法表
 *
 * @author ygg
 * @date 2020-06-11 15:40:33
 */
@RestController
@RequestMapping("set/scorekbi")
public class SetScoreKbiController {
    @Autowired
    private SetScoreKbiService setScoreKbiService;
    @Autowired
    private SetScoreKbiVoService setScoreKbiVoService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        List<ScoreKbiVoEntity> list = setScoreKbiVoService.queryList(params);

        return R.ok().put("list", list);
    }

    /**
     * 信息
     */
    @RequestMapping("/info/{dutyId}")
    public R info(@PathVariable("dutyId") Long dutyId){
		ScoreKbiVoEntity setScoreKbi = setScoreKbiVoService.queryByDutyId(dutyId);

        return R.ok().put("setScoreKbi", setScoreKbi);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody SetScoreKbiEntity setScoreKbi){
		setScoreKbiService.insertOrUpdate(setScoreKbi);

        return R.ok();
    }
}
