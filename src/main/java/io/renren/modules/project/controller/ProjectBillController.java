package io.renren.modules.project.controller;

import java.util.Arrays;
import java.util.Map;
import java.util.List;

import io.renren.common.annotation.SysLog;
import io.renren.modules.project.entity.ProjectBillEntity;
import io.renren.modules.project.service.ProjectBillService;
import io.renren.modules.project.vo.ProjectInfoVoEntity;
import io.renren.modules.project.vo.ProjectVoEntity;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * 项目清单表
 *
 * @author ygg
 * @date 2020-12-08 11:07:29
 */
@RestController
@RequestMapping("project/bill")
public class ProjectBillController {
    @Autowired
    private ProjectBillService projectBillService;


    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        List<ProjectBillEntity> list = projectBillService.queryList(params);

        return R.ok().put("list", list);
    }

    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
		ProjectBillEntity projectBill = projectBillService.selectById(id);

        return R.ok().put("projectBill", projectBill);
    }

    /**
     * 保存
     */
    @SysLog("保存项目清单表")
    @RequestMapping("/save")
    public R save(@RequestBody ProjectInfoVoEntity projectInfoVo){
        projectBillService.deleteByProNo(projectInfoVo.getprojectNo());
        if (projectInfoVo.getProjectBillList().size() > 0) projectBillService.insertBatch(projectInfoVo.getProjectBillList());

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("set:projectbill:update")
    public R update(@RequestBody ProjectBillEntity projectBill){
		projectBillService.updateById(projectBill);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("set:projectbill:delete")
    public R delete(@RequestBody Long[] ids){
		projectBillService.deleteBatch(ids);

        return R.ok();
    }

}
