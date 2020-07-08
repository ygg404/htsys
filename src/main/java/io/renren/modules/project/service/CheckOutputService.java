package io.renren.modules.project.service;

import com.baomidou.mybatisplus.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.project.entity.CheckOutputEntity;

import java.util.Map;

/**
 * 产值核算表
 *
 * @author ygg
 * @date 2019-11-18 15:04:00
 */
public interface CheckOutputService extends IService<CheckOutputEntity> {

    PageUtils queryPage(Map<String, Object> params);

    void save(CheckOutputEntity entity);

    void update(CheckOutputEntity entity);

    void deleteBatch(Long[] roleIds);
}

