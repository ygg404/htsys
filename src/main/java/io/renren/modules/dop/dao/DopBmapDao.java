package io.renren.modules.dop.dao;

import com.baomidou.mybatisplus.plugins.Page;
import io.renren.modules.dop.entity.DopBmapEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 地图标注表
 * 
 * @author ygg
 * @date 2020-09-24 10:26:01
 */
@Mapper
public interface DopBmapDao extends BaseMapper<DopBmapEntity> {
    /**
     * 获取所有父类ID
     * @return
     */
    List<Long> getMapPIdList(Page<DopBmapEntity> pagination, Map<String, Object> params);

    /**
     * 获取所有父类ID
     * @return
     */
    List<DopBmapEntity> getMapChildList(List<Long> pIdList);
}
