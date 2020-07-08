package io.renren.modules.project.dao;

import io.renren.modules.project.entity.BackWorkEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 返修表
 * 
 * @author ygg
 * @date 2019-11-28 11:52:47
 */
@Mapper
public interface BackWorkDao extends BaseMapper<BackWorkEntity> {
	
}
