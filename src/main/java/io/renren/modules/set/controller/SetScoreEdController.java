package io.renren.modules.set.controller;

import java.util.List;
import java.util.Map;

import io.renren.modules.set.entity.SetScoreEdEntity;
import io.renren.modules.set.service.SetScoreEdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.renren.common.utils.R;



/**
 * 学历分对照表
 *
 * @author ygg
 * @date 2020-05-06 10:14:50
 */
@RestController
@RequestMapping("set/scoreed")
public class SetScoreEdController {
    @Autowired
    private SetScoreEdService setScoreEdService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        List<SetScoreEdEntity> list = setScoreEdService.queryList(params);

        return R.ok().put("list", list);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        SetScoreEdEntity setScoreEd = setScoreEdService.selectById(id);

        return R.ok().put("setScoreEd", setScoreEd);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody SetScoreEdEntity setScoreEd){
        Long maxNum = setScoreEdService.getMaxOrderNum(setScoreEd.getCateid());
        setScoreEd.setOrderNum(maxNum);
        setScoreEdService.save(setScoreEd);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody SetScoreEdEntity setScoreEd){
        setScoreEdService.updateById(setScoreEd);

        return R.ok();
    }

    @RequestMapping("/changeSort")
    public R changeSort(@RequestParam Map<String, Object> params){
        setScoreEdService.updateSort(params);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        setScoreEdService.deleteBatch(ids);

        return R.ok();
    }

}
