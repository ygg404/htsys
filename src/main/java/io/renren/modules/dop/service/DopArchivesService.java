package io.renren.modules.dop.service;

import io.renren.common.utils.PageUtils;
import io.renren.datasources.DataSourceNames;
import io.renren.datasources.annotation.DataSource;
import io.renren.modules.project.service.ProjectArchivesVoService;
import io.renren.modules.project.vo.ProjectArchivesVoEntity;
import io.renren.modules.sys.entity.SysUserEntity;
import io.renren.modules.sys.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

public interface DopArchivesService {

    PageUtils queryArchivesPage(Map<String, Object> params);
}
