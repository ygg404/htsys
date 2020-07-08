package io.renren.modules.set.service;

import com.baomidou.mybatisplus.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.set.entity.BranchEntity;


import java.util.List;
import java.util.Map;

/**
 * 部门图表
 *
 * @author ygg
 * @date 2020-02-21 15:03:54
 */
public interface BranchService extends IService<BranchEntity> {

    PageUtils queryPage(Map<String, Object> params);

    // 通过上级部门Id 获取列表
    List<BranchEntity> queryByParentId(Long parentId);

    void save(BranchEntity entity);

    void update(BranchEntity entity);

    void deleteBatch(Long[] roleIds);
}

