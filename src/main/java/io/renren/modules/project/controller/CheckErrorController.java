package io.renren.modules.project.controller;

import java.util.Arrays;
import java.util.Map;
import java.util.List;

import io.renren.modules.project.entity.CheckErrorEntity;
import io.renren.modules.project.service.CheckErrorService;
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
 * 质检报告误差
 *
 * @author ygg
 * @date 2020-08-19 17:13:37
 */
@RestController
@RequestMapping("project/checkerror")
public class CheckErrorController {
    @Autowired
    private CheckErrorService checkErrorService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        List<CheckErrorEntity> list = checkErrorService.queryList(params);

        return R.ok().put("list", list);
    }

    /**
     * 信息
     */
    @RequestMapping("/info/{projectNo}")
    public R info(@PathVariable("projectNo") String projectNo){
		CheckErrorEntity checkError = checkErrorService.selectById(projectNo);

        return R.ok().put("checkError", checkError);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody CheckErrorEntity checkError){
		checkErrorService.save(checkError);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody CheckErrorEntity checkError){
		checkErrorService.updateById(checkError);

        return R.ok();
    }


}
