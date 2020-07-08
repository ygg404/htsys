package io.renren.modules.perf.controller;

import java.util.Arrays;
import java.util.Map;
import java.util.List;

import io.renren.common.annotation.SysLog;
import io.renren.modules.perf.dao.PerfAssessVoDao;
import io.renren.modules.perf.service.PerfAssessVoService;
import io.renren.modules.perf.vo.PerfAssessVoEntity;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.renren.modules.perf.entity.PerfExtraEntity;
import io.renren.modules.perf.service.PerfExtraService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;


/**
 * 加减分表
 *
 * @author ygg
 * @date 2020-05-25 17:36:28
 */
@RestController
@RequestMapping("perf/extra")
public class PerfExtraController {

    @Autowired
    private PerfExtraService perfExtraService;
    @Autowired
    private PerfAssessVoService perfAssessVoService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        List<PerfExtraEntity> list = perfExtraService.queryList(params);

        return R.ok().put("list", list);
    }

    /**
     * 获取所有岗位的用户
     */
    @RequestMapping("/userList")
    public R userList(Map<String, Object> params){
        List<PerfAssessVoEntity> userList = perfAssessVoService.queryUserRoleList(params);

        return R.ok().put("userList", userList);
    }
    /**
     * 保存
     */
    @SysLog("保存加减分明细")
    @RequestMapping("/save")
    @RequiresPermissions("perf:extra:save")
    public R save(@RequestBody PerfExtraEntity perfExtra){
		perfExtraService.save(perfExtra);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("perf:extra:update")
    public R update(@RequestBody PerfExtraEntity perfExtra){
		perfExtraService.updateById(perfExtra);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("perf:extra:delete")
    public R delete(@RequestBody Long[] ids){
		perfExtraService.deleteBatch(ids);

        return R.ok();
    }

}
