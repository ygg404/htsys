package io.renren.modules.project.service;

import com.baomidou.mybatisplus.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.project.entity.ProjectGroupEntity;
import io.renren.modules.project.vo.ProjectGroupVoEntity;
import io.renren.modules.project.vo.WorkGroupVoEntity;

import java.util.List;
import java.util.Map;

/**
 * 项目安排分组
 *
 * @author ygg
 * @date 2019-11-01 16:46:10
 */
public interface ProjectGroupService extends IService<ProjectGroupEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<ProjectGroupVoEntity> getListByProjectNo(Map<String, Object> params);

    List<WorkGroupVoEntity> getProjectDataCoe(Map<String, Object> params);

    List<ProjectGroupVoEntity> getChartCollect(Map<String, Object> params);

    void save(ProjectGroupEntity entity);

    void update(ProjectGroupEntity entity);

    void deleteBatch(Long[] roleIds);
}

