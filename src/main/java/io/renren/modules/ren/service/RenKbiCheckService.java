package io.renren.modules.ren.service;

import com.baomidou.mybatisplus.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.ren.entity.RenKbiCheckEntity;

import java.util.Map;
import java.util.List;

/**
 * 效能分年度考核人员
 *
 * @author ygg
 * @date 2020-08-26 09:38:17
 */
public interface RenKbiCheckService extends IService<RenKbiCheckEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<RenKbiCheckEntity> queryList(Map<String, Object> params);

    RenKbiCheckEntity queryByParams(Map<String, Object> params);

    void save(RenKbiCheckEntity entity);

    void update(RenKbiCheckEntity entity);

    void deleteBatch(Long[] Ids);
}

