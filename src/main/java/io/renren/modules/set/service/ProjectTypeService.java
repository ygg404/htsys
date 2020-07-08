package io.renren.modules.set.service;

import com.baomidou.mybatisplus.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.project.entity.ProjectEntity;
import io.renren.modules.set.entity.ProjectTypeEntity;
import io.renren.modules.set.entity.WorkProjectTypeEntity;
import io.renren.modules.set.entity.WorkTypeEntity;

import java.util.List;
import java.util.Map;

/**
 * 项目类型表
 *
 * @author ygg
 * @date 2019-10-29 10:39:04
 */
public interface ProjectTypeService extends IService<ProjectTypeEntity> {

    PageUtils queryPage(Map<String, Object> params);

    void save(ProjectTypeEntity entity);

    void update(ProjectTypeEntity entity);

    void deleteBatch(Long[] roleIds);

    List<ProjectTypeEntity> ToSelectqueryList();

    List<ProjectTypeEntity> queryList(Map<String, Object> params);

    List<ProjectTypeEntity> selectBatch(Long[] ids);


}

