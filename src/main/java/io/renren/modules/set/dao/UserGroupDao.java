package io.renren.modules.set.dao;

import io.renren.modules.set.entity.UserGroupEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户ID项目ID表
 * 
 * @author ygg
 * @date 2019-11-07 10:55:09
 */
@Mapper
public interface UserGroupDao extends BaseMapper<UserGroupEntity> {
	
}
