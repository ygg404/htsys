package io.renren.modules.project.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.plugins.Page;
import io.renren.common.annotation.SysLog;
import io.renren.modules.project.entity.ProjectContractEntity;
import io.renren.modules.project.service.*;
import io.renren.modules.project.vo.RecycleVoEntity;
import net.bytebuddy.asm.Advice;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.util.StringUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.renren.modules.project.entity.ProjectEntity;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;


/**
 * 项目回收站列表
 *
 * @author ygg
 * @date 2019-10-31 11:36:02
 */
@RestController
@RequestMapping("project/recycle")
public class RecycleController {
    @Autowired
    private RecycleService recycleService;

    @Autowired
    private ProjectService projectService;

    @Autowired
    private ProjectPlanService projectPlanService;

    @Autowired
    private ProjectScheduleService projectScheduleService;

    @Autowired
    private ProjectWorkService projectWorkService;

    @Autowired
    private ProjectGroupService projectGroupService;

    @Autowired
    private CheckOutputService checkOutputService;

    @Autowired
    private CheckQualityService checkQualityService;

    @Autowired
    private ProjectSituationService projectSituationService;

    @Autowired
    private QualityScoreService qualityScoreService;

    @Autowired
    private ProjectContractService projectContractService;

    @Autowired
    private CheckOutputTempService checkOutputTempService;

    @Autowired
    private CheckOutputRemarkService checkOutputRemarkService;

    @Autowired
    private BackWorkService backWorkService;

    @Autowired
    private ProjectBillService projectBillService;

    @Autowired
    private ProjectMtaskService projectMtaskService;

    /**
     * 列表
     */
//    @SysLog("查看项目回收站列表")
    @RequestMapping("/list")
    @RequiresPermissions("project:recycle:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = recycleService.getRecyclePage(params);

        return R.ok().put("page", page);
    }



    /**
     * 恢复项目
     */
    @SysLog("恢复项目")
    @RequestMapping("/update")
    @RequiresPermissions("project:recycle:restore")
    public R update(@RequestBody ProjectEntity project){
        ProjectContractEntity contractEntity = projectContractService.getByContractNo(project.getcontractNo());
        if( contractEntity == null){
            return R.error("该项目所关联的合同不存在了，无法恢复！");
        }

        project.setpStage("1");
        projectService.updateById(project);

        return R.ok();
    }

    /**
     * 删除
     */
    @SysLog("彻底删除回收站项目")
    @RequestMapping("/delete")
    @RequiresPermissions("project:recycle:delete")
    public R delete(@RequestBody Map<String, Object> params){
        if(!StringUtils.isNotBlank((String) params.get("projectNo"))){
            return R.error("项目编号为空！");
        }
        //根据项目编号进行删除
        Map<String , Object> parm = new HashMap<>();
        parm.put("project_no" , params.get("projectNo"));
        //项目 进度 工作安排 工作组 产值 质检 项目情况 成果清单 返修记录相关内容删除
        projectService.deleteByMap(parm);
        projectPlanService.deleteByMap(parm);
        projectScheduleService.deleteByMap(parm);
        projectWorkService.deleteByMap(parm);
        projectGroupService.deleteByMap(parm);
        checkOutputService.deleteByMap(parm);
        checkQualityService.deleteByMap(parm);
        projectSituationService.deleteByMap(parm);
        qualityScoreService.deleteByMap(parm);
        checkOutputTempService.deleteByMap(parm);
        backWorkService.deleteByMap(parm);
        checkOutputRemarkService.deleteByMap(parm);
        projectBillService.deleteByMap(parm);
        projectMtaskService.deleteByMap(parm);
        
        return R.ok();
    }

}