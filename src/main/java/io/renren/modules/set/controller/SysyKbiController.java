package io.renren.modules.set.controller;

import java.util.List;
import java.util.Map;

import io.renren.modules.sys.service.SysKbiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.renren.modules.sys.entity.SysKbiEntity;
import io.renren.common.utils.R;

/**
 * 效能考核项
 *
 * @author ygg
 * @date 2020-05-15 10:26:46
 */
@RestController
@RequestMapping("/sysykbi")
public class SysyKbiController {
    @Autowired
    private SysKbiService sysKbiService;


    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        List<SysKbiEntity> list = sysKbiService.queryList(params);

        return R.ok().put("list", list);
    }

    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
		SysKbiEntity sysyKbi = sysKbiService.selectById(id);

        return R.ok().put("sysyKbi", sysyKbi);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody SysKbiEntity sysyKbi){
        sysKbiService.save(sysyKbi);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody SysKbiEntity sysyKbi){
        sysKbiService.updateById(sysyKbi);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        sysKbiService.deleteBatch(ids);

        return R.ok();
    }

}
