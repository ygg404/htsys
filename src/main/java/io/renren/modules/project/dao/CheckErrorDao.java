package io.renren.modules.project.dao;


import com.baomidou.mybatisplus.mapper.BaseMapper;
import io.renren.modules.project.entity.CheckErrorEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 质检报告误差
 * 
 * @author ygg
 * @date 2020-08-19 17:13:37
 */
@Mapper
public interface CheckErrorDao extends BaseMapper<CheckErrorEntity> {
	
}
