package io.renren.modules.project.controller;

import java.io.File;
import java.util.*;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.google.gson.Gson;
import io.renren.common.annotation.SysLog;
import io.renren.common.utils.*;
import io.renren.modules.project.entity.ProjectScheduleEntity;
import io.renren.modules.project.entity.ProjectSituationEntity;
import io.renren.modules.project.service.ProjectScheduleService;
import io.renren.modules.project.service.ProjectSituationService;
import io.renren.modules.sys.entity.SysUserEntity;
import io.renren.modules.sys.service.SysUserService;
import jdk.nashorn.internal.runtime.regexp.joni.constants.EncloseType;
import org.apache.logging.log4j.util.Strings;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.renren.modules.project.entity.CheckQualityEntity;
import io.renren.modules.project.service.CheckQualityService;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * 质量检查表
 *
 * @author ygg
 * @date 2019-11-16 09:22:12
 */
@RestController
@RequestMapping("project/quality")
public class CheckQualityController {

    @Value("${spring.file.upReportFolder}")
    private String upReportFolder;

    @Autowired
    private CheckQualityService checkQualityService;
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private ProjectSituationService projectSituationService;
    @Autowired
    private ProjectScheduleService projectScheduleService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("project:quality:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = checkQualityService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("project:quality:info")
    public R info(@PathVariable("id") Long id){
		CheckQualityEntity checkQuality = checkQualityService.selectById(id);

        return R.ok().put("checkQuality", checkQuality);
    }

    /**
     * 通过 项目编号 获取 质检
     */
    @RequestMapping("/getInfo")
    public R getInfo(@RequestParam Map<String, Object> params){
        CheckQualityEntity checkQuality = checkQualityService.queryByMap(params);
        ProjectSituationEntity situationEntity = projectSituationService.queryByProjectNo(params.get("projectNo").toString());

        return R.ok().put("checkQuality", checkQuality).put("isCheck",situationEntity.getisCheck());
    }

    /**
     * 保存
     */
    @SysLog("保存质检临时")
    @RequestMapping("/saveTemp")
    @RequiresPermissions("project:quality:save")
    public R saveTemp(@RequestBody CheckQualityEntity checkQuality) {
        if (!Strings.isNotBlank(checkQuality.getprojectNo())) {
            return R.error("项目编号为空");
        }
        //查找 含有该项目编号的 质检
        CheckQualityEntity entity = checkQualityService.selectOne(
                new EntityWrapper<CheckQualityEntity>().eq("project_no", checkQuality.getprojectNo())
        );
        if (entity == null) {
            entity = new CheckQualityEntity();
            entity.setprojectNo(checkQuality.getprojectNo());
        }
        entity.setqualityReport(checkQuality.getqualityReport());

        checkQualityService.insertOrUpdate(entity);
        return R.ok();
    }
    /**
     * 保存
     */
    @SysLog("保存质检编辑")
    @RequestMapping("/save")
    @RequiresPermissions("project:quality:save")
    public R save(@RequestBody CheckQualityEntity checkQuality){
        if( checkQuality.getprojectNo() == null || checkQuality.getprojectNo() == ""){
            return  R.error("项目编号为空");
        }
        //查找 含有该项目编号的 质检
        CheckQualityEntity entity = checkQualityService.selectOne(
                new EntityWrapper<CheckQualityEntity>().eq("project_no", checkQuality.getprojectNo())
        );
        if(entity == null) {
            entity = new CheckQualityEntity();
            entity.setprojectNo(checkQuality.getprojectNo());
        }
        entity.setqualityScore(checkQuality.getqualityScore());
//        entity.setqualityNote(checkQuality.getqualityNote());
        entity.setqualityReport(checkQuality.getqualityReport());
        // 质检时间为空
        if(entity.getfinishDateTime() == null ){
            entity.setfinishDateTime(new Date());
            // 添加进度
            ProjectScheduleEntity scheduleEntity = new ProjectScheduleEntity();
            scheduleEntity.setprojectNo(checkQuality.getprojectNo());
            scheduleEntity.setscheduleRate(100);
            scheduleEntity.setscheduleNote("质检完成，已提交至产值核算。");
            scheduleEntity.setcreateTime(new Date());
            projectScheduleService.insert(scheduleEntity);
        }
        //结算时间为空
        if(entity.getcutOffTime() == null){
            entity.setcutOffTime(new Date());
        }
        // 获取当前用户Id
        Long createUserId =  ((SysUserEntity) SecurityUtils.getSubject().getPrincipal()).getUserId();
        if(entity.getqualityUseraccount() == null){
            SysUserEntity userEntity = sysUserService.selectById(createUserId);
            entity.setqualityUseraccount(userEntity.getUseraccount());
//            entity.setqualityConfirmaccount(userEntity.getUseraccount());
        }
		checkQualityService.insertOrUpdate(entity);

        //保存质检后 设置项目情况已经质检
        ProjectSituationEntity situationEntity = projectSituationService.queryByProjectNo(checkQuality.getprojectNo());
        situationEntity.setisCheck(1L);
        projectSituationService.insertOrUpdate(situationEntity);

        return R.ok();
    }

    /**
     * 保存结算时间
     */
    @SysLog("保存结算时间")
    @RequestMapping("/cutOffTimesave")
    public R cutOffTimesave(@RequestBody CheckQualityEntity checkQuality){
        if( checkQuality.getprojectNo() == null || checkQuality.getprojectNo() == ""){
            return  R.error("项目编号为空");
        }
        //查找 含有该项目编号的 质检
        CheckQualityEntity entity = checkQualityService.selectOne(
                new EntityWrapper<CheckQualityEntity>().eq("project_no", checkQuality.getprojectNo())
        );
        if(entity == null) {
            entity = new CheckQualityEntity();
            entity.setprojectNo(checkQuality.getprojectNo());
        }
        entity.setcutOffTime(checkQuality.getcutOffTime());
        checkQualityService.insertOrUpdate(entity);
        return R.ok();
    }

    /**
     * 修改
     */
    @SysLog("质量审核")
    @RequestMapping("/update")
    @RequiresPermissions("project:quality:update")
    public R update(@RequestBody CheckQualityEntity checkQuality){
        // 获取当前用户Id
        Long userId =  ((SysUserEntity) SecurityUtils.getSubject().getPrincipal()).getUserId();
        SysUserEntity userEntity = sysUserService.selectById(userId);
        // 设置质量审核人
        checkQuality.setqualityConfirmaccount(userEntity.getUseraccount());
		checkQualityService.updateById(checkQuality);

        //保存质量综述后 设置项目情况已经质审
        ProjectSituationEntity situationEntity = projectSituationService.queryByProjectNo(checkQuality.getprojectNo());
        situationEntity.setisQauth(1L);
        projectSituationService.insertOrUpdate(situationEntity);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("project:quality:delete")
    public R delete(@RequestBody Long[] ids){
		checkQualityService.deleteBatch(ids);

        return R.ok();
    }

    /**
     * 提交质检报告图片
     */
    @RequestMapping("/uploadEditorImg")
    public R uploadEditorImg(MultipartFile file, @RequestParam("projectNo") String projectNo) {
        String imgName = projectNo + ".png";
        try {
            String filePath = FileUtil.setFilePath(upReportFolder, imgName , true);
            File dest = new File(filePath);
            imgName = dest.getName();
            file.transferTo(dest);
        }catch (Exception ex){
            return R.error();
        }

        return R.ok().put("errno",0).put("imgName",imgName);
    }

}
