package io.renren.modules.set.service;

import com.baomidou.mybatisplus.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.set.entity.SetScoreKbiTitleEntity;

import java.util.Map;
import java.util.List;

/**
 * 职称效能分计算方法表
 *
 * @author ygg
 * @date 2020-06-29 14:17:35
 */
public interface SetScoreKbiTitleService extends IService<SetScoreKbiTitleEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<SetScoreKbiTitleEntity> queryList(Map<String, Object> params);

    void save(SetScoreKbiTitleEntity entity);

    void update(SetScoreKbiTitleEntity entity);

    void deleteBatch(Long[] Ids);
}

