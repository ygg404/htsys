package io.renren.modules.project.controller;

import io.renren.common.annotation.SysLog;
import io.renren.common.utils.R;
import io.renren.modules.project.service.ProjectGroupService;
import io.renren.modules.project.vo.ChartOutputVoEntity;
import io.renren.modules.project.vo.ProjectGroupVoEntity;
import io.renren.modules.project.vo.WorkGroupVoEntity;

import io.renren.modules.set.entity.WorkGroupEntity;
import io.renren.modules.set.service.WorkGroupService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 产值汇总统计表
 *
 * @author ygg
 * @date 2019-11-18 15:04:00
 */
@RestController
@RequestMapping("project/chartcollect")
public class ChartCollectController {

    @Autowired
    private WorkGroupService workGroupService;

    @Autowired
    private ProjectGroupService projectGroupService;

    /**
     * 列表
     */
    @SysLog("查看产值汇总统计表")
    @RequestMapping("/list")
    @RequiresPermissions("project:chartcollect")
    public R list(@RequestParam Map<String, Object> params){
        List<WorkGroupEntity> groupList = workGroupService.queryList(params);
        List<ProjectGroupVoEntity> volist = projectGroupService.getChartCollect(params);

        List<WorkGroupVoEntity> wgVoList = new ArrayList<>();
        for(WorkGroupEntity wgEntity : groupList){
            Integer projectNum = 0; // 项目个数
            Float output = 0f; // 产值
            //遍历各个组对应的项目
            for(ProjectGroupVoEntity groupVoEntity : volist){
                if(groupVoEntity.getgroupId() == wgEntity.getId()){
                    projectNum ++;
                    // 有实际产值则算实际产值
                    if(groupVoEntity.getprojectActuallyOutput() != null ){
                        output += groupVoEntity.getprojectActuallyOutput();
                    } else {
                        output += groupVoEntity.getprojectOutput();
                    }
                }
            }
            WorkGroupVoEntity workGroupVoEntity = new WorkGroupVoEntity();
            workGroupVoEntity.setgroupName(wgEntity.getName());
            workGroupVoEntity.setprojectNum(projectNum);
            workGroupVoEntity.setOutput(output);
            wgVoList.add(workGroupVoEntity);
        }

        return R.ok().put("list", wgVoList);
    }
}
