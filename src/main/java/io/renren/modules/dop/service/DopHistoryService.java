package io.renren.modules.dop.service;

import com.baomidou.mybatisplus.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.dop.entity.DopHistoryEntity;

import java.util.Map;
import java.util.List;

/**
 * 仪器租赁情况表
 *
 * @author ygg
 * @date 2020-09-28 10:38:16
 */
public interface DopHistoryService extends IService<DopHistoryEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<DopHistoryEntity> queryList(Map<String, Object> params);

    void save(DopHistoryEntity entity);

    void update(DopHistoryEntity entity);

    void deleteBatch(Long[] Ids);
}

