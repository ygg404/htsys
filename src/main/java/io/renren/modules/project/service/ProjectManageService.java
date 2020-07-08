package io.renren.modules.project.service;

import com.baomidou.mybatisplus.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.project.vo.ProjectVoEntity;

import java.util.List;
import java.util.Map;

/**
 * 项目管理
 *
 * @author ygg
 * @date 2019-11-01 16:46:10
 */
public interface ProjectManageService extends IService<ProjectVoEntity> {
    /**
     * 项目管理分页查询
     * @param params
     * @return
     */
    PageUtils getProjectManagPage(Map<String, Object> params);

    /**
     * 项目管理列表
     * @param params
     * @return
     */
    List<ProjectVoEntity> getProjectManagList(Map<String, Object> params);
}