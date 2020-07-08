package io.renren.modules.ren.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.renren.modules.ren.entity.RenBranchScoreEntity;
import io.renren.modules.ren.service.RenBranchScoreService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.renren.modules.ren.entity.RenBranchBaseEntity;
import io.renren.modules.ren.service.RenBranchBaseService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * 部门考核基本信息
 *
 * @author ygg
 * @date 2020-03-05 10:58:38
 */
@RestController
@RequestMapping("ren/branchbase")
public class RenBranchBaseController {
    @Autowired
    private RenBranchBaseService renBranchBaseService;
    @Autowired
    private RenBranchScoreService renBranchScoreService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        List<RenBranchBaseEntity> list = renBranchBaseService.queryList(params);
        //获取对应的产值分数列表
        for(RenBranchBaseEntity entity : list){
            HashMap<String ,Object> parm = new HashMap<>();
            parm.put("branchId" , entity.getbranchId().toString());
            List<RenBranchScoreEntity> scoreList = renBranchScoreService.queryList(parm);
            entity.setScoreList(scoreList);
        }
        return R.ok().put("list", list);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{branchId}")
    public R info(@PathVariable("branchId") Long branchId){
		RenBranchBaseEntity renBranchBase = renBranchBaseService.selectById(branchId);
        HashMap<String ,Object> parm = new HashMap<>();
        parm.put("branchId" , branchId.toString());
        List<RenBranchScoreEntity> scoreList = renBranchScoreService.queryList(parm);
        if(scoreList.size() > 0) renBranchBase.setScoreList(scoreList);

        return R.ok().put("renBranchBase", renBranchBase);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody RenBranchBaseEntity renBranchBase){
        // 保存前删除团队产值评分明细
        Long branchId = renBranchBase.getbranchId();
        renBranchBaseService.deleteBatch(new Long[]{branchId});
        renBranchScoreService.deleteByBranchId(branchId);

		renBranchBaseService.insert(renBranchBase);
		renBranchScoreService.insertBatch(renBranchBase.getScoreList());

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody RenBranchBaseEntity renBranchBase){
		renBranchBaseService.updateById(renBranchBase);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody RenBranchBaseEntity renBranchBase){
        Long branchId = renBranchBase.getbranchId();
		renBranchBaseService.deleteBatch(new Long[]{branchId});
		renBranchScoreService.deleteByBranchId(branchId);

        return R.ok();
    }

}
