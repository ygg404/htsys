package io.renren.modules.set.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.modules.set.dao.ProjectTypeDao;
import io.renren.modules.set.dao.ProjectTypeVoDao;
import io.renren.modules.set.service.ProjectTypeVoService;
import io.renren.modules.set.vo.ProjectTypeVoEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("projectTypeVoService")
public class ProjectTypeVoServiceImpl extends ServiceImpl<ProjectTypeVoDao, ProjectTypeVoEntity> implements ProjectTypeVoService {

    @Override
    public List<ProjectTypeVoEntity> getPtypeVoList(){
        return this.baseMapper.getPtypeVoList();
    }
}
