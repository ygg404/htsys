package io.renren.modules.project.service.impl;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.modules.project.dao.ProjectInfoDao;
import io.renren.modules.project.service.ProjectInfoService;
import io.renren.modules.project.vo.ProjectInfoVoEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("ProjectInfoService")
public class ProjectInfoServiceImpl extends ServiceImpl<ProjectInfoDao, ProjectInfoVoEntity> implements ProjectInfoService  {
    /**
     * 获取项目基本信息实体类
     * @param projectNo
     * @return
     */
    @Override
    public ProjectInfoVoEntity getInfoByProjectNo(String projectNo){
        ProjectInfoVoEntity entity = baseMapper.getInfoByProjectNo( projectNo );
        return entity;
    }

    /**
     * 获取项目打印信息实体类
     * @param projectNo
     * @return
     */
    @Override
    public List<ProjectInfoVoEntity> getPrintByProjectNo(String projectNo){
        List<ProjectInfoVoEntity> list = baseMapper.getPrintByProjectNo( projectNo );
        return list;
    }
}
