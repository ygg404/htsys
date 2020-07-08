package io.renren.modules.project.dao;

import io.renren.modules.project.entity.CheckQualityEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 质量检查表
 * 
 * @author ygg
 * @date 2019-11-16 09:22:12
 */
@Mapper
public interface CheckQualityDao extends BaseMapper<CheckQualityEntity> {
	
}
