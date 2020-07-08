package io.renren.modules.project.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.project.dao.QualityScoreDao;
import io.renren.modules.project.entity.QualityScoreEntity;
import io.renren.modules.project.service.QualityScoreService;
import org.springframework.transaction.annotation.Transactional;


@Service("qualityScoreService")
public class QualityScoreServiceImpl extends ServiceImpl<QualityScoreDao, QualityScoreEntity> implements QualityScoreService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String key = (String)params.get("key");

        Page<QualityScoreEntity> page = this.selectPage(
                new Query<QualityScoreEntity>(params).getPage(),
                new EntityWrapper<QualityScoreEntity>().like(StringUtils.isNotBlank(key),"id", key)
        );

        return new PageUtils(page);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<QualityScoreEntity> queryList(Map<String, Object> params){
        String projectNo = (String)params.get("projectNo");
        List<QualityScoreEntity> list = this.selectList(
                new EntityWrapper<QualityScoreEntity>().eq("project_no" , projectNo)
        );
        return list;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(QualityScoreEntity entity) {
        this.insert(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(QualityScoreEntity entity) {
        this.updateById(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteBatch(Long[] roleIds) {
        //删除
        this.deleteBatchIds(Arrays.asList(roleIds));
    }

}