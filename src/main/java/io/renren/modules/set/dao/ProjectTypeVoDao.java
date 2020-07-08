package io.renren.modules.set.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import io.renren.modules.set.vo.ProjectTypeVoEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProjectTypeVoDao extends BaseMapper<ProjectTypeVoEntity> {

    /**
     * 获取项目和作业复合类列表
     */
    List<ProjectTypeVoEntity> getPtypeVoList();
}
