package io.renren.modules.set.service;

import com.baomidou.mybatisplus.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.set.entity.SetScoreKbiEntity;

import java.util.Map;
import java.util.List;

/**
 * 效能分计算方法表
 *
 * @author ygg
 * @date 2020-06-11 15:40:33
 */
public interface SetScoreKbiService extends IService<SetScoreKbiEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<SetScoreKbiEntity> queryList(Map<String, Object> params);

    void save(SetScoreKbiEntity entity);

    void update(SetScoreKbiEntity entity);

    void deleteBatch(Long[] Ids);
}

