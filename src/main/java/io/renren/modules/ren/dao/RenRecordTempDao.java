package io.renren.modules.ren.dao;

import io.renren.modules.ren.entity.RenRecordTempEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 职工档案表（临时）
 * 
 * @author ygg
 * @date 2020-02-15 11:39:17
 */
@Mapper
public interface RenRecordTempDao extends BaseMapper<RenRecordTempEntity> {
	
}
