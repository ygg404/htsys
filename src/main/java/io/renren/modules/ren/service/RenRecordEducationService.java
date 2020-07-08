package io.renren.modules.ren.service;

import com.baomidou.mybatisplus.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.ren.entity.RenRecordEducationEntity;

import java.util.List;
import java.util.Map;

/**
 * 职工档案教育背景表(正式)
 *
 * @author ygg
 * @date 2020-02-13 11:59:22
 */
public interface RenRecordEducationService extends IService<RenRecordEducationEntity> {

    PageUtils queryPage(Map<String, Object> params);

    //通过用户Id 获取列表
    List<RenRecordEducationEntity> queryListByUserId(Long userId);

    //通过用户Id 删除列表
    void deleteByUserId(Long userId);

    void save(RenRecordEducationEntity entity);

    void update(RenRecordEducationEntity entity);

    void deleteBatch(Long[] roleIds);
}

