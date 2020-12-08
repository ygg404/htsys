package io.renren.modules.project.service;

import com.baomidou.mybatisplus.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.project.entity.ProjectBillEntity;


import java.util.Map;
import java.util.List;

/**
 * 项目清单表
 *
 * @author ygg
 * @date 2020-12-08 11:07:29
 */
public interface ProjectBillService extends IService<ProjectBillEntity> {

    List<ProjectBillEntity> queryList(Map<String, Object> params);

    void deleteByProNo(String projectNo);

    void save(ProjectBillEntity entity);

    void update(ProjectBillEntity entity);

    void deleteBatch(Long[] Ids);
}

