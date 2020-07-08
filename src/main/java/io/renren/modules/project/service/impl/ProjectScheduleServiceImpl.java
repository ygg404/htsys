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

import io.renren.modules.project.dao.ProjectScheduleDao;
import io.renren.modules.project.entity.ProjectScheduleEntity;
import io.renren.modules.project.service.ProjectScheduleService;
import org.springframework.transaction.annotation.Transactional;


@Service("projectScheduleService")
public class ProjectScheduleServiceImpl extends ServiceImpl<ProjectScheduleDao, ProjectScheduleEntity> implements ProjectScheduleService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String key = (String)params.get("key");

        Page<ProjectScheduleEntity> page = this.selectPage(
                new Query<ProjectScheduleEntity>(params).getPage(),
                new EntityWrapper<ProjectScheduleEntity>().like(StringUtils.isNotBlank(key),"id", key)
        );

        return new PageUtils(page);
    }

    @Override
    public List<ProjectScheduleEntity> queryList(Map<String, Object> params){
        String projectNo = (String)params.get("projectNo");

        List<ProjectScheduleEntity> list = this.selectList(
                new EntityWrapper<ProjectScheduleEntity>().eq(StringUtils.isNotBlank(projectNo),"project_no", projectNo)
        );

        return list;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(ProjectScheduleEntity entity) {
        this.insert(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(ProjectScheduleEntity entity) {
        this.updateById(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteBatch(Long[] roleIds) {
        //删除
        this.deleteBatchIds(Arrays.asList(roleIds));
    }

}