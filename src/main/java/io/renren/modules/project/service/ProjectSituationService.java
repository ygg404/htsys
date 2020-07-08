package io.renren.modules.project.service;

import com.baomidou.mybatisplus.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.project.entity.ProjectSituationEntity;


import java.util.Map;

/**
 * 项目情况表
 *
 * @author ygg
 * @date 2019-11-26 09:06:04
 */
public interface ProjectSituationService extends IService<ProjectSituationEntity> {

    PageUtils queryPage(Map<String, Object> params);

    ProjectSituationEntity queryByProjectNo(String projectNo);

    void save(ProjectSituationEntity entity);

    void update(ProjectSituationEntity entity);

    void deleteBatch(Long[] roleIds);
}

