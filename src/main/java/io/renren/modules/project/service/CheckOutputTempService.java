package io.renren.modules.project.service;

import com.baomidou.mybatisplus.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.project.entity.CheckOutputTempEntity;

import java.util.List;
import java.util.Map;

/**
 * 产值明细预算表
 *
 * @author ygg
 * @date 2019-12-26 16:01:03
 */
public interface CheckOutputTempService extends IService<CheckOutputTempEntity> {

    List<CheckOutputTempEntity> queryList(Map<String, Object> params);

    void save(CheckOutputTempEntity entity);

    void update(CheckOutputTempEntity entity);

    void deleteBatch(Long[] roleIds);
}

