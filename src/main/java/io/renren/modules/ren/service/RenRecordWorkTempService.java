package io.renren.modules.ren.service;

import com.baomidou.mybatisplus.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.ren.entity.RenRecordWorkEntity;
import io.renren.modules.ren.entity.RenRecordWorkTempEntity;

import java.util.List;
import java.util.Map;

/**
 * 职工档案工作经验表（临时）
 *
 * @author ygg
 * @date 2020-02-15 11:39:18
 */
public interface RenRecordWorkTempService extends IService<RenRecordWorkTempEntity> {

    PageUtils queryPage(Map<String, Object> params);

    //通过用户Id 获取列表
    List<RenRecordWorkTempEntity> queryListByUserId(Long userId);

    void deleteByUserId(Long userId);

    void save(RenRecordWorkTempEntity entity);

    void update(RenRecordWorkTempEntity entity);

    void deleteBatch(Long[] roleIds);
}

