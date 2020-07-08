package io.renren.modules.ren.service;

import com.baomidou.mybatisplus.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.ren.entity.RenAccessUserEntity;

import java.util.List;
import java.util.Map;

/**
 * 考核目标与用户关系表
 *
 * @author ygg
 * @date 2020-03-03 09:18:50
 */
public interface RenAccessUserService extends IService<RenAccessUserEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<RenAccessUserEntity> queryList(Map<String, Object> params);

    List<RenAccessUserEntity> queryByUserId(Long userId);

    void deleteByUserId(Long userId);

    void save(RenAccessUserEntity entity);

    void update(RenAccessUserEntity entity);

    void deleteBatch(Long[] roleIds);
}

