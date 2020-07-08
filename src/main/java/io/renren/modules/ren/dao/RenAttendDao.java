package io.renren.modules.ren.dao;

import io.renren.modules.ren.entity.RenAttendEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 出勤表
 * 
 * @author ygg
 * @date 2020-03-10 10:10:43
 */
@Mapper
public interface RenAttendDao extends BaseMapper<RenAttendEntity> {
	
}
