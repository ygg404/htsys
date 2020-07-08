package io.renren.modules.set.service;

import com.baomidou.mybatisplus.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.set.entity.WpShortcutEntity;


import java.util.List;
import java.util.Map;

/**
 * 快捷短语输入
 * @date 2019-10-22 15:09:03
 */
public interface WpShortcutService extends IService<WpShortcutEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<WpShortcutEntity> getListByShortTypeId(Long shortTypeId);

    void save(WpShortcutEntity role);

    void update(WpShortcutEntity role);

    void deleteBatch(Long[] roleIds);
}

