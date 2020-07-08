package io.renren.modules.set.service;

import com.baomidou.mybatisplus.service.IService;
import io.renren.modules.set.vo.ProjectTypeVoEntity;

import java.util.List;

public interface ProjectTypeVoService extends IService<ProjectTypeVoEntity> {

    List<ProjectTypeVoEntity> getPtypeVoList();
}
