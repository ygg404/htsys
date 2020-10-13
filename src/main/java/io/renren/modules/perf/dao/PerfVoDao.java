package io.renren.modules.perf.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import io.renren.modules.perf.vo.PerfVoEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/***
 *  查询 年月评分列表
 */
@Mapper
public interface PerfVoDao extends BaseMapper<PerfVoEntity> {

    List<PerfVoEntity> queryVoList(Map<String, Object> params);
}
