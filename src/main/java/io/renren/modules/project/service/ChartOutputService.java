package io.renren.modules.project.service;

import com.baomidou.mybatisplus.service.IService;
import io.renren.modules.project.entity.CheckOutputEntity;
import io.renren.modules.project.vo.ChartOutputVoEntity;

import java.util.List;
import java.util.Map;

public interface ChartOutputService extends IService<ChartOutputVoEntity> {

    List<ChartOutputVoEntity> queryList(Map<String, Object> params);
}
