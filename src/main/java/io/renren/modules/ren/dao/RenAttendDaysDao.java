package io.renren.modules.ren.dao;

import io.renren.modules.ren.entity.RenAttendDaysEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 应出勤天数表
 * 
 * @author ygg
 * @date 2020-03-10 15:32:12
 */
@Mapper
public interface RenAttendDaysDao extends BaseMapper<RenAttendDaysEntity> {
	
}
