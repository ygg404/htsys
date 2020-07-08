package io.renren.modules.ren.service;

import com.baomidou.mybatisplus.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.ren.entity.RenKbiPersonEntity;

import java.util.Map;
import java.util.List;

/**
 * 效能分年度参评人员
 *
 * @author ygg
 * @date 2020-07-04 10:33:57
 */
public interface RenKbiPersonService extends IService<RenKbiPersonEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<RenKbiPersonEntity> queryList(Map<String, Object> params);

    void save(RenKbiPersonEntity entity);

    void update(RenKbiPersonEntity entity);

    void deleteBatch(Long[] Ids);
}

