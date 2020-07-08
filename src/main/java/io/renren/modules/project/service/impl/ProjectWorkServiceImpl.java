package io.renren.modules.project.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Map;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.project.dao.ProjectWorkDao;
import io.renren.modules.project.entity.ProjectWorkEntity;
import io.renren.modules.project.service.ProjectWorkService;
import org.springframework.transaction.annotation.Transactional;


@Service("projectWorkService")
public class ProjectWorkServiceImpl extends ServiceImpl<ProjectWorkDao, ProjectWorkEntity> implements ProjectWorkService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String key = (String)params.get("key");

        Page<ProjectWorkEntity> page = this.selectPage(
                new Query<ProjectWorkEntity>(params).getPage(),
                new EntityWrapper<ProjectWorkEntity>().like(StringUtils.isNotBlank(key),"id", key)
        );

        return new PageUtils(page);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ProjectWorkEntity queryByProjectNo(String projectNo) {
        ProjectWorkEntity entity = this.selectOne(new EntityWrapper<ProjectWorkEntity>().eq("project_no" , projectNo));
        return  entity;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(ProjectWorkEntity entity) {
        this.insert(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(ProjectWorkEntity entity) {
        this.updateById(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteBatch(Long[] roleIds) {
        //删除
        this.deleteBatchIds(Arrays.asList(roleIds));
    }

}