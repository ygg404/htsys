package io.renren.modules.ren.dao;

import io.renren.modules.ren.entity.RenSalaryBaseEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 基本工资表
 * 
 * @author ygg
 * @date 2020-03-07 11:22:51
 */
@Mapper
public interface RenSalaryBaseDao extends BaseMapper<RenSalaryBaseEntity> {
	
}
