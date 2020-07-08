package io.renren.modules.project.service;

import com.baomidou.mybatisplus.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.project.entity.CheckQualityEntity;

import java.util.Map;

/**
 * 质量检查表
 *
 * @author ygg
 * @date 2019-11-16 09:22:12
 */
public interface CheckQualityService extends IService<CheckQualityEntity> {

    PageUtils queryPage(Map<String, Object> params);

    CheckQualityEntity queryByMap(Map<String, Object> params);

    void save(CheckQualityEntity entity);

    void update(CheckQualityEntity entity);

    void deleteBatch(Long[] roleIds);
}

