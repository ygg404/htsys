package io.renren.modules.ren.service;

import com.baomidou.mybatisplus.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.ren.entity.RenRecordEducationEntity;
import io.renren.modules.ren.entity.RenRecordWorkEntity;

import java.util.List;
import java.util.Map;

/**
 * 职工档案工作经验表（正式）
 *
 * @author ygg
 * @date 2020-02-13 11:59:22
 */
public interface RenRecordWorkService extends IService<RenRecordWorkEntity> {

    PageUtils queryPage(Map<String, Object> params);

    //通过用户Id 获取列表
    List<RenRecordWorkEntity> queryListByUserId(Long userId);

    void deleteByUserId(Long userId);

    void save(RenRecordWorkEntity entity);

    void update(RenRecordWorkEntity entity);

    void deleteBatch(Long[] roleIds);
}

