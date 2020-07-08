package io.renren.modules.ren.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.modules.ren.dao.RenRecordTempDao;
import io.renren.modules.ren.entity.RenRecordTempEntity;
import io.renren.modules.ren.service.RenRecordTempService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Map;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import org.springframework.transaction.annotation.Transactional;


import org.springframework.transaction.annotation.Transactional;


@Service("renRecordTempService")
public class RenRecordTempServiceImpl extends ServiceImpl<RenRecordTempDao, RenRecordTempEntity> implements RenRecordTempService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String key = (String)params.get("key");

        Page<RenRecordTempEntity> page = this.selectPage(
                new Query<RenRecordTempEntity>(params).getPage(),
                new EntityWrapper<RenRecordTempEntity>().like(StringUtils.isNotBlank(key),"id", key)
        );

        return new PageUtils(page);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(RenRecordTempEntity entity) {
        this.insert(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(RenRecordTempEntity entity) {
        this.updateById(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteBatch(Long[] roleIds) {
        //删除
        this.deleteBatchIds(Arrays.asList(roleIds));
    }

}