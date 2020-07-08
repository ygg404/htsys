package io.renren.modules.perf.controller;

import java.util.Map;
import java.util.List;

import io.renren.common.annotation.SysLog;
import io.renren.modules.perf.service.PerfAssessVoService;
import io.renren.modules.perf.vo.PerfAssessVoEntity;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.renren.modules.perf.entity.PerfAssessEntity;
import io.renren.modules.perf.service.PerfAssessService;
import io.renren.common.utils.R;



/**
 * 效能考核评分
 *
 * @author ygg
 * @date 2020-05-16 15:45:46
 */
@RestController
@RequestMapping("perf/access")
public class PerfAssessController {

    @Autowired
    private PerfAssessService perfAccessService;
    @Autowired
    private PerfAssessVoService perfAssessVoService;

    /**
     * 列表
     */
    @RequestMapping("/list")
//    @RequiresPermissions("perf:access:list")
    public R list(@RequestParam Map<String, Object> params){
        List<PerfAssessEntity> list = perfAccessService.queryList(params);

        return R.ok().put("list", list);
    }

    /**
     * 各岗位中各成员的年度评分列表
     */
    @RequestMapping("/volist")
//    @RequiresPermissions("perf:access:list")
    public R volist(@RequestParam Map<String, Object> params){
        List<PerfAssessVoEntity> list = perfAssessVoService.queryPerfAccessVoList(params);
        boolean isPost = perfAssessVoService.queryCountPerfAccess(params) > 0L ? true:false;
        return R.ok().put("list", list).put("isPost", isPost);
    }

    /**
     * 获取已经评分的用户列表
     */
    @RequestMapping("/uAssessList")
    public R uAccessList(@RequestParam Map<String, Object> params){
        List<PerfAssessVoEntity> uAssessList = perfAssessVoService.queryUserHasAssess(params);
        List<PerfAssessVoEntity> uRoleList = perfAssessVoService.queryUserRoleList(params);
        for (PerfAssessVoEntity voEntity : uRoleList) {
            voEntity.setIsAssess(false);
            for(PerfAssessVoEntity uAssess : uAssessList) {
                if (voEntity.getUserId() == uAssess.getUserId()) {
                    voEntity.setIsAssess(true);
                }
            }
        }

        return R.ok().put("list", uRoleList);
    }

    /**
     * 保存
     */
    @SysLog("提交效能评分")
    @RequestMapping("/saveList")
    public R save(@RequestBody PerfAssessVoEntity perfAccessVo){
        if(perfAccessVo.getUserId() == null){
            return R.error("提交用户Id为空");
        }
        if(perfAccessVo.getYear() == null || perfAccessVo.getUpdown() == null){
            return R.error("提交年份为空");
        }
        perfAccessService.deleteByMap(perfAccessVo);
        perfAccessService.insertBatch(perfAccessVo.getAccessList());

        return R.ok("提交评分成功");
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("perf:access:update")
    public R update(@RequestBody PerfAssessEntity perfAccess){
		perfAccessService.updateById(perfAccess);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("perf:access:delete")
    public R delete(@RequestBody Long[] ids){
		perfAccessService.deleteBatch(ids);

        return R.ok();
    }

}
