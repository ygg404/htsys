package io.renren.modules.project.dao;

import io.renren.modules.project.entity.ProjectGroupEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import io.renren.modules.project.vo.ProjectGroupVoEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 项目安排分组
 * 
 * @author ygg
 * @date 2019-11-01 16:46:10
 */
@Mapper
public interface ProjectGroupDao extends BaseMapper<ProjectGroupEntity> {

    List<ProjectGroupVoEntity> getProjectDataCoe();

    List<ProjectGroupVoEntity> getChartCollect(Map<String, Object> params);
}
