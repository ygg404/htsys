package io.renren.modules.ren.controller;

import java.util.List;
import java.util.Map;

import io.renren.modules.set.entity.SetScoreDutyEntity;
import io.renren.modules.set.service.SetScoreDutyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.renren.common.utils.R;



/**
 * 职务评分表
 *
 * @author ygg
 * @date 2020-05-06 10:14:50
 */
@RestController
@RequestMapping("set/scoreduty")
public class SetScoreDutyController {
    @Autowired
    private SetScoreDutyService setScoreDutyService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        List<SetScoreDutyEntity> list = setScoreDutyService.queryList(params);

        return R.ok().put("list", list);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        SetScoreDutyEntity renScoreDuty = setScoreDutyService.selectById(id);

        return R.ok().put("renScoreDuty", renScoreDuty);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody SetScoreDutyEntity setScoreDuty){
        Long maxNum = setScoreDutyService.getMaxOrderNum();
        setScoreDuty.setOrderNum(maxNum);
        setScoreDutyService.save(setScoreDuty);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody SetScoreDutyEntity setScoreDuty){
        setScoreDutyService.updateById(setScoreDuty);

        return R.ok();
    }

    /**
     * 修改顺序
     * @param params
     * @return
     */
    @RequestMapping("/changeSort")
    public R changeSort(@RequestParam Map<String, Object> params){
        setScoreDutyService.updateSort(params);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        setScoreDutyService.deleteBatch(ids);

        return R.ok();
    }

}
