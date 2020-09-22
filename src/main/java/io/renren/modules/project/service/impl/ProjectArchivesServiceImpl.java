package io.renren.modules.project.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.modules.project.dao.ProjectArchivesDao;
import io.renren.modules.project.entity.ProjectArchivesEntity;
import io.renren.modules.project.service.ProjectArchivesService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Arrays;
import java.util.Map;
import java.util.List;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;




@Service("projectArchivesService")
public class ProjectArchivesServiceImpl extends ServiceImpl<ProjectArchivesDao, ProjectArchivesEntity> implements ProjectArchivesService {

    @Override
    public List<ProjectArchivesEntity> queryList(Map<String, Object> params){
        List<ProjectArchivesEntity> list = this.selectList(
                new EntityWrapper<ProjectArchivesEntity>()
        );
        return list;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(ProjectArchivesEntity entity) {
        this.insert(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(ProjectArchivesEntity entity) {
        this.updateById(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteBatch(Long[] Ids) {
        //删除
        this.deleteBatchIds(Arrays.asList(Ids));
    }

}