package io.renren.modules.project.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import io.renren.common.utils.PageUtils;
import io.renren.modules.project.entity.ProjectArchivesEntity;
import io.renren.modules.project.vo.ProjectArchivesVoEntity;
import io.renren.modules.project.vo.RecycleVoEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 项目成果表
 * 
 * @author ygg
 * @date 2020-09-18 10:56:51
 */
@Mapper
public interface ProjectArchivesVoDao extends BaseMapper<ProjectArchivesVoEntity> {

    /**
     * 分页查询
     * @param pagination：mybatisPlus 原生分页查询，查询SQL会自动拼接分页
     * @param params：QueryWrapper条件，注意，这里需要使用 @Param("params") 指定mybatis参数
     * @return , Wrapper<ProjectArchivesVoEntity> queryWrapper
     */
    List<ProjectArchivesVoEntity> getArchivesVoList(Page<ProjectArchivesVoEntity> pagination, Map<String, Object> params);

    /**
     * 查询
     * @param params
     * @return
     */
    ProjectArchivesVoEntity getArchivesVo(Map<String, Object> params);
}
