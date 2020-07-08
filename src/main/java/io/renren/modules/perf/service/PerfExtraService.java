package io.renren.modules.perf.service;

import com.baomidou.mybatisplus.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.perf.entity.PerfExtraEntity;

import java.util.Map;
import java.util.List;

/**
 * 加减分表
 *
 * @author ygg
 * @date 2020-05-25 17:36:28
 */
public interface PerfExtraService extends IService<PerfExtraEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<PerfExtraEntity> queryList(Map<String, Object> params);

    void save(PerfExtraEntity entity);

    void update(PerfExtraEntity entity);

    void deleteBatch(Long[] Ids);
}

