package io.renren.modules.perf.dao;

import io.renren.modules.perf.entity.PerfExtraScoringEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 加减分年度评分
 * 
 * @author ygg
 * @date 2020-05-25 17:36:28
 */
@Mapper
public interface PerfExtraScoringDao extends BaseMapper<PerfExtraScoringEntity> {

}
