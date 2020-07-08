package io.renren.modules.project.dao;

import io.renren.modules.project.entity.ProjectStageEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 项目阶段表
 * 
 * @author ygg
 * @date 2019-11-01 16:46:10
 */
@Mapper
public interface ProjectStageDao extends BaseMapper<ProjectStageEntity> {
	
}
