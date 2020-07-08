package io.renren.modules.ren.service;

import com.baomidou.mybatisplus.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.ren.entity.RenRecordEntity;

import java.util.Map;

/**
 * 职工档案表（正式）
 *
 * @author ygg
 * @date 2020-02-11 11:50:15
 */
public interface RenRecordService extends IService<RenRecordEntity> {

    PageUtils queryPage(Map<String, Object> params);

    void save(RenRecordEntity entity);

    void update(RenRecordEntity entity);

    void deleteBatch(Long[] roleIds);
}

