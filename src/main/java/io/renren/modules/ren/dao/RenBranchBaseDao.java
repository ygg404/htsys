package io.renren.modules.ren.dao;

import io.renren.modules.ren.entity.RenBranchBaseEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 部门考核基本信息
 * 
 * @author ygg
 * @date 2020-03-05 10:58:38
 */
@Mapper
public interface RenBranchBaseDao extends BaseMapper<RenBranchBaseEntity> {
	
}
