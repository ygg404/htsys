package io.renren.modules.ren.dao;

import io.renren.modules.ren.entity.RenRecordEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 职工档案表（正式）
 * 
 * @author ygg
 * @date 2020-02-11 11:50:15
 */
@Mapper
public interface RenRecordDao extends BaseMapper<RenRecordEntity> {
	
}
