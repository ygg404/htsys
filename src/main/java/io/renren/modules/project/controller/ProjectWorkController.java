package io.renren.modules.project.controller;

import java.util.Arrays;
import java.util.Date;
import java.util.Map;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import io.renren.common.annotation.SysLog;
import io.renren.common.utils.StringUtil;
import io.renren.modules.project.entity.ProjectSituationEntity;
import io.renren.modules.project.service.ProjectSituationService;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.renren.modules.project.entity.ProjectWorkEntity;
import io.renren.modules.project.service.ProjectWorkService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * 项目作业表
 *
 * @author ygg
 * @date 2019-11-01 16:46:10
 */
@RestController
@RequestMapping("project/work")
public class ProjectWorkController {
    @Autowired
    private ProjectWorkService projectWorkService;
    @Autowired
    private ProjectSituationService projectSituationService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("project:work:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = projectWorkService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("project:work:info")
    public R info(@PathVariable("id") Integer id){
		ProjectWorkEntity projectWork = projectWorkService.selectById(id);

        return R.ok().put("projectWork", projectWork);
    }

    /**
     * 根据项目编号获取项目工作
     */
    @RequestMapping("/getInfoByProjectNo/{projectNo}")
    public R getInfoByProjectNo(@PathVariable("projectNo") String projectNo){
        ProjectWorkEntity projectWork = projectWorkService.queryByProjectNo(projectNo);

        return R.ok().put("projectWork", projectWork);
    }

    /**
     * 保存
     */
    @SysLog("保存项目作业")
    @RequestMapping("/save")
    @RequiresPermissions("project:work:save")
    public R save(@RequestBody ProjectWorkEntity projectWork){
        if( !StringUtils.isNotBlank(projectWork.getprojectNo()) ){
            return R.error("项目编号无效");
        }
        ProjectWorkEntity entity = projectWorkService.selectOne(new EntityWrapper<ProjectWorkEntity>().eq("project_no",projectWork.getprojectNo()));
        if( entity != null){
            return R.error("该项目已经存在，请刷新后重试！");
        }
        entity.setfinishDateTime(new Date());
		projectWorkService.save(projectWork);

        //保存作业后 设置项目情况已经作业
        ProjectSituationEntity situationEntity = projectSituationService.queryByProjectNo(projectWork.getprojectNo());
        situationEntity.setisWork(1L);
        projectSituationService.insertOrUpdate(situationEntity);

        return R.ok();
    }

    /**
     * 修改
     */
    @SysLog("修改项目作业")
    @RequestMapping("/update")
    @RequiresPermissions("project:work:update")
    public R update(@RequestBody ProjectWorkEntity projectWork){
        if( projectWork.getfinishDateTime() == null ) projectWork.setfinishDateTime(new Date());
		projectWorkService.updateById(projectWork);

        //保存作业后 设置项目情况已经作业
        ProjectSituationEntity situationEntity = projectSituationService.queryByProjectNo(projectWork.getprojectNo());
        situationEntity.setisWork(1L);
        projectSituationService.insertOrUpdate(situationEntity);

        return R.ok();
    }

    /**
     * 修改（项目状态）
     */
    @SysLog("修改项目状态")
    @RequestMapping("/updateStatus")
    @RequiresPermissions("project:work:update")
    public R insertOrUpdate(@RequestBody ProjectWorkEntity projectWork){
        if(projectWork.getprojectNo() != null)
        {
            ProjectWorkEntity entity = projectWorkService.queryByProjectNo(projectWork.getprojectNo());
            if(entity == null){
                return R.error("项目未安排开工！");
            }else{
                projectWork.setId(entity.getId());
                // 暂停项目
                if(projectWork.getprojectStatus() == 1){
                    projectWork.setsuspendTime(new Date());
                } else {
                    // 暂停后启动项目
                    long diff = new Date().getTime() - entity.getsuspendTime().getTime();
                    Long days = diff / (1000 * 60 * 60 * 24);
                    if( entity.getsuspendDay() != null) days += entity.getsuspendDay();
                    projectWork.setsuspendDay(days);
                }
                projectWorkService.updateById(projectWork);
            }
        }

        return R.ok();
    }

    /**
     * 删除
     */
    @SysLog("删除项目作业")
    @RequestMapping("/delete")
    @RequiresPermissions("project:work:delete")
    public R delete(@RequestBody Long[] ids){
		projectWorkService.deleteBatch(ids);

        return R.ok();
    }

}
