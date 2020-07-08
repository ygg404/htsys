package io.renren.modules.project.controller;

import io.renren.common.utils.R;
import io.renren.modules.project.service.ProjectInfoService;
import io.renren.modules.project.vo.ProjectInfoVoEntity;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 项目基本信息
 *
 * @author ygg
 * @date 2019-11-01 16:46:10
 */
@RestController
@RequestMapping("project/projectInfo")
public class ProjectInfoController {
    @Autowired
    public ProjectInfoService projectInfoService;

    /**
     * 项目基本信息实体类
     */
    @RequestMapping("/info/{projectNo}")
    public R list(@PathVariable("projectNo") String projectNo) {
        ProjectInfoVoEntity entity = projectInfoService.getInfoByProjectNo(projectNo);

        return R.ok().put("projectInfo", entity);
    }

    /**
     * 项目打印信息实体类
     */
    @RequestMapping("/print/{projectNo}")
    public R print(@PathVariable("projectNo") String projectNo) {
        ProjectInfoVoEntity entity = projectInfoService.getPrintByProjectNo(projectNo);

        return R.ok().put("projectInfo", entity);
    }
}
