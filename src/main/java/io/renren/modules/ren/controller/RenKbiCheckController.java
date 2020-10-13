package io.renren.modules.ren.controller;

import java.util.Arrays;
import java.util.Map;
import java.util.List;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import io.renren.common.annotation.SysLog;
import io.renren.modules.perf.service.PerfAssessVoService;
import io.renren.modules.perf.vo.PerfAssessVoEntity;
import io.renren.modules.ren.entity.RenAccessEntity;
import io.renren.modules.ren.entity.RenKbiPersonEntity;
import io.renren.modules.ren.service.RenKbiCheckVoService;
import io.renren.modules.ren.vo.RenKbiCheckVoEntity;
import io.renren.modules.ren.vo.RenKbiPersonVoEntity;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.renren.modules.ren.entity.RenKbiCheckEntity;
import io.renren.modules.ren.service.RenKbiCheckService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * 效能分年度考核人员
 *
 * @author ygg
 * @date 2020-08-26 09:38:17
 */
@RestController
@RequestMapping("ren/kbicheck")
public class RenKbiCheckController {
    @Autowired
    private RenKbiCheckService renKbiCheckService;
    @Autowired
    private RenKbiCheckVoService renKbiCheckVoService;
    @Autowired
    private PerfAssessVoService perfAssessVoService;
    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        List<RenKbiCheckVoEntity> list = renKbiCheckVoService.queryList(params);

        return R.ok().put("list", list);
    }

    /**
     * 信息
     */
    @RequestMapping("/info")
    public R info(@RequestParam Map<String, Object> params){
		RenKbiCheckEntity renKbiCheck = renKbiCheckService.queryByParams(params);

        return R.ok().put("renKbiCheck", renKbiCheck);
    }

    /**
     * 保存
     */
    @SysLog("保存考核人")
    @RequestMapping("/save")
    public R save(@RequestBody RenKbiCheckVoEntity renKbiCheckVo){
        Long year = renKbiCheckVo.getYear();
        Long month = renKbiCheckVo.getMonth();
        renKbiCheckService.delete(
                new EntityWrapper<RenKbiCheckEntity>().eq("year" ,year).and()
                        .eq("month",month)
        );
        renKbiCheckService.insertBatch(renKbiCheckVo.getKbiCheckList());

        return R.ok();
    }

    /**
     * 获取考核人员的提交情况
     */
    @RequestMapping("/getUcheckList")
    public R getUcheckList(@RequestParam Map<String, Object> params){
        List<RenKbiCheckVoEntity> list = renKbiCheckVoService.queryList(params);
        List<PerfAssessVoEntity> uAssessList = perfAssessVoService.queryUserHasAssess(params);
        for (RenKbiCheckVoEntity voEntity : list) {
            voEntity.setIsAssess(false);
            for(PerfAssessVoEntity uAssess : uAssessList) {
                if (voEntity.getUserId() == uAssess.getUserId()) {
                    voEntity.setIsAssess(true);
                }
            }
        }

        return R.ok().put("list", list);
    }

    /**
     * 获取被考核人数
     */
    @RequestMapping("/getCount")
    public R getCount(@RequestParam Map<String, Object> params){
        List<RenKbiCheckEntity> list = renKbiCheckService.queryList(params);

        return R.ok().put("count", list.size());
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody RenKbiCheckEntity renKbiCheck){
		renKbiCheckService.updateById(renKbiCheck);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
		renKbiCheckService.deleteBatch(ids);

        return R.ok();
    }

}
