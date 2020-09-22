package io.renren.modules.project.service;

import com.baomidou.mybatisplus.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.project.vo.ProjectArchivesVoEntity;
import io.renren.modules.project.vo.RecycleVoEntity;

import java.util.Map;

/**
 * 项目成果分页表
 *
 * @author ygg
 * @date 2019-11-01 16:46:10
 */
public interface ProjectArchivesVoService extends IService<ProjectArchivesVoEntity> {
    /**
     * 项目成果分页查询
     * @param params
     * @return
     */
    PageUtils getArchivesVoPage(Map<String, Object> params);
}
