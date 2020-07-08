package io.renren.modules.project.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import io.renren.modules.project.vo.ChartQualityVoEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 质量统计表
 */
@Mapper
public interface ChartQualityDao extends BaseMapper<ChartQualityVoEntity> {

    List<ChartQualityVoEntity> getChartQualityVoList(Map<String, Object> params);
}
