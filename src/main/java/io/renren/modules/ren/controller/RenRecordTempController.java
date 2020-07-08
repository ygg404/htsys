package io.renren.modules.ren.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import io.renren.common.annotation.SysLog;
import io.renren.modules.ren.entity.*;
import io.renren.modules.ren.service.*;
import io.renren.modules.ren.vo.RenRecordTempVoEntity;
import io.renren.modules.ren.vo.RenRecordVoEntity;
import io.renren.modules.sys.entity.SysUserEntity;
import org.apache.shiro.SecurityUtils;
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
 * 职工档案表（临时）
 *
 * @author ygg
 * @date 2020-02-15 11:39:17
 */
@RestController
@RequestMapping("ren/recordtemp")
public class RenRecordTempController {

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
     * 获取个人信息
     */
    @RequestMapping("/info/{userId}")
    public R info(@PathVariable("userId") Long userId){
        // userId为空 获取当前用户Id
        if(userId == null){
            userId =  ((SysUserEntity) SecurityUtils.getSubject().getPrincipal()).getUserId();
        }
		RenRecordTempVoEntity renRecordVo = renRecordTempVoService.selectById(userId);
        renRecordVo.setEdBackgroundList(renRecordEducationTempService.queryListByUserId(userId));
        renRecordVo.setWorkBackgroundList(renRecordWorkTempService.queryListByUserId(userId));
        return R.ok().put("renRecordVo", renRecordVo);
    }

    /**
     * 保存
     */
    @SysLog("提交个人档案")
    @RequestMapping("/save")
    public R save(@RequestBody RenRecordTempVoEntity renRecordVo){
        if(renRecordVo.getRenRecordTempEntity().getUserId() == null) {
            return R.error("用户Id为空！");
        }
        // 临时表
        RenRecordTempEntity temp = renRecordVo.getRenRecordTempEntity();
        for(RenRecordEducationTempEntity etemp : renRecordVo.getEdBackgroundList()){
            etemp.setuserId(temp.getUserId());
        }
        for (RenRecordWorkTempEntity wtemp : renRecordVo.getWorkBackgroundList()){
            wtemp.setuserId(temp.getUserId());
        }
        renRecordTempService.deleteById(temp.getUserId());
        renRecordTempService.insert(temp);
        //临时教育背景和工作经验 先根据用户id删除列表 再添加
        renRecordEducationTempService.deleteByUserId(temp.getUserId());
        renRecordWorkTempService.deleteByUserId(temp.getUserId());
        if( renRecordVo.getEdBackgroundList().size() != 0 ) renRecordEducationTempService.insertBatch(renRecordVo.getEdBackgroundList());
        if( renRecordVo.getWorkBackgroundList().size() != 0 ) renRecordWorkTempService.insertBatch(renRecordVo.getWorkBackgroundList());

        // 表示审核通过 临时表填充 正式表)
        if(temp.getIsAudit() == 1){
            RenRecordEntity entity = new RenRecordEntity();
            entity.setUserId(temp.getUserId());
            entity.setBirthday(temp.getBirthday());
            entity.setEducation(temp.getEducation());
            entity.setEducationType(temp.getEducationType());
            entity.setEducationTime(temp.getEducationTime());
            entity.setProRatio(temp.getProRatio());
            entity.setEmail(temp.getEmail());
            entity.setEntryTime(temp.getEntryTime());
            entity.setDutyId(temp.getDutyId());
            entity.setMobile(temp.getMobile());
            entity.setSex(temp.getSex());
            entity.setHouseType(temp.getHouseType());
            entity.setJobType(temp.getJobType());
            entity.setIdNo(temp.getIdNo());
            entity.setMaritalStatus(temp.getMaritalStatus());
            entity.setTitleLever(temp.getTitleLever());
            entity.setTitlePro(temp.getTitlePro());
            entity.setTrialPeriod(temp.getTrialPeriod());
            entity.setNativeProvince(temp.getNativeProvince());
            entity.setNativeCity(temp.getNativeCity());
            entity.setHeadImg(temp.getHeadImg());
            List<RenRecordEducationEntity> elist = new ArrayList<>();
            List<RenRecordWorkEntity> wlist = new ArrayList<>();
            for(RenRecordEducationTempEntity etemp : renRecordVo.getEdBackgroundList()){
                RenRecordEducationEntity en = new RenRecordEducationEntity();
                en.setuserId(temp.getUserId());
                en.setstartDate(etemp.getstartDate());
                en.setendDate(etemp.getendDate());
                en.seteducationBackground(etemp.geteducationBackground());
                en.seteducationSchool(etemp.geteducationSchool());
                en.setmajor(etemp.getmajor());
                elist.add(en);
            }
            for (RenRecordWorkTempEntity wtemp : renRecordVo.getWorkBackgroundList()){
                RenRecordWorkEntity en = new RenRecordWorkEntity();
                en.setuserId(temp.getUserId());
                en.setcompany(wtemp.getcompany());
                en.setstartDate(wtemp.getstartDate());
                en.setendDate(wtemp.getendDate());
                en.setjobPosition(wtemp.getjobPosition());
                en.setjobDescription(wtemp.getjobDescription());
                wlist.add(en);
            }
            renRecordService.deleteById(temp.getUserId());
            renRecordService.insert(entity);

            //正式教育背景和工作经验 先根据用户id删除列表 再添加
            renRecordEducationService.deleteByUserId(temp.getUserId());
            renRecordWorkService.deleteByUserId(temp.getUserId());
            if( elist.size() != 0 ) renRecordEducationService.insertBatch(elist);
            if(wlist.size() != 0 ) renRecordWorkService.insertBatch(wlist);
        }

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody RenRecordTempEntity renRecordTemp){
		renRecordTempService.updateById(renRecordTemp);

        return R.ok();
    }

    /**
     * 撤销个人编辑
     */
    @SysLog("撤销个人档案编辑")
    @RequestMapping("/deleteAudit")
    public R deleteAudit(){
        Long userId =  ((SysUserEntity) SecurityUtils.getSubject().getPrincipal()).getUserId();
        if(userId == null){
            return R.error("用户id为空!");
        }
        // 删除个人编辑临时表
        renRecordEducationTempService.deleteByUserId(userId);
        renRecordTempService.deleteById(userId);
        renRecordWorkTempService.deleteByUserId(userId);

        // 用正式表替换 临时表
        RenRecordEntity entity = renRecordService.selectById(userId);
        List<RenRecordEducationEntity> elist = renRecordEducationService.queryListByUserId(userId);
        List<RenRecordWorkEntity> wlist = renRecordWorkService.queryListByUserId(userId);
        if(entity != null){
            RenRecordTempEntity temp = new RenRecordTempEntity();
            temp.setUserId(entity.getUserId());
            temp.setBirthday(entity.getBirthday());
            temp.setEducation(entity.getEducation());
            temp.setEducationTime(entity.getEducationTime());
            temp.setEducationType(entity.getEducationType());
            temp.setEmail(entity.getEmail());
            temp.setEntryTime(entity.getEntryTime());
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
            temp.setDutyId(entity.getDutyId());
            temp.setIsAudit(1L);
            renRecordTempService.insert(temp);

            List<RenRecordEducationTempEntity> etempList = new ArrayList<>();
            List<RenRecordWorkTempEntity> wtempList = new ArrayList<>();
            for(RenRecordEducationEntity etemp : elist){
                RenRecordEducationTempEntity en = new RenRecordEducationTempEntity();
                en.setuserId(userId);
                en.setstartDate(etemp.getstartDate());
                en.setendDate(etemp.getendDate());
                en.seteducationBackground(etemp.geteducationBackground());
                en.seteducationSchool(etemp.geteducationSchool());
                en.setmajor(etemp.getmajor());
                etempList.add(en);
            }
            for (RenRecordWorkEntity wtemp : wlist){
                RenRecordWorkTempEntity en = new RenRecordWorkTempEntity();
                en.setuserId(wtemp.getuserId());
                en.setcompany(wtemp.getcompany());
                en.setstartDate(wtemp.getstartDate());
                en.setendDate(wtemp.getendDate());
                en.setjobPosition(wtemp.getjobPosition());
                en.setjobDescription(wtemp.getjobDescription());
                wtempList.add(en);
            }
            if( etempList.size() != 0 ) renRecordEducationTempService.insertBatch(etempList);
            if( wtempList.size() != 0 ) renRecordWorkTempService.insertBatch(wtempList);
        }

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("ren:renrecordtemp:delete")
    public R delete(@RequestBody Long[] userIds){
		renRecordTempService.deleteBatch(userIds);

        return R.ok();
    }

}
