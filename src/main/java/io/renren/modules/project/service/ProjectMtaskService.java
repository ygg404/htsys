package io.renren.modules.project.service;

import com.baomidou.mybatisplus.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.project.entity.ProjectMtaskEntity;

import java.util.Map;
import java.util.List;

/**
 * 作业任务分工表
 *
 * @author ygg
 * @date 2020-12-29 15:41:51
 */
public interface ProjectMtaskService extends IService<ProjectMtaskEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<ProjectMtaskEntity> queryList(Map<String, Object> params);

    ProjectMtaskEntity queryByNo(Map<String, Object> params);

    void save(ProjectMtaskEntity entity);

    void update(ProjectMtaskEntity entity);

    void deleteByProNo(String projectNo);

    void deleteBatch(Long[] Ids);
}

