package io.renren.modules.set.dao;

import io.renren.modules.set.entity.WorkProjectTypeEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 作业类型ID项目类型ID关联表
 * 
 * @author ygg
 * @date 2019-10-31 16:56:32
 */
@Mapper
public interface WorkProjectTypeDao extends BaseMapper<WorkProjectTypeEntity> {
	
}
