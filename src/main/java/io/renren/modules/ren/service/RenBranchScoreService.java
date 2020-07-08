package io.renren.modules.ren.service;

import com.baomidou.mybatisplus.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.ren.entity.RenBranchBaseEntity;
import io.renren.modules.ren.entity.RenBranchScoreEntity;

import java.util.List;
import java.util.Map;

/**
 * 部门与产值的考核分数
 *
 * @author ygg
 * @date 2020-03-05 10:58:38
 */
public interface RenBranchScoreService extends IService<RenBranchScoreEntity> {

    List<RenBranchScoreEntity> queryList(Map<String, Object> params);

    void save(RenBranchScoreEntity entity);

    void update(RenBranchScoreEntity entity);

    void deleteBatch(Long[] roleIds);

    void deleteByBranchId(Long branchId);
}

