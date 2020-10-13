package io.renren.modules.ren.controller;

import java.util.Arrays;
import java.util.Map;
import java.util.List;

import cn.hutool.db.Entity;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import io.renren.common.annotation.SysLog;
import io.renren.modules.ren.service.RenKbiPersonVoService;
import io.renren.modules.ren.vo.RenKbiPersonVoEntity;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.renren.modules.ren.entity.RenKbiPersonEntity;
import io.renren.modules.ren.service.RenKbiPersonService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * 效能分年度被考核人员
 *
 * @author ygg
 * @date 2020-07-04 10:33:57
 */
@RestController
@RequestMapping("ren/kbiperson")
public class RenKbiPersonController {
    @Autowired
    private RenKbiPersonService renKbiPersonService;
    @Autowired
    private RenKbiPersonVoService renKbiPersonVoService;
    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        List<RenKbiPersonVoEntity> list = renKbiPersonVoService.queryList(params);

        return R.ok().put("list", list);
    }

    /**
     * 获取被考核人数
     */
    @RequestMapping("/getCount")
    public R getCount(@RequestParam Map<String, Object> params){
        List<RenKbiPersonEntity> list = renKbiPersonService.queryList(params);

        return R.ok().put("count", list.size());
    }
    /**
     * 保存
     */
    @SysLog("保存被考核人")
    @RequestMapping("/save")
    public R save(@RequestBody RenKbiPersonVoEntity renKbiPersonVo){
        Long year = renKbiPersonVo.getYear();
        Long month = renKbiPersonVo.getMonth();
        renKbiPersonService.delete(
                new EntityWrapper<RenKbiPersonEntity>().eq("year" ,year).and()
                .eq("month",month)
        );
		renKbiPersonService.insertBatch(renKbiPersonVo.getKbiPersonList());

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("ren:kbiperson:update")
    public R update(@RequestBody RenKbiPersonEntity renKbiPerson){
		renKbiPersonService.updateById(renKbiPerson);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("ren:kbiperson:delete")
    public R delete(@RequestBody Long[] ids){
		renKbiPersonService.deleteBatch(ids);

        return R.ok();
    }

}

