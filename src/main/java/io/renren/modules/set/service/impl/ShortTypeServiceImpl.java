package io.renren.modules.set.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.modules.set.entity.WpShortcutEntity;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.set.dao.ShortTypeDao;
import io.renren.modules.set.entity.ShortTypeEntity;
import io.renren.modules.set.service.ShortTypeService;
import org.springframework.transaction.annotation.Transactional;


@Service("shortTypeService")
public class ShortTypeServiceImpl extends ServiceImpl<ShortTypeDao, ShortTypeEntity> implements ShortTypeService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String key = (String)params.get("key");

        Page<ShortTypeEntity> page = this.selectPage(
                new Query<ShortTypeEntity>(params).getPage(),
                new EntityWrapper<ShortTypeEntity>().like(StringUtils.isNotBlank(key),"typeName", key)
        );

        return new PageUtils(page);
    }

    @Override
    public List<ShortTypeEntity> queryList(){
        List<ShortTypeEntity> list = this.selectList( new EntityWrapper<ShortTypeEntity>());
        return list;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(ShortTypeEntity shortTypeEntity) {
        this.insert(shortTypeEntity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(ShortTypeEntity shortTypeEntity) {
        this.updateById(shortTypeEntity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteBatch(int[] roleIds) {
        //删除快捷短语
        this.deleteBatchIds(Arrays.asList(roleIds));
    }

}