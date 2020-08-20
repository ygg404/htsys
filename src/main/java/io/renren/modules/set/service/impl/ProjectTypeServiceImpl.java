package io.renren.modules.set.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.modules.project.entity.ProjectEntity;
import io.renren.modules.sys.entity.SysUserEntity;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.set.dao.ProjectTypeDao;
import io.renren.modules.set.entity.ProjectTypeEntity;
import io.renren.modules.set.service.ProjectTypeService;
import org.springframework.transaction.annotation.Transactional;


@Service("projectTypeService")
public class ProjectTypeServiceImpl extends ServiceImpl<ProjectTypeDao, ProjectTypeEntity> implements ProjectTypeService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String key = (String)params.get("key");

        Page<ProjectTypeEntity> page = this.selectPage(
                new Query<ProjectTypeEntity>(params).getPage(),
                new EntityWrapper<ProjectTypeEntity>().like(StringUtils.isNotBlank(key),"id", key)
        );

        return new PageUtils(page);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ProjectTypeEntity getByName(String typeName){
        return this.selectOne( new EntityWrapper<ProjectTypeEntity>().eq("name" , typeName));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(ProjectTypeEntity entity) {
        this.insert(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(ProjectTypeEntity entity) {
        this.updateById(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteBatch(Long[] roleIds) {
        //删除
        this.deleteBatchIds(Arrays.asList(roleIds));
    }

    //下拉列表
    @Override
    public List<ProjectTypeEntity> ToSelectqueryList(){
        List<ProjectTypeEntity> list = this.selectList( new EntityWrapper<ProjectTypeEntity>());
        return list;
    }

    @Override
    public List<ProjectTypeEntity> queryList(Map<String, Object> params){
        String id = (String)params.get("wtypeId");
        List<ProjectTypeEntity> list = this.selectList( new EntityWrapper<ProjectTypeEntity>().eq("id",id));
        return list;
    }

    @Override
    public List<ProjectTypeEntity> selectBatch(Long[] ids){
        List<ProjectTypeEntity> list = this.selectBatchIds(Arrays.asList(ids));
        return list;
    }

}