package io.renren.modules.set.controller;

import java.util.List;
import java.util.Map;

import io.renren.modules.set.entity.SetScoreTitleEntity;
import io.renren.modules.set.service.SetScoreTitleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.renren.common.utils.R;



/**
 * 职称基准分表
 *
 * @author ygg
 * @date 2020-05-06 10:14:50
 */
@RestController
@RequestMapping("set/scoretitle")
public class SetScoreTitleController {
    @Autowired
    private SetScoreTitleService setScoreTitleService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        List<SetScoreTitleEntity> list = setScoreTitleService.queryList(params);

        return R.ok().put("list", list);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        SetScoreTitleEntity renScoreTitle = setScoreTitleService.selectById(id);

        return R.ok().put("renScoreTitle", renScoreTitle);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody SetScoreTitleEntity renScoreTitle){
        Long maxNum = setScoreTitleService.getMaxOrderNum();
        renScoreTitle.setOrderNum(maxNum);
        setScoreTitleService.save(renScoreTitle);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody SetScoreTitleEntity renScoreTitle){
        setScoreTitleService.updateById(renScoreTitle);

        return R.ok();
    }

    /**
     * 修改顺序
     * @param params
     * @return
     */
    @RequestMapping("/changeSort")
    public R changeSort(@RequestParam Map<String, Object> params){
        setScoreTitleService.updateSort(params);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        setScoreTitleService.deleteBatch(ids);

        return R.ok();
    }

}
