package io.renren.modules.dop.service;

import com.baomidou.mybatisplus.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.dop.entity.DopBmapEntity;

import java.util.Map;
import java.util.List;

/**
 * 地图标注表
 *
 * @author ygg
 * @date 2020-09-24 10:26:01
 */
public interface DopBmapService extends IService<DopBmapEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<DopBmapEntity> queryList(Map<String, Object> params);

    void save(DopBmapEntity entity);

    void update(DopBmapEntity entity);

    void deleteBatch(Long[] Ids);

    void deleteByProId(Long projectId);
}

