package io.renren.modules.set.controller;

import java.util.List;
import java.util.Map;

import io.renren.modules.set.entity.SetScoreHouseEntity;
import io.renren.modules.set.service.SetScoreHouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.renren.common.utils.R;



/**
 * 职级及其房补表
 *
 * @author ygg
 * @date 2020-05-06 10:14:50
 */
@RestController
@RequestMapping("set/scorehouse")
public class SetScoreHouseController {
    @Autowired
    private SetScoreHouseService setScoreHouseService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        List<SetScoreHouseEntity> list = setScoreHouseService.queryList(params);

        return R.ok().put("list", list);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        SetScoreHouseEntity renScoreHouse = setScoreHouseService.selectById(id);

        return R.ok().put("renScoreHouse", renScoreHouse);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody SetScoreHouseEntity setScoreHouse){
        Long maxNum = setScoreHouseService.getMaxOrderNum();
        setScoreHouse.setOrderNum(maxNum);
        setScoreHouseService.save(setScoreHouse);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody SetScoreHouseEntity setScoreHouse){
        setScoreHouseService.updateById(setScoreHouse);

        return R.ok();
    }

    /**
     * 修改顺序
     * @param params
     * @return
     */
    @RequestMapping("/changeSort")
    public R changeSort(@RequestParam Map<String, Object> params){
        setScoreHouseService.updateSort(params);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        setScoreHouseService.deleteBatch(ids);

        return R.ok();
    }

}
