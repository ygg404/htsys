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

import io.renren.modules.project.dao.ProjectPlanDao;
import io.renren.modules.project.entity.ProjectPlanEntity;
import io.renren.modules.project.service.ProjectPlanService;
import org.springframework.transaction.annotation.Transactional;


@Service("projectPlanService")
public class ProjectPlanServiceImpl extends ServiceImpl<ProjectPlanDao, ProjectPlanEntity> implements ProjectPlanService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String key = (String)params.get("key");

        Page<ProjectPlanEntity> page = this.selectPage(
                new Query<ProjectPlanEntity>(params).getPage(),
                new EntityWrapper<ProjectPlanEntity>().like(StringUtils.isNotBlank(key),"id", key)
        );

        return new PageUtils(page);
    }

    @Override
    public ProjectPlanEntity queryByProjectNo(String projectNo){
        ProjectPlanEntity entity = this.selectOne( new EntityWrapper<ProjectPlanEntity>().eq("project_no" , projectNo));
        return entity;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(ProjectPlanEntity entity) {
        this.insert(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(ProjectPlanEntity entity) {
        this.updateById(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteBatch(Long[] roleIds) {
        //删除
        this.deleteBatchIds(Arrays.asList(roleIds));
    }

}