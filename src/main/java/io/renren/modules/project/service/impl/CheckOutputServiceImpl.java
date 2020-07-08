package io.renren.modules.project.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Map;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.project.dao.CheckOutputDao;
import io.renren.modules.project.entity.CheckOutputEntity;
import io.renren.modules.project.service.CheckOutputService;
import org.springframework.transaction.annotation.Transactional;


@Service("checkOutputService")
public class CheckOutputServiceImpl extends ServiceImpl<CheckOutputDao, CheckOutputEntity> implements CheckOutputService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String key = (String)params.get("key");

        Page<CheckOutputEntity> page = this.selectPage(
                new Query<CheckOutputEntity>(params).getPage(),
                new EntityWrapper<CheckOutputEntity>().like(StringUtils.isNotBlank(key),"id", key)
        );

        return new PageUtils(page);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(CheckOutputEntity entity) {
        this.insert(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(CheckOutputEntity entity) {
        this.updateById(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteBatch(Long[] roleIds) {
        //删除
        this.deleteBatchIds(Arrays.asList(roleIds));
    }

}