package io.renren.modules.set.service;

import com.baomidou.mybatisplus.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.set.entity.SetScoreHouseEntity;

import java.util.Map;
import java.util.List;

/**
 * 职级及其房补表
 *
 * @author ygg
 * @date 2020-06-11 09:29:42
 */
public interface SetScoreHouseService extends IService<SetScoreHouseEntity> {

    Long getMaxOrderNum();

    List<SetScoreHouseEntity> queryList(Map<String, Object> params);

    void updateSort(Map<String, Object> params);

    void save(SetScoreHouseEntity entity);

    void update(SetScoreHouseEntity entity);

    void deleteBatch(Long[] Ids);
}

