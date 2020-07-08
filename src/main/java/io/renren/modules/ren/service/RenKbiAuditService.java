package io.renren.modules.ren.service;

import com.baomidou.mybatisplus.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.ren.entity.RenKbiAuditEntity;

import java.util.Map;
import java.util.List;

/**
 * 效能分审定表
 *
 * @author ygg
 * @date 2020-07-03 16:11:22
 */
public interface RenKbiAuditService extends IService<RenKbiAuditEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<RenKbiAuditEntity> queryList(Map<String, Object> params);

    void save(RenKbiAuditEntity entity);

    void update(RenKbiAuditEntity entity);

    void deleteBatch(Long[] Ids);
}

