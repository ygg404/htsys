package io.renren.modules.dop.dao;

import io.renren.modules.dop.entity.DopBmapEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 地图标注表
 * 
 * @author ygg
 * @date 2020-09-24 10:26:01
 */
@Mapper
public interface DopBmapDao extends BaseMapper<DopBmapEntity> {
	
}
