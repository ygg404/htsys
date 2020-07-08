package io.renren.modules.perf.dao;

import io.renren.modules.perf.entity.PerfAssessEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 效能考核评分
 * 
 * @author ygg
 * @date 2020-05-16 15:45:46
 */
@Mapper
public interface PerfAssessDao extends BaseMapper<PerfAssessEntity> {
	
}
