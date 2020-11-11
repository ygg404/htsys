package io.renren.modules.dop.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import io.renren.common.utils.PageUtils;
import io.renren.datasources.DataSourceNames;
import io.renren.datasources.annotation.DataSource;
import io.renren.modules.dop.service.DopArchivesService;
import io.renren.modules.dop.service.DopDeviceService;
import io.renren.modules.project.entity.ProjectArchivesEntity;
import io.renren.modules.project.service.ProjectArchivesService;
import io.renren.modules.project.service.ProjectArchivesVoService;
import io.renren.modules.project.vo.ProjectArchivesVoEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

@Service("dopArchivesService")
public class DopArchivesServiceImpl implements DopArchivesService {

    @Autowired
    ProjectArchivesVoService projectArchivesVoService;

    @Autowired
    ProjectArchivesService projectArchivesService;

    @Override
    @DataSource(name = DataSourceNames.SECOND)
    public PageUtils queryArchivesPage(Map<String, Object> params){
        return projectArchivesVoService.getArchivesVoPage(params);
    }

    @Override
    @DataSource(name = DataSourceNames.SECOND)
    public ProjectArchivesVoEntity queryArchivesVo(Map<String, Object> params){
        return projectArchivesVoService.getArchivesVo(params);
    }

    @Override
    @DataSource(name = DataSourceNames.SECOND)
    public void update(ProjectArchivesEntity archive){
        ProjectArchivesEntity entity = projectArchivesService.selectOne(
                new EntityWrapper<ProjectArchivesEntity>().eq("project_no" , archive.getProjectNo())
        );
        if (entity != null) {
            archive.setId( entity.getId() );
            archive.setCreateTime(new Date());
        }
        projectArchivesService.insertOrUpdate(archive);
    }
}
