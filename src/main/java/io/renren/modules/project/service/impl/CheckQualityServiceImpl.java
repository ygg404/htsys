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

import io.renren.modules.project.dao.CheckQualityDao;
import io.renren.modules.project.entity.CheckQualityEntity;
import io.renren.modules.project.service.CheckQualityService;
import org.springframework.transaction.annotation.Transactional;


@Service("checkQualityService")
public class CheckQualityServiceImpl extends ServiceImpl<CheckQualityDao, CheckQualityEntity> implements CheckQualityService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String key = (String)params.get("key");

        Page<CheckQualityEntity> page = this.selectPage(
                new Query<CheckQualityEntity>(params).getPage(),
                new EntityWrapper<CheckQualityEntity>().like(StringUtils.isNotBlank(key),"id", key)
        );

        return new PageUtils(page);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public CheckQualityEntity queryByMap(Map<String, Object> params){
        String projectNo = (String)params.get("projectNo");
        CheckQualityEntity entity = this.selectOne(
                new EntityWrapper<CheckQualityEntity>().eq("project_no" , projectNo)
        );
        return  entity;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(CheckQualityEntity entity) {
        this.insert(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(CheckQualityEntity entity) {
        this.updateById(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteBatch(Long[] roleIds) {
        //删除
        this.deleteBatchIds(Arrays.asList(roleIds));
    }

}