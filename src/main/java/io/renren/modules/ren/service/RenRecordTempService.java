package io.renren.modules.ren.service;

import com.baomidou.mybatisplus.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.ren.entity.RenRecordTempEntity;

import java.util.Map;

/**
 * 职工档案表（临时）
 *
 * @author ygg
 * @date 2020-02-15 11:39:17
 */
public interface RenRecordTempService extends IService<RenRecordTempEntity> {

    PageUtils queryPage(Map<String, Object> params);

    void save(RenRecordTempEntity entity);

    void update(RenRecordTempEntity entity);

    void deleteBatch(Long[] roleIds);
}

