package io.renren.modules.project.dao;

import io.renren.modules.project.entity.CheckOutputTempEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 产值明细预算表
 * 
 * @author ygg
 * @date 2019-12-26 16:01:03
 */
@Mapper
public interface CheckOutputTempDao extends BaseMapper<CheckOutputTempEntity> {
	
}
