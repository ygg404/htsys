package io.renren.modules.project.service;

import com.baomidou.mybatisplus.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.project.entity.BackWorkEntity;


import java.util.List;
import java.util.Map;

/**
 * 返修表
 *
 * @author ygg
 * @date 2019-11-28 11:52:47
 */
public interface BackWorkService extends IService<BackWorkEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<BackWorkEntity> queryByProjectNo(String projectNo);

    void save(BackWorkEntity entity);

    void update(BackWorkEntity entity);

    void deleteBatch(Long[] roleIds);
}

