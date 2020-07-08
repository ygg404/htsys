package io.renren.modules.set.controller;

import java.util.Arrays;
import java.util.Map;
import java.util.List;

import io.renren.common.annotation.SysLog;
import io.renren.modules.set.entity.SetScoreKbiDutyEntity;
import io.renren.modules.set.service.SetScoreKbiTitleVoService;
import io.renren.modules.set.vo.ScoreKbiTitleVoEntity;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.renren.modules.set.entity.SetScoreKbiTitleEntity;
import io.renren.modules.set.service.SetScoreKbiTitleService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * 职称效能分计算方法表
 *
 * @author ygg
 * @date 2020-06-29 14:17:35
 */
@RestController
@RequestMapping("set/scorekbititle")
public class SetScoreKbiTitleController {
    @Autowired
    private SetScoreKbiTitleService setScoreKbiTitleService;
    @Autowired
    private SetScoreKbiTitleVoService setScoreKbiTitleVoService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        List<ScoreKbiTitleVoEntity> list = setScoreKbiTitleVoService.queryList(params);

        return R.ok().put("list", list);
    }

    /**
     * 信息
     */
    @RequestMapping("/info/{titleId}")
    public R info(@PathVariable("titleId") Long titleId){
		ScoreKbiTitleVoEntity scoreKbiTitle = setScoreKbiTitleVoService.queryByDutyId(titleId);

        return R.ok().put("scoreKbiTitle", scoreKbiTitle);
    }

    /**
     * 保存
     */
    @SysLog("保存职称效能分设置")
    @RequestMapping("/save")
    public R save(@RequestBody SetScoreKbiTitleEntity setScoreKbi){
        setScoreKbiTitleService.insertOrUpdate(setScoreKbi);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] titleIds){
		setScoreKbiTitleService.deleteBatch(titleIds);

        return R.ok();
    }

}
