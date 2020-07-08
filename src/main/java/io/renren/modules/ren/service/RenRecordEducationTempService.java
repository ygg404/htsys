package io.renren.modules.ren.service;

import com.baomidou.mybatisplus.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.ren.entity.RenRecordEducationEntity;
import io.renren.modules.ren.entity.RenRecordEducationTempEntity;

import java.util.List;
import java.util.Map;

/**
 * 职工档案教育背景表(临时)
 *
 * @author ygg
 * @date 2020-02-15 11:39:18
 */
public interface RenRecordEducationTempService extends IService<RenRecordEducationTempEntity> {

    PageUtils queryPage(Map<String, Object> params);

    //通过用户Id 获取列表
    List<RenRecordEducationTempEntity> queryListByUserId(Long userId);

    //通过用户Id 删除列表
    void deleteByUserId(Long userId);

    void save(RenRecordEducationTempEntity entity);

    void update(RenRecordEducationTempEntity entity);

    void deleteBatch(Long[] roleIds);
}

