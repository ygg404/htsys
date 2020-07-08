package io.renren.modules.set.service;


import com.baomidou.mybatisplus.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.set.entity.ShortTypeEntity;
import io.renren.modules.set.entity.WpShortcutEntity;

import java.util.List;
import java.util.Map;

/**
 * 类型名称
 *
 * @author ygg
 * @date 2019-10-23 09:28:19
 */
public interface ShortTypeService extends IService<ShortTypeEntity> {

    PageUtils queryPage(Map<String, Object> params);

    /**
     * 查询用短语类型列表列表
     */
    List<ShortTypeEntity> queryList();

    void save(ShortTypeEntity stype);

    void update(ShortTypeEntity stype);

    void deleteBatch(int[] roleIds);
}

