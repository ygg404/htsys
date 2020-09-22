package io.renren.modules.dop.service.impl;

import io.renren.common.utils.PageUtils;
import io.renren.datasources.DataSourceNames;
import io.renren.datasources.annotation.DataSource;
import io.renren.modules.dop.service.DopArchivesService;
import io.renren.modules.dop.service.DopDeviceService;
import io.renren.modules.project.service.ProjectArchivesVoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service("dopArchivesService")
public class DopArchivesServiceImpl implements DopArchivesService {

    @Autowired
    ProjectArchivesVoService projectArchivesVoService;


    @Override
    @DataSource(name = DataSourceNames.SECOND)
    public PageUtils queryArchivesPage(Map<String, Object> params){
        return projectArchivesVoService.getArchivesVoPage(params);
    }
}
