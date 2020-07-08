package io.renren.modules.project.dao;

import io.renren.modules.project.entity.ProjectScheduleEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 进度表
 * 
 * @author ygg
 * @date 2019-11-01 16:46:10
 */
@Mapper
public interface ProjectScheduleDao extends BaseMapper<ProjectScheduleEntity> {
	
}
