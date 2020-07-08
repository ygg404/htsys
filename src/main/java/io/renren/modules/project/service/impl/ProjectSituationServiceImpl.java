package io.renren.modules.project.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.modules.project.entity.ProjectSituationEntity;
import io.renren.modules.project.service.ProjectSituationService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Map;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.project.dao.ProjectSituationDao;
import org.springframework.transaction.annotation.Transactional;


@Service("ProjectSituationService")
public class ProjectSituationServiceImpl extends ServiceImpl<ProjectSituationDao, ProjectSituationEntity> implements ProjectSituationService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String key = (String)params.get("key");

        Page<ProjectSituationEntity> page = this.selectPage(
                new Query<ProjectSituationEntity>(params).getPage(),
                new EntityWrapper<ProjectSituationEntity>().like(StringUtils.isNotBlank(key),"id", key)
        );

        return new PageUtils(page);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ProjectSituationEntity queryByProjectNo(String projectNo){
        //保存核算后 设置项目情况已经核算
        ProjectSituationEntity situationEntity = this.selectOne(
                new EntityWrapper<ProjectSituationEntity>().eq("project_no",projectNo)
        );
        if(situationEntity == null){
            situationEntity = new ProjectSituationEntity();
            situationEntity.setprojectNo(projectNo);
        }
        return situationEntity;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(ProjectSituationEntity entity) {
        this.insert(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(ProjectSituationEntity entity) {
        this.updateById(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteBatch(Long[] roleIds) {
        //删除
        this.deleteBatchIds(Arrays.asList(roleIds));
    }

}