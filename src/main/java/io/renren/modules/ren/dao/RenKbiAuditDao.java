package io.renren.modules.ren.dao;

import io.renren.modules.ren.entity.RenKbiAuditEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 效能分审定表
 * 
 * @author ygg
 * @date 2020-07-03 16:11:22
 */
@Mapper
public interface RenKbiAuditDao extends BaseMapper<RenKbiAuditEntity> {
	
}
