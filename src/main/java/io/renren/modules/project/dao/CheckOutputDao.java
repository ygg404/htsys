package io.renren.modules.project.dao;

import io.renren.modules.project.entity.CheckOutputEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 产值核算表
 * 
 * @author ygg
 * @date 2019-11-18 15:04:00
 */
@Mapper
public interface CheckOutputDao extends BaseMapper<CheckOutputEntity> {
	
}
