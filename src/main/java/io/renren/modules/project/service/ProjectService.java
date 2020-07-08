package io.renren.modules.project.service;

import com.baomidou.mybatisplus.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.project.entity.ProjectEntity;
import io.renren.modules.set.entity.ShortTypeEntity;

import java.util.List;
import java.util.Map;

/**
 * 项目列表
 *
 * @author ygg
 * @date 2019-10-31 11:36:02
 */
public interface ProjectService extends IService<ProjectEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<ProjectEntity> queryList(Map<String, Object> params);

    void save(ProjectEntity entity);

    void update(ProjectEntity entity);

    void deleteBatch(Long id);

    String getProjectNo(String contractNo);
}

