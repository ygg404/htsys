package io.renren.modules.project.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import io.renren.modules.project.vo.ProjectInfoVoEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 项目基本信息
 *
 * @author ygg
 * @date 2019-11-01 16:46:10
 */
@Mapper
public interface ProjectInfoDao extends BaseMapper<ProjectInfoVoEntity> {

    ProjectInfoVoEntity getInfoByProjectNo(String projectNo);

    ProjectInfoVoEntity getPrintByProjectNo(String projectNo);
}
