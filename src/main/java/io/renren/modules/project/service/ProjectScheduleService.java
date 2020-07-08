package io.renren.modules.project.service;

import com.baomidou.mybatisplus.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.project.entity.ProjectScheduleEntity;

import java.util.List;
import java.util.Map;

/**
 * 进度表
 *
 * @author ygg
 * @date 2019-11-01 16:46:10
 */
public interface ProjectScheduleService extends IService<ProjectScheduleEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<ProjectScheduleEntity> queryList(Map<String, Object> params);

    void save(ProjectScheduleEntity entity);

    void update(ProjectScheduleEntity entity);

    void deleteBatch(Long[] roleIds);
}

