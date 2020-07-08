package io.renren.modules.set.service;

import com.baomidou.mybatisplus.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.set.entity.WorkGroupEntity;

import java.util.Map;
import java.util.List;

/**
 * 工作组表
 *
 * @author ygg
 * @date 2020-06-02 09:38:14
 */
public interface WorkGroupService extends IService<WorkGroupEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<WorkGroupEntity> queryList(Map<String, Object> params);

    void save(WorkGroupEntity entity);

    void update(WorkGroupEntity entity);

    void setSort(WorkGroupEntity entity);

    void deleteBatch(Long[] Ids);
}

