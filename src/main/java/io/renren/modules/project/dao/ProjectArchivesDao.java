package io.renren.modules.project.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import io.renren.modules.project.entity.ProjectArchivesEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 项目成果表
 * 
 * @author ygg
 * @date 2020-09-18 10:56:51
 */
@Mapper
public interface ProjectArchivesDao extends BaseMapper<ProjectArchivesEntity> {
	
}
