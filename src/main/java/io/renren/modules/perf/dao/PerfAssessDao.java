package io.renren.modules.perf.dao;

import io.renren.modules.perf.entity.PerfAssessEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import io.renren.modules.perf.entity.PerfExtraScoringEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 效能考核评分
 * 
 * @author ygg
 * @date 2020-05-16 15:45:46
 */
@Mapper
public interface PerfAssessDao extends BaseMapper<PerfAssessEntity> {
    /**
     * 获取该年度的被考核人的评分表
     * @param params
     * @return
     */
    List<PerfAssessEntity> queryAssessList(Map<String, Object> params);
}
