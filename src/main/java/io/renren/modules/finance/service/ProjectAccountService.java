package io.renren.modules.finance.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.finance.entity.ProjectAccountEntity;
import io.renren.modules.project.entity.ProjectContractEntity;

import java.util.List;
import java.util.Map;

/**
 * 财务操作表
 *
 * @author ygg
 * @date 2019-10-31 11:18:50
 */
public interface ProjectAccountService extends IService<ProjectAccountEntity> {

    PageUtils queryPage(Map<String, Object> params);

    void save(ProjectAccountEntity entity);

    void update(ProjectAccountEntity entity);

    void deleteBatch(Long[] roleIds);

    List<ProjectAccountEntity> GetDataListByContractNo(String  ContractNo);

    PageUtils getProjectAccountByContractPage(Map<String, Object> params);

    Page<ProjectContractEntity> getProjectContractInfoPage(Map<String, Object> params);

    List<String> getBusinessList();
}

