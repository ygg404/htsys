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

import io.renren.modules.project.dao.ProjectStageDao;
import io.renren.modules.project.entity.ProjectStageEntity;
import io.renren.modules.project.service.ProjectStageService;
import org.springframework.transaction.annotation.Transactional;


@Service("projectStageService")
public class ProjectStageServiceImpl extends ServiceImpl<ProjectStageDao, ProjectStageEntity> implements ProjectStageService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String key = (String)params.get("key");

        Page<ProjectStageEntity> page = this.selectPage(
                new Query<ProjectStageEntity>(params).getPage(),
                new EntityWrapper<ProjectStageEntity>().like(StringUtils.isNotBlank(key),"id", key)
        );

        return new PageUtils(page);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(ProjectStageEntity entity) {
        this.insert(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(ProjectStageEntity entity) {
        this.updateById(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteBatch(Long[] roleIds) {
        //删除
        this.deleteBatchIds(Arrays.asList(roleIds));
    }

}