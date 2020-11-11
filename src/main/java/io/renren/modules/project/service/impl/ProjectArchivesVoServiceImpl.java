package io.renren.modules.project.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;
import io.renren.modules.project.dao.ProjectArchivesDao;
import io.renren.modules.project.dao.ProjectArchivesVoDao;
import io.renren.modules.project.entity.ProjectArchivesEntity;
import io.renren.modules.project.service.ProjectArchivesService;
import io.renren.modules.project.service.ProjectArchivesVoService;
import io.renren.modules.project.vo.ProjectArchivesVoEntity;
import io.renren.modules.project.vo.RecycleVoEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Map;


@Service("projectArchivesVoService")
public class ProjectArchivesVoServiceImpl extends ServiceImpl<ProjectArchivesVoDao, ProjectArchivesVoEntity> implements ProjectArchivesVoService {

    @Override
    @Transactional(rollbackFor = Exception.class)
    public PageUtils getArchivesVoPage(Map<String, Object> params){
        Page<ProjectArchivesVoEntity> pagnation = new Query<ProjectArchivesVoEntity>(params).getPage();
        pagnation = pagnation.setRecords( baseMapper.getArchivesVoList(pagnation , params ) );
        return new PageUtils(pagnation);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ProjectArchivesVoEntity getArchivesVo(Map<String, Object> params){
        return baseMapper.getArchivesVo(params);
    }

}