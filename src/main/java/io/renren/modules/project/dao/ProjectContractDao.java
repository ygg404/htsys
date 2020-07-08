package io.renren.modules.project.dao;


import com.baomidou.mybatisplus.mapper.BaseMapper;
import io.renren.modules.project.entity.ProjectContractEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 合同表
 * 
 * @author ygg
 * @date 2019-10-30 15:40:10
 */
@Mapper
public interface ProjectContractDao extends BaseMapper<ProjectContractEntity> {
	
}
