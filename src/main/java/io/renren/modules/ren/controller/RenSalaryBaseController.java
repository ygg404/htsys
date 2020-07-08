package io.renren.modules.ren.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import io.renren.modules.ren.service.RenRecordVoService;
import io.renren.modules.ren.service.RenSalaryVoService;
import io.renren.modules.ren.vo.RenRecordVoEntity;
import io.renren.modules.ren.vo.RenSalaryVoEntity;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.renren.modules.ren.entity.RenSalaryBaseEntity;
import io.renren.modules.ren.service.RenSalaryBaseService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;

import javax.servlet.http.HttpServletResponse;


/**
 * 基本工资表
 *
 * @author ygg
 * @date 2020-03-07 11:22:51
 */
@RestController
@RequestMapping("ren/salarybase")
public class RenSalaryBaseController {
    @Autowired
    private RenSalaryBaseService renSalaryBaseService;

    @Autowired
    private RenSalaryVoService renSalaryVoService;

    @Autowired
    private RenRecordVoService renRecordVoService;
    /**
     * 列表
     */
    @RequestMapping("/list")
//    @RequiresPermissions("ren:salarybase:list")
    public R list(@RequestParam Map<String, Object> params){
//        List<RenRecordVoEntity> list =
        List<RenRecordVoEntity> recordList = renRecordVoService.getRecordScoreVoList(params);
        List<RenSalaryBaseEntity> salaryBaseList = renSalaryBaseService.queryList(params);

        List<RenSalaryBaseEntity> list = renSalaryBaseService.setSalaryByRecord(recordList,salaryBaseList,params);
        return R.ok().put("list", list);
    }

    @RequestMapping("/exportExcel")
    public R exportExcel(@RequestParam Map<String, Object> params) {
        try {
            List<RenRecordVoEntity> recordList = renRecordVoService.getRecordScoreVoList(params);
            List<RenSalaryBaseEntity> salaryBaseList = renSalaryBaseService.queryList(params);
            List<RenSalaryBaseEntity> list = renSalaryBaseService.setSalaryByRecord(recordList,salaryBaseList,params);



        } catch (Exception ex){
            return R.error(ex.toString());
        }
       return R.ok();
    }
    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("ren:salarybase:info")
    public R info(@PathVariable("id") Long id){
		RenSalaryBaseEntity renSalaryBase = renSalaryBaseService.selectById(id);

        return R.ok().put("renSalaryBase", renSalaryBase);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("ren:salarybase:save")
    public R save(@RequestBody RenSalaryBaseEntity renSalaryBase){
		renSalaryBaseService.save(renSalaryBase);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("ren:salarybase:update")
    public R update(@RequestBody RenSalaryBaseEntity renSalaryBase){
		renSalaryBaseService.updateById(renSalaryBase);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("ren:salarybase:delete")
    public R delete(@RequestBody Long[] ids){
		renSalaryBaseService.deleteBatch(ids);

        return R.ok();
    }

    /**
     *  上传基本工资Excel
     */
    @RequestMapping("/upBaseExcel")
    public R upBaseExcel(@RequestParam("payDate") String payDate, HttpServletResponse response) {
        return R.ok();
    }
}
