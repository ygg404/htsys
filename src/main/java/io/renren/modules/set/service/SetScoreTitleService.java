package io.renren.modules.set.service;

import com.baomidou.mybatisplus.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.set.entity.SetScoreTitleEntity;

import java.util.Map;
import java.util.List;

/**
 * 职称基准分表
 *
 * @author ygg
 * @date 2020-06-11 09:29:42
 */
public interface SetScoreTitleService extends IService<SetScoreTitleEntity> {

    Long getMaxOrderNum();

    List<SetScoreTitleEntity> queryList(Map<String, Object> params);

    void updateSort(Map<String, Object> params);

    void save(SetScoreTitleEntity entity);

    void update(SetScoreTitleEntity entity);

    void deleteBatch(Long[] Ids);
}

