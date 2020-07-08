package io.renren.modules.project.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import io.renren.modules.project.vo.ChartOutputVoEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 产值统计表
 */
@Mapper
public interface ChartOutputDao extends BaseMapper<ChartOutputVoEntity> {

    List<ChartOutputVoEntity> getChartOutputVoList(Map<String, Object> params);
}
