package io.renren.modules.project.service;


import com.baomidou.mybatisplus.service.IService;
import io.renren.modules.project.vo.ChartOutputVoEntity;
import io.renren.modules.project.vo.ChartQualityVoEntity;

import java.util.List;
import java.util.Map;

/**
 * 质量统计表
 */
public interface ChartQualityService extends IService<ChartQualityVoEntity> {

    List<ChartQualityVoEntity> queryList(Map<String, Object> params);
}

