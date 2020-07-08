package io.renren.modules.project.service;

import com.baomidou.mybatisplus.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.project.entity.ProjectWorkEntity;

import java.util.Map;

/**
 * 项目作业表
 *
 * @author ygg
 * @date 2019-11-01 16:46:10
 */
public interface ProjectWorkService extends IService<ProjectWorkEntity> {

    PageUtils queryPage(Map<String, Object> params);

    ProjectWorkEntity queryByProjectNo(String projectNo);

    void save(ProjectWorkEntity entity);

    void update(ProjectWorkEntity entity);

    void deleteBatch(Long[] roleIds);
}

