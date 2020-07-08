package io.renren.modules.set.service;

import com.baomidou.mybatisplus.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.set.entity.SetScoreKbiDutyEntity;

import java.util.Map;
import java.util.List;

/**
 * 职务效能分计算方法表
 *
 * @author ygg
 * @date 2020-06-29 14:17:35
 */
public interface SetScoreKbiDutyService extends IService<SetScoreKbiDutyEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<SetScoreKbiDutyEntity> queryList(Map<String, Object> params);

    void save(SetScoreKbiDutyEntity entity);

    void update(SetScoreKbiDutyEntity entity);

    void deleteBatch(Long[] Ids);
}

