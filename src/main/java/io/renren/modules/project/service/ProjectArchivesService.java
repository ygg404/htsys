package io.renren.modules.project.service;

import com.baomidou.mybatisplus.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.project.entity.ProjectArchivesEntity;

import java.util.Map;
import java.util.List;

/**
 * 项目成果表
 *
 * @author ygg
 * @date 2020-09-18 10:56:51
 */
public interface ProjectArchivesService extends IService<ProjectArchivesEntity> {

    List<ProjectArchivesEntity> queryList(Map<String, Object> params);

    void save(ProjectArchivesEntity entity);

    void update(ProjectArchivesEntity entity);

    void deleteBatch(Long[] Ids);
}

