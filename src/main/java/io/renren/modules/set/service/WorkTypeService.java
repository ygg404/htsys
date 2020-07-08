package io.renren.modules.set.service;

import com.baomidou.mybatisplus.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.project.entity.ProjectEntity;
import io.renren.modules.set.entity.WorkProjectTypeEntity;
import io.renren.modules.set.entity.WorkTypeEntity;

import java.util.List;
import java.util.Map;

/**
 * 作业类型表
 *
 * @author ygg
 * @date 2019-10-29 11:52:46
 */
public interface WorkTypeService extends IService<WorkTypeEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<WorkTypeEntity> queryListByPtypeId(Long projectTypeId);

    List<WorkTypeEntity> queryList();

    void save(WorkTypeEntity entity);

    void setOrderNum(Map<String, Object> params);

    void update(WorkTypeEntity entity);

    void deleteBatch(Long[] roleIds);

    Long ReturnIDAfterSave(WorkTypeEntity entity);
}

