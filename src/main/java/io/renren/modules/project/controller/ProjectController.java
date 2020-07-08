package io.renren.modules.project.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import io.renren.common.annotation.SysLog;
import io.renren.modules.app.service.UserService;
import io.renren.modules.project.entity.ProjectSituationEntity;
import io.renren.modules.project.service.ProjectGroupService;
import io.renren.modules.project.service.ProjectSituationService;
import io.renren.modules.sys.entity.SysUserEntity;
import io.renren.modules.sys.service.SysUserService;
import io.renren.modules.sys.vo.UserVoEntity;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.renren.modules.project.entity.ProjectEntity;
import io.renren.modules.project.service.ProjectService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * 项目列表
 *
 * @author ygg
 * @date 2019-10-31 11:36:02
 */
@RestController
@RequestMapping("project/project")
public class ProjectController {
    @Autowired
    private ProjectService projectService;

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private ProjectSituationService projectSituationService;

    /**
     * 列表
     */
    @SysLog("查看项目")
    @RequestMapping("/list")
    @RequiresPermissions("project:project:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = projectService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("project:project:info")
    public R info(@PathVariable("id") Integer id){
		ProjectEntity project = projectService.selectById(id);

        return R.ok().put("project", project);
    }

    /**
     * 通过项目编号获取信息
     */
    @RequestMapping("/getByProjectNo/{projectNo}")
    public R getByProjectNo(@PathVariable("projectNo") String projectNo){
        ProjectEntity project = projectService.selectOne(
                new EntityWrapper<ProjectEntity>().eq("project_no",projectNo)
        );

        return R.ok().put("project", project);
    }


    /**
     * 添加项目 获取项目编号
     */
    @RequestMapping("/getProjectNo")
    public R getProjectNo(@RequestParam("contractNo") String contractNo){
        String projectNo = projectService.getProjectNo(contractNo);

        return R.ok().put("projectNo", projectNo);
    }

    /**
     * 获取 生产负责人列表
     * @return
     */
    @RequestMapping("/getProduceList")
    public R getUserList() {
        List<UserVoEntity> list = sysUserService.getUserList(3L);
        return R.ok().put("list", list);
    }

    /**
     * 保存
     */
    @SysLog("保存项目")
    @RequestMapping("/save")
    @RequiresPermissions("project:project:save")
    public R save(@RequestBody ProjectEntity project){
		projectService.save(project);

        return R.ok();
    }

    /**
     * 修改
     */
    @SysLog("修改项目")
    @RequestMapping("/update")
    @RequiresPermissions("project:project:update")
    public R update(@RequestBody ProjectEntity project){
		projectService.updateById(project);

        return R.ok();
    }

    /**
     * 提交审定
     */
    @SysLog("提交审定")
    @RequestMapping("/authorize")
    @RequiresPermissions("project:authorize:update")
    public R authorize(@RequestBody ProjectEntity project){
        Long userId =  ((SysUserEntity) SecurityUtils.getSubject().getPrincipal()).getUserId();
        SysUserEntity userEntity = sysUserService.selectById(userId);

        //提交审定 设置项目情况已经审定
        ProjectSituationEntity situationEntity = projectSituationService.queryByProjectNo(project.getprojectNo());

        if(project.getexamineNote() == null || project.getexamineNote().trim() == ""){
            project.setexamUserId(null);
            project.setexamUsername("");
            situationEntity.setisAuthorize(0L);
        } else {
            project.setexamUserId(userId);
            project.setexamUsername(userEntity.getUsername());
            situationEntity.setisAuthorize(1L);
        }
        projectService.updateById(project);
        projectSituationService.insertOrUpdate(situationEntity);

        return R.ok();
    }

    /**
     * 删除
     */
    @SysLog("删除项目")
    @RequestMapping("/delete")
    @RequiresPermissions("project:project:delete")
    public R delete(@RequestBody Long id){
		projectService.deleteBatch(id);

        return R.ok();
    }

}
