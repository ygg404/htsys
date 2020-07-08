package io.renren.modules.set.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.modules.set.dao.WpShortcutDao;
import io.renren.modules.set.entity.WpShortcutEntity;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;


import io.renren.modules.set.service.WpShortcutService;
import org.springframework.transaction.annotation.Transactional;


@Service("wpShortcutService")
public class WpShortcutServiceImpl extends ServiceImpl<WpShortcutDao, WpShortcutEntity> implements WpShortcutService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String key = (String)params.get("key");

        Page<WpShortcutEntity> page = this.selectPage(
                new Query<WpShortcutEntity>(params).getPage(),
                new EntityWrapper<WpShortcutEntity>().like(StringUtils.isNotBlank(key),"shortcut_note", key)
        );

        return new PageUtils(page);
    }

    @Override
    public List<WpShortcutEntity> getListByShortTypeId(Long shortTypeId){
        List<WpShortcutEntity> wpList = this.selectList(
                new EntityWrapper<WpShortcutEntity>()
                .andNew( "concat(',', shortcut_type_id, ',') Like {0}" ,
                        "%," + shortTypeId.toString() + ",%")
        );
        return  wpList;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(WpShortcutEntity wpShortcutEntity) {
        this.insert(wpShortcutEntity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(WpShortcutEntity wpShortcutEntity) {
        this.updateById(wpShortcutEntity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteBatch(Long[] roleIds) {
        //删除快捷短语
        this.deleteBatchIds(Arrays.asList(roleIds));
    }
}