package io.renren.modules.dop.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import io.renren.modules.dop.entity.DopBmapProjectEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 百度地图项目名称
 * 
 * @author ygg
 * @date 2020-10-22 10:56:31
 */
@Mapper
public interface DopBmapProjectDao extends BaseMapper<DopBmapProjectEntity> {
	
}
