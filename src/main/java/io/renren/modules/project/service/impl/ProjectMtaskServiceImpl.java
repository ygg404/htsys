package io.renren.modules.project.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Arrays;
import java.util.Map;
import java.util.List;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.project.dao.ProjectMtaskDao;
import io.renren.modules.project.entity.ProjectMtaskEntity;
import io.renren.modules.project.service.ProjectMtaskService;


@Service("projectMtaskService")
public class ProjectMtaskServiceImpl extends ServiceImpl<ProjectMtaskDao, ProjectMtaskEntity> implements ProjectMtaskService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String key = (String)params.get("key");

        Page<ProjectMtaskEntity> page = this.selectPage(
                new Query<ProjectMtaskEntity>(params).getPage(),
                new EntityWrapper<ProjectMtaskEntity>().like(StringUtils.isNotBlank(key),"id", key)
        );

        return new PageUtils(page);
    }

    @Override
    public List<ProjectMtaskEntity> queryList(Map<String, Object> params){
        List<ProjectMtaskEntity> list = this.selectByMap(params);
        return list;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ProjectMtaskEntity queryByNo(Map<String, Object> params){
        String projectNo = params.get("projectNo").toString();
        ProjectMtaskEntity entity = this.selectOne(new EntityWrapper<ProjectMtaskEntity>().eq("project_no" , projectNo));
        return entity;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(ProjectMtaskEntity entity) {
        this.insert(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(ProjectMtaskEntity entity) {
        this.updateById(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteBatch(Long[] Ids) {
        //删除
        this.deleteBatchIds(Arrays.asList(Ids));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteByProNo(String projectNo){
        this.delete(new EntityWrapper<ProjectMtaskEntity>().eq("project_no", projectNo));
    }

}