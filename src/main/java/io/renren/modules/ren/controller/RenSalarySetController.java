package io.renren.modules.ren.controller;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import io.renren.modules.ren.service.RenSalaryVoService;
import io.renren.modules.ren.vo.RenSalaryVoEntity;
import io.renren.modules.sys.entity.SysUserEntity;
import io.renren.modules.sys.service.SysUserService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.renren.modules.ren.entity.RenSalarySetEntity;
import io.renren.modules.ren.service.RenSalarySetService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * 基本工资设置
 *
 * @author ygg
 * @date 2020-03-23 16:58:53
 */
@RestController
@RequestMapping("ren/salaryset")
public class RenSalarySetController {
    @Autowired
    private RenSalarySetService renSalarySetService;

    @Autowired
    private RenSalaryVoService renSalaryVoService;
    @Autowired
    private SysUserService sysUserService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("ren:salaryset:list")
    public R list(){
        List<RenSalaryVoEntity> list = renSalaryVoService.getSalarySetVoList();

        return R.ok().put("list", list);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{userId}")
//    @RequiresPermissions("ren:rensalaryset:info")
    public R info(@PathVariable("userId") Long userId){
		RenSalarySetEntity salarySet = renSalarySetService.selectById(userId);
        SysUserEntity userEntity = sysUserService.selectById(userId);
        return R.ok().put("salarySet", salarySet).put("username" , userEntity.getUsername());
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
//    @RequiresPermissions("ren:salaryset:save")
    public R save(@RequestBody RenSalarySetEntity renSalarySet){
        renSalarySet.setcreateDate(new Date());
		renSalarySetService.insertOrUpdate(renSalarySet);

        return R.ok();
    }


    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("ren:salaryset:delete")
    public R delete(@RequestBody Long[] userIds){
		renSalarySetService.deleteBatch(userIds);

        return R.ok();
    }

}
