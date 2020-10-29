package io.renren.modules.perf.controller;

import java.util.Arrays;
import java.util.Map;
import java.util.List;

import io.renren.common.annotation.SysLog;
import io.renren.modules.perf.vo.PerfExtraVoEntity;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.renren.modules.perf.entity.PerfExtraScoringEntity;
import io.renren.modules.perf.service.PerfExtraScoringService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * 加减分年度评分
 *
 * @author ygg
 * @date 2020-05-25 17:36:28
 */
@RestController
@RequestMapping("perf/extrascoring")
public class PerfExtraScoringController {
    @Autowired
    private PerfExtraScoringService perfExtraScoringService;


    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        List<PerfExtraScoringEntity> list = perfExtraScoringService.queryList(params);

        return R.ok().put("list", list);
    }

    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("perf:extrascoring:info")
    public R info(@PathVariable("id") Long id){
		PerfExtraScoringEntity perfExtraScoring = perfExtraScoringService.selectById(id);

        return R.ok().put("perfExtraScoring", perfExtraScoring);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("perf:extrascoring:save")
    public R save(@RequestBody PerfExtraScoringEntity perfExtraScoring){
		perfExtraScoringService.save(perfExtraScoring);

        return R.ok();
    }

    /**
     * 保存加减分评分列表
     */
    @SysLog("保存加减评分")
    @RequestMapping("/saveList")
    public R saveList(@RequestBody PerfExtraVoEntity perfExtraVoEntity){
        if(perfExtraVoEntity.getCheckUserId() == null){
            return R.error("考核对象不能为空");
        }
        if(perfExtraVoEntity.getYear() == null || perfExtraVoEntity.getMonth() == null){
            return R.error("提交日期不能为空");
        }
        perfExtraScoringService.deleteByParms(perfExtraVoEntity);
        perfExtraScoringService.insertBatch(perfExtraVoEntity.getScoreList());

        return R.ok();
    }


}
