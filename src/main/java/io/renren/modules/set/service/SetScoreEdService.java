package io.renren.modules.set.service;

import com.baomidou.mybatisplus.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.set.entity.SetScoreEdEntity;

import java.util.Map;
import java.util.List;

/**
 * 学历分对照表
 *
 * @author ygg
 * @date 2020-06-11 09:29:42
 */
public interface SetScoreEdService extends IService<SetScoreEdEntity> {

    Long getMaxOrderNum(Long cateId);

    List<SetScoreEdEntity> queryList(Map<String, Object> params);

    void updateSort(Map<String, Object> params);

    void save(SetScoreEdEntity entity);

    void update(SetScoreEdEntity entity);

    void deleteBatch(Long[] Ids);
}

