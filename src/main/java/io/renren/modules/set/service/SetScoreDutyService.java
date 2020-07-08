package io.renren.modules.set.service;

import com.baomidou.mybatisplus.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.set.entity.SetScoreDutyEntity;

import java.util.Map;
import java.util.List;

/**
 * 职务评分表
 *
 * @author ygg
 * @date 2020-06-11 09:29:42
 */
public interface SetScoreDutyService extends IService<SetScoreDutyEntity> {

    Long getMaxOrderNum();

    List<SetScoreDutyEntity> queryList(Map<String, Object> params);

    void updateSort(Map<String, Object> params);

    void save(SetScoreDutyEntity entity);

    void update(SetScoreDutyEntity entity);

    void deleteBatch(Long[] Ids);
}

