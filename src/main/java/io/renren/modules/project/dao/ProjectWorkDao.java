package io.renren.modules.project.dao;

import io.renren.modules.project.entity.ProjectWorkEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 项目作业表
 * 
 * @author ygg
 * @date 2019-11-01 16:46:10
 */
@Mapper
public interface ProjectWorkDao extends BaseMapper<ProjectWorkEntity> {
	
}
