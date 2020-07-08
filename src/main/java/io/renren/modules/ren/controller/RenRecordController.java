package io.renren.modules.ren.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.alibaba.druid.sql.visitor.functions.If;
import io.renren.common.annotation.SysLog;
import io.renren.modules.ren.entity.*;
import io.renren.modules.ren.service.*;
import io.renren.modules.ren.vo.RenRecordVoEntity;
import org.apache.logging.log4j.util.Strings;
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
 * 职工档案表（正式）
 *
 * @author ygg
 * @date 2020-02-11 11:50:15
 */
@RestController
@RequestMapping("ren/record")
public class RenRecordController {

    @Autowired
    private RenRecordVoService renRecordVoService;
    @Autowired
    private RenRecordTempVoService renRecordTempVoService;
    @Autowired
    private RenRecordTempService renRecordTempService;
    @Autowired
    private RenRecordEducationTempService renRecordEducationTempService;
    @Autowired
    private RenRecordWorkTempService renRecordWorkTempService;
    @Autowired
    private RenRecordService renRecordService;
    @Autowired
    private RenRecordEducationService renRecordEducationService;
    @Autowired
    private RenRecordWorkService renRecordWorkService;
    /**
     * 分页列表
     */
    @RequestMapping("/page")
    @RequiresPermissions("ren:record:page")
    public R page(@RequestParam Map<String, Object> params){
        PageUtils page = renRecordVoService.getRenRecordVoPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{userId}")
    @RequiresPermissions("ren:record:info")
    public R info(@PathVariable("userId") Long userId){
		RenRecordVoEntity renRecordVo = renRecordVoService.selectById(userId);
        renRecordVo.setEdBackgroundList(renRecordEducationService.queryListByUserId(userId));
		renRecordVo.setWorkBackgroundList(renRecordWorkService.queryListByUserId(userId));
        return R.ok().put("renRecordVo", renRecordVo);
    }

    /**
     * 保存
     */
    @SysLog("保存个人档案")
    @RequestMapping("/save")
    @RequiresPermissions("ren:record:save")
    public R save(@RequestBody RenRecordVoEntity renRecordVo){
//		renRecordService.save(renRecord);
        if(renRecordVo.getRenRecordEntity().getUserId() == null){
            return R.error("保存失败，用户ID为空。");
        }
        // 正式表
        RenRecordEntity entity = renRecordVo.getRenRecordEntity();
        renRecordService.deleteById(entity.getUserId());
        renRecordService.insert(entity);
        //教育背景和工作经验 先根据用户id删除列表 再添加
        renRecordEducationService.deleteByUserId(entity.getUserId());
        renRecordWorkService.deleteByUserId(entity.getUserId());
        if( renRecordVo.getEdBackgroundList().size() != 0 ) renRecordEducationService.insertBatch(renRecordVo.getEdBackgroundList());
        if( renRecordVo.getWorkBackgroundList().size() != 0 ) renRecordWorkService.insertBatch(renRecordVo.getWorkBackgroundList());

        // 临时表
        RenRecordTempEntity temp = new RenRecordTempEntity();
        temp.setUserId(entity.getUserId());
        temp.setBirthday(entity.getBirthday());
        temp.setEducation(entity.getEducation());
        temp.setEmail(entity.getEmail());
        temp.setEntryTime(entity.getEntryTime());
        temp.setEducationType(entity.getEducationType());
        temp.setEducationTime(entity.getEducationTime());
        temp.setProRatio(entity.getProRatio());
        temp.setDutyId(entity.getDutyId());
        temp.setMobile(entity.getMobile());
        temp.setSex(entity.getSex());
        temp.setHouseType(entity.getHouseType());
        temp.setJobType(entity.getJobType());
        temp.setIdNo(entity.getIdNo());
        temp.setMaritalStatus(entity.getMaritalStatus());
        temp.setTitleLever(entity.getTitleLever());
        temp.setTitlePro(entity.getTitlePro());
        temp.setTrialPeriod(entity.getTrialPeriod());
        temp.setNativeProvince(entity.getNativeProvince());
        temp.setNativeCity(entity.getNativeCity());
        temp.setHeadImg(entity.getHeadImg());
        temp.setIsAudit(1L); // 管理员自己编辑表示已经审核
        renRecordTempService.deleteById(entity.getUserId());
        renRecordTempService.insert(temp);
        //临时 教育背景和工作经验 先根据用户id删除列表 再添加
        renRecordEducationTempService.deleteByUserId(entity.getUserId());
        renRecordWorkTempService.deleteByUserId(entity.getUserId());
        List<RenRecordEducationTempEntity> elist = new ArrayList<>();
        List<RenRecordWorkTempEntity> wlist = new ArrayList<>();
        for(RenRecordEducationEntity etemp : renRecordVo.getEdBackgroundList()){
            RenRecordEducationTempEntity en = new RenRecordEducationTempEntity();
            en.setuserId(entity.getUserId());
            en.setstartDate(etemp.getstartDate());
            en.setendDate(etemp.getendDate());
            en.seteducationBackground(etemp.geteducationBackground());
            en.seteducationSchool(etemp.geteducationSchool());
            en.setmajor(etemp.getmajor());
            elist.add(en);
        }
        for (RenRecordWorkEntity wtemp : renRecordVo.getWorkBackgroundList()){
            RenRecordWorkTempEntity en = new RenRecordWorkTempEntity();
            en.setuserId(wtemp.getuserId());
            en.setcompany(wtemp.getcompany());
            en.setstartDate(wtemp.getstartDate());
            en.setendDate(wtemp.getendDate());
            en.setjobPosition(wtemp.getjobPosition());
            en.setjobDescription(wtemp.getjobDescription());
            wlist.add(en);
        }
        if( elist.size() != 0 ) renRecordEducationTempService.insertBatch(elist);
        if( wlist.size() != 0 ) renRecordWorkTempService.insertBatch(wlist);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("ren:record:update")
    public R update(@RequestBody RenRecordEntity renRecord){
		renRecordService.updateById(renRecord);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("ren:record:delete")
    public R delete(@RequestBody Long[] userIds){
		renRecordService.deleteBatch(userIds);

        return R.ok();
    }

}
