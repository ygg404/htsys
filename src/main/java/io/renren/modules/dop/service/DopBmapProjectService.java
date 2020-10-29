package io.renren.modules.dop.service;

import com.baomidou.mybatisplus.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.dop.entity.DopBmapProjectEntity;


import java.util.Map;
import java.util.List;

/**
 * 百度地图项目名称
 *
 * @author ygg
 * @date 2020-10-22 10:56:31
 */
public interface DopBmapProjectService extends IService<DopBmapProjectEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<DopBmapProjectEntity> queryList(Map<String, Object> params);

    void save(DopBmapProjectEntity entity);

    void update(DopBmapProjectEntity entity);

    void deleteBatch(Long[] Ids);
}

