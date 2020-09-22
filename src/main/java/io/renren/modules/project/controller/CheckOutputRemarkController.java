package io.renren.modules.project.controller;

import java.util.Arrays;
import java.util.Map;
import java.util.List;

import io.renren.common.annotation.SysLog;
import io.renren.modules.project.entity.CheckOutputRemarkEntity;
import io.renren.modules.project.service.CheckOutputRemarkService;
import io.renren.modules.project.vo.CheckOutputVoEntity;
import org.apache.commons.lang.StringUtils;
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
 * 产值核算备注
 *
 * @author ygg
 * @date 2020-09-22 10:48:54
 */
@RestController
@RequestMapping("project/outputremark")
public class CheckOutputRemarkController {
    @Autowired
    private CheckOutputRemarkService checkOutputRemarkService;


    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam String projectNo){
        List<CheckOutputRemarkEntity> list = checkOutputRemarkService.queryList(projectNo);

        return R.ok().put("list", list);
    }


    /**
     * 保存
     */
    @SysLog("保存核算备注")
    @RequestMapping("/saveList")
    public R save(@RequestBody CheckOutputVoEntity checkOutputVoEntity){
        if (StringUtils.isBlank(checkOutputVoEntity.getprojectNo())) {
            return R.error("项目编号为空！");
        }
        // 保存前删除产值核算备注
		checkOutputRemarkService.deleteByProjectNo(checkOutputVoEntity.getprojectNo());
        if (checkOutputVoEntity.getOutputRemarkList().size() > 0)
        checkOutputRemarkService.insertBatch(checkOutputVoEntity.getOutputRemarkList());

        return R.ok();
    }

}
