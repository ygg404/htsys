package io.renren.modules.ren.dao;

import io.renren.modules.ren.entity.RenAccessUserEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 考核目标与用户关系表
 * 
 * @author ygg
 * @date 2020-03-03 09:18:50
 */
@Mapper
public interface RenAccessUserDao extends BaseMapper<RenAccessUserEntity> {
	
}
