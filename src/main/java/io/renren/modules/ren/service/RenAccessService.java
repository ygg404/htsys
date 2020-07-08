package io.renren.modules.ren.service;

import com.baomidou.mybatisplus.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.ren.entity.RenAccessEntity;

import java.util.List;
import java.util.Map;

/**
 * 考核目标
 *
 * @author ygg
 * @date 2020-02-28 16:07:08
 */
public interface RenAccessService extends IService<RenAccessEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<RenAccessEntity> queryList(Map<String, Object> params);

    void save(RenAccessEntity entity);

    void update(RenAccessEntity entity);

    void deleteBatch(Long[] roleIds);

    void deleteByParentId(Long parentId);
}

