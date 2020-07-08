package io.renren.modules.project.service;

import com.baomidou.mybatisplus.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.project.entity.ProjectPlanEntity;

import java.util.Map;

/**
 * 项目安排表
 *
 * @author ygg
 * @date 2019-11-01 16:46:10
 */
public interface ProjectPlanService extends IService<ProjectPlanEntity> {

    PageUtils queryPage(Map<String, Object> params);

    ProjectPlanEntity queryByProjectNo(String projectNo);

    void save(ProjectPlanEntity entity);

    void update(ProjectPlanEntity entity);

    void deleteBatch(Long[] roleIds);
}

