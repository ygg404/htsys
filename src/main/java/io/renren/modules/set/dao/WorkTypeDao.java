package io.renren.modules.set.dao;


import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import io.renren.modules.set.entity.WorkTypeEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 作业类型表
 * 
 * @author ygg
 * @date 2019-10-29 11:52:46
 */
@Mapper
public interface WorkTypeDao extends BaseMapper<WorkTypeEntity> {
    /**
     * 分页查询
     */
    List<WorkTypeEntity> getWorkTypePage(Page<WorkTypeEntity> pagination, Map<String, Object> params);

    /**
     * 通过项目类型 获取 工作类型
     * @param projectTypeId
     * @return
     */
    List<WorkTypeEntity> getListByPtypeId(Long projectTypeId);
}
