package io.renren.modules.project.dao;

import io.renren.modules.project.entity.ProjectEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 项目列表
 * 
 * @author ygg
 * @date 2019-10-31 11:36:02
 */
@Mapper
public interface ProjectDao extends BaseMapper<ProjectEntity> {
	
}
