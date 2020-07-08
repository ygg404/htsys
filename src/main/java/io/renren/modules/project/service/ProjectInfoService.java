package io.renren.modules.project.service;

import com.baomidou.mybatisplus.service.IService;
import io.renren.modules.project.vo.ProjectInfoVoEntity;

/**
 * 项目基本信息
 *
 * @author ygg
 * @date 2019-11-01 16:46:10
 */
public interface ProjectInfoService extends IService<ProjectInfoVoEntity> {
    /**
     * 通过项目编号 获取项目有基本信息
     * @param projectNo
     * @return
     */
    ProjectInfoVoEntity getInfoByProjectNo(String projectNo);

    /**
     * 通过项目编号 获取项目打印信息
     * @param projectNo
     * @return
     */
    ProjectInfoVoEntity getPrintByProjectNo(String projectNo);
}