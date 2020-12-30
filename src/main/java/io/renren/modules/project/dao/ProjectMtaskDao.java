package io.renren.modules.project.dao;


import com.baomidou.mybatisplus.mapper.BaseMapper;
import io.renren.modules.project.entity.ProjectMtaskEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 作业任务分工表
 * 
 * @author ygg
 * @date 2020-12-29 15:41:51
 */
@Mapper
public interface ProjectMtaskDao extends BaseMapper<ProjectMtaskEntity> {
	
}
