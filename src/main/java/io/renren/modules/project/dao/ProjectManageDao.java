package io.renren.modules.project.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import io.renren.modules.project.entity.ProjectPlanEntity;
import io.renren.modules.project.vo.ProjectVoEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 项目管理
 *
 * @author ygg
 * @date 2019-11-01 16:46:10
 */
@Mapper
public interface ProjectManageDao extends BaseMapper<ProjectVoEntity> {
    /**
     * 分页查询
     */
    List<ProjectVoEntity> getProjectManagPage(Page<ProjectVoEntity> pagination, Map<String, Object> params);

    /**
     * 列表
     */
    List<ProjectVoEntity> getProjectManagPage(Map<String, Object> params);
}

