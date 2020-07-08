package io.renren.modules.finance.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.common.utils.R;
import io.renren.modules.project.entity.ProjectContractEntity;
import io.renren.modules.project.entity.ProjectEntity;
import io.renren.modules.project.service.ProjectContractService;
import io.renren.modules.set.service.WorkProjectTypeService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.finance.dao.ProjectAccountDao;
import io.renren.modules.finance.entity.ProjectAccountEntity;
import io.renren.modules.finance.service.ProjectAccountService;
import org.springframework.transaction.annotation.Transactional;


@Service("projectAccountService")
public class ProjectAccountServiceImpl extends ServiceImpl<ProjectAccountDao, ProjectAccountEntity> implements ProjectAccountService {

    @Autowired
    public ProjectAccountService projectAccountService;
    @Autowired
    public ProjectContractService projectContractService;


    public PageUtils getProjectAccountByContractPage(Map<String, Object> params) {
        //project_contract (合同)数据
        //MyError  需要完善
        String key = (String) params.get("contract_no");
        Page<ProjectAccountEntity> page = projectAccountService.selectPage(
                new Query<ProjectAccountEntity>(params).getPage(),
                new EntityWrapper<ProjectAccountEntity>().eq(StringUtils.isNotBlank(key), "contract_no", key)
        );
        //设置合同名称
        List<ProjectAccountEntity> PCEList = page.getRecords();
       for(ProjectAccountEntity item : PCEList){
           ProjectContractEntity PCEntity = projectContractService.selectOne(
                   new EntityWrapper<ProjectContractEntity>().eq("contract_no", item.getcontractNo())
           );
           if(PCEntity != null){
               item.setContractName(PCEntity.getcontractName());
           }
       }
        return new PageUtils(page);
    }

    @Override
    public Page<ProjectContractEntity> getProjectContractInfoPage(Map<String, Object> params){
        String key = (String)params.get("key");
        String sidx = (String)params.get("sidx");
        String startDate = (String)params.get("startDate");
        String endDate = (String)params.get("endDate");
        String projectType = (String)params.get("projectType");
        boolean isAsc = params.get("order").equals("asc");
        Page<ProjectContractEntity> page = projectContractService.selectPage(
                new Query<ProjectContractEntity>(params).getPage(),
                new EntityWrapper<ProjectContractEntity>().like(StringUtils.isNotBlank(key),"contract_name", key)
                        .like(StringUtils.isNotBlank(projectType), "project_type", projectType)
                        .and(StringUtils.isNotBlank(startDate) , "contract_add_time>={0}",startDate)
                        .and(StringUtils.isNotBlank(endDate) , "contract_add_time<={0}",endDate)
                        .orderBy(sidx,isAsc )
        );

        return page;
    }

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String key = (String)params.get("key");

        Page<ProjectAccountEntity> page = this.selectPage(
                new Query<ProjectAccountEntity>(params).getPage(),
                new EntityWrapper<ProjectAccountEntity>().like(StringUtils.isNotBlank(key),"id", key)
        );

        //财务操作数据列表
        for(ProjectAccountEntity PAEntity : page.getRecords()){
               //根据 合同编号 找到对应的 合同数据
            ProjectContractEntity entity = projectContractService.selectOne(
                    new EntityWrapper<ProjectContractEntity>().eq("contract_no", PAEntity.getcontractNo())
            );
               //设置财务操作数据中的合同名称
            if(entity != null)PAEntity.setContractName(entity.getcontractName());
        }

        return new PageUtils(page);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<String> getBusinessList(){
        return this.baseMapper.getBusinessList();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(ProjectAccountEntity entity) {
        this.insert(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(ProjectAccountEntity entity) {
        this.updateById(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteBatch(Long[] roleIds) {
        //删除
        this.deleteBatchIds(Arrays.asList(roleIds));
    }

    @Override
   public List<ProjectAccountEntity> GetDataListByContractNo(String ContractNo){
        Map<String, Object> params = new HashMap<>();
        params.put("contract_no" , ContractNo);
        List<ProjectAccountEntity> list = this.selectByMap(params);
        return list;
    }
}