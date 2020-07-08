package io.renren.modules.ren.service;

import com.baomidou.mybatisplus.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.ren.entity.RenBranchBaseEntity;

import java.util.List;
import java.util.Map;

/**
 * 部门考核基本信息
 *
 * @author ygg
 * @date 2020-03-05 10:58:38
 */
public interface RenBranchBaseService extends IService<RenBranchBaseEntity> {

    List<RenBranchBaseEntity> queryList(Map<String, Object> params);

    void save(RenBranchBaseEntity entity);

    void update(RenBranchBaseEntity entity);

    void deleteBatch(Long[] roleIds);
}

