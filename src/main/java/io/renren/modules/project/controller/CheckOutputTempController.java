package io.renren.modules.project.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import io.renren.common.annotation.SysLog;
import io.renren.common.utils.MapUtils;
import io.renren.common.utils.StringUtil;
import io.renren.modules.project.entity.CheckOutputEntity;
import io.renren.modules.project.vo.CheckOutputTempVoEntity;
import io.renren.modules.project.vo.CheckOutputVoEntity;
import io.renren.modules.set.entity.WorkTypeEntity;
import io.renren.modules.set.service.WorkTypeService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.renren.modules.project.entity.CheckOutputTempEntity;
import io.renren.modules.project.service.CheckOutputTempService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * 产值明细预算表
 *
 * @author ygg
 * @date 2019-12-26 16:01:03
 */
@RestController
@RequestMapping("project/checkoutputtemp")
public class CheckOutputTempController {
    @Autowired
    private CheckOutputTempService checkOutputTempService;
    @Autowired
    private WorkTypeService workTypeService;

    /**
     * 列表
     */
    @RequestMapping("/list")
//    @RequiresPermissions("project:checkoutputtemp:list")
    public R list(@RequestParam Map<String, Object> params){
        List<CheckOutputVoEntity> checkOutputVoEntityList = new ArrayList<>();
        List<CheckOutputTempEntity> checkOutputTemplist = checkOutputTempService.queryList(params);
        List<WorkTypeEntity> workTypeList = workTypeService.queryList();
        for(WorkTypeEntity wtEntity : workTypeList) {
            CheckOutputVoEntity vo = new CheckOutputVoEntity();
            vo.setChecked(false);
            vo.settypeId(wtEntity.getId());
            vo.setTypeName(wtEntity.getTypeName());
            vo.setUnit(wtEntity.getUnit());
            vo.setUnitOutput(wtEntity.getUnitOutput());
            vo.setProjectTypeIdList(wtEntity.getProjectTypeIdList());
            vo.setprojectRatio(1f);
            vo.setworkLoad(0f);
            for (CheckOutputTempEntity entity : checkOutputTemplist) {
                if (entity.gettypeId() == wtEntity.getId()) {
                    vo.setChecked(true);
                    vo.setprojectRatio(entity.getprojectRatio());
                    vo.setworkLoad(entity.getworkLoad());
                    break;
                }
            }
            checkOutputVoEntityList.add(vo);
        }
        return R.ok().put("list", checkOutputVoEntityList);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("project:checkoutputtemp:info")
    public R info(@PathVariable("id") Long id){
		CheckOutputTempEntity checkOutputTemp = checkOutputTempService.selectById(id);

        return R.ok().put("checkOutputTemp", checkOutputTemp);
    }

    /**
     * 保存
     */
    @SysLog("保存预算明细")
    @RequestMapping("/save")
//    @RequiresPermissions("project:checkoutputtemp:save")
    public R save(@RequestBody CheckOutputTempVoEntity checkOutputTempVoEntity){
        if(StringUtil.isEmpty(checkOutputTempVoEntity.getprojectNo())){
            return  R.error("异常，保存项目编号为空！");
        }
        checkOutputTempService.deleteByMap(new MapUtils().put("project_no" ,checkOutputTempVoEntity.getprojectNo()));
        List<CheckOutputTempEntity> templist = new ArrayList<>();
        for(CheckOutputVoEntity checkvo : checkOutputTempVoEntity.gettempOutputlist()){
            if(checkvo.getChecked()) {
                CheckOutputTempEntity temp = new CheckOutputTempEntity();
                temp.setprojectNo(checkOutputTempVoEntity.getprojectNo());
                temp.settypeId(checkvo.gettypeId());
                temp.setprojectRatio(checkvo.getprojectRatio());
                temp.setworkLoad(checkvo.getworkLoad());
                templist.add(temp);
            }
        }
        if( templist.size() > 0) checkOutputTempService.insertBatch(templist);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("project:checkoutputtemp:update")
    public R update(@RequestBody CheckOutputTempEntity checkOutputTemp){
		checkOutputTempService.updateById(checkOutputTemp);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
		checkOutputTempService.deleteBatch(ids);

        return R.ok();
    }

}
