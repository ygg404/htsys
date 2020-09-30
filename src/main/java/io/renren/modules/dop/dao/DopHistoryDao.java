package io.renren.modules.dop.dao;

import io.renren.modules.dop.entity.DopHistoryEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 仪器租赁情况表
 * 
 * @author ygg
 * @date 2020-09-28 10:38:16
 */
@Mapper
public interface DopHistoryDao extends BaseMapper<DopHistoryEntity> {
	
}
