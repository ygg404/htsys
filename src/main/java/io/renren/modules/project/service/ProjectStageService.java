package io.renren.modules.project.service;

import com.baomidou.mybatisplus.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.project.entity.ProjectStageEntity;

import java.util.Map;

/**
 * 项目阶段表
 *
 * @author ygg
 * @date 2019-11-01 16:46:10
 */
public interface ProjectStageService extends IService<ProjectStageEntity> {

    PageUtils queryPage(Map<String, Object> params);

    void save(ProjectStageEntity entity);

    void update(ProjectStageEntity entity);

    void deleteBatch(Long[] roleIds);
}

