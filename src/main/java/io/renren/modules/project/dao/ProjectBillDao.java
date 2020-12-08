package io.renren.modules.project.dao;


import com.baomidou.mybatisplus.mapper.BaseMapper;
import io.renren.modules.project.entity.ProjectBillEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 项目清单表
 * 
 * @author ygg
 * @date 2020-12-08 11:07:29
 */
@Mapper
public interface ProjectBillDao extends BaseMapper<ProjectBillEntity> {
	
}
