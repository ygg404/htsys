package io.renren.modules.project.service;

import com.baomidou.mybatisplus.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.app.entity.UserEntity;
import io.renren.modules.project.entity.ProjectContractEntity;
import io.renren.modules.sys.vo.UserVoEntity;

import java.util.List;
import java.util.Map;

/**
 * 合同表
 *
 * @author ygg
 * @date 2019-10-30 15:40:10
 */
public interface ProjectContractService extends IService<ProjectContractEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<ProjectContractEntity> queryList(Map<String, Object> params);

    ProjectContractEntity getByContractNo(String contractNo);

    void save(ProjectContractEntity entity);

    void update(ProjectContractEntity entity);

    void deleteBatch(Long[] roleIds);

    String getMaxContractNo();
}

