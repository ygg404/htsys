package io.renren.modules.set.service;

import com.baomidou.mybatisplus.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.set.dao.BranchUserDao;
import io.renren.modules.set.entity.BranchUserEntity;

import java.util.List;
import java.util.Map;

/**
 * 部门与成员关系表
 *
 * @author ygg
 * @date 2020-02-24 09:58:46
 */
public interface BranchUserService extends IService<BranchUserEntity> {

    PageUtils queryPage(Map<String, Object> params);

    // 通过部门Id 获取 部门与用户的关系列表
    List<BranchUserEntity> getListByBranchId(Long branchId);

    // 通过部门Id 删除 部门与用户的关系列表
    void deleteByBranchId(Long branchId);

    // 通过用户Id 删除 部门与用户的关系列表
    void deleteByUserId(Long userId);

    void save(BranchUserEntity entity);

    void update(BranchUserEntity entity);

    void deleteBatch(Long[] roleIds);
}

