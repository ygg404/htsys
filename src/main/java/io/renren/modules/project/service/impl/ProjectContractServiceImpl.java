package io.renren.modules.project.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import io.renren.common.utils.StringUtil;
import io.renren.modules.app.entity.UserEntity;
import io.renren.modules.app.service.UserService;
import io.renren.modules.project.dao.ProjectContractDao;
import io.renren.modules.project.entity.ProjectContractEntity;
import io.renren.modules.project.entity.ProjectEntity;
import io.renren.modules.project.service.ProjectContractService;
import io.renren.modules.project.service.ProjectService;
import io.renren.modules.sys.dao.SysUserDao;
import io.renren.modules.sys.entity.SysUserRoleEntity;
import io.renren.modules.sys.service.SysUserRoleService;
import io.renren.modules.sys.service.SysUserService;
import io.renren.modules.sys.vo.UserVoEntity;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;
import org.springframework.transaction.annotation.Transactional;


@Service("projectContractService")
public class ProjectContractServiceImpl extends ServiceImpl<ProjectContractDao, ProjectContractEntity> implements ProjectContractService {

    @Autowired
    public ProjectService projectService;

    @Autowired
    public SysUserRoleService sysUserRoleService;

    @Autowired
    public SysUserService sysUserService;

    @Autowired
    public SysUserDao sysUserDao;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String key = (String)params.get("key");
        String startDate = (String)params.get("startDate");
        String endDate = (String)params.get("endDate");
        boolean isAsc = params.get("order").equals("asc");
        Page<ProjectContractEntity> page = this.selectPage(
                new Query<ProjectContractEntity>(params).getPage(),
                new EntityWrapper<ProjectContractEntity>()
                .and(StringUtils.isNotBlank(startDate) , "contract_add_time>={0}",startDate)
                .and(StringUtils.isNotBlank(endDate) , "contract_add_time<={0}",endDate)
                .andNew(StringUtils.isNotBlank(key), "contract_name like {0} or contract_no like {0} or contract_authorize like {0}" ,
                        "%" + key.trim() + "%")
                .orderBy("id",isAsc )
        );
        //查找合同对应的项目
        for ( ProjectContractEntity entity: page.getRecords() ) {
            Map<String, Object> parms = new HashMap<>();
            parms.put("contractNo", entity.getcontractNo());
            parms.put("pStage", "1");
            entity.setprojectList( projectService.queryList(parms) );
        }
        return new PageUtils(page);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<ProjectContractEntity> queryList(Map<String, Object> params) {
        String key = (String)params.get("key");
        String startDate = (String)params.get("startDate");
        String endDate = (String)params.get("endDate");
        String order = (String)params.get("order");
        String sidx = (String)params.get("sidx");
        //业务负责人
        String contractBusiness = (String)params.get("business");

        List<ProjectContractEntity> list = this.selectList(
                new EntityWrapper<ProjectContractEntity>().like(StringUtils.isNotBlank(key),"contract_name", key )
                        .and(StringUtils.isNotBlank(startDate), "contract_add_time>={0}", startDate)
                        .and(StringUtils.isNotBlank(endDate), "contract_add_time<={0}", endDate)
                        .eq(StringUtils.isNotBlank(contractBusiness), "contract_business", contractBusiness)
                        .orderBy( sidx,order.equals("asc"))
        );
        return  list;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ProjectContractEntity getByContractNo(String contractNo){
        return this.selectOne(new EntityWrapper<ProjectContractEntity>().eq("contract_no", contractNo));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(ProjectContractEntity entity) {
        entity.setcontractStage(1);
        entity.setcontractCreateTime( new Date());
        this.insert(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(ProjectContractEntity entity) {
        this.updateById(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteBatch(Long[] roleIds) {
        //删除
        this.deleteBatchIds(Arrays.asList(roleIds));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String getMaxContractNo(){
        //获取当前年月
        Calendar cal = Calendar.getInstance();
        int month = cal.get(Calendar.MONTH) + 1;
        int year = cal.get(Calendar.YEAR);
        String ymKey = String.valueOf(year) + String.format("%02d", month);
        //获取当前年月的合同编号列表
        List<ProjectContractEntity> list = this.selectList(
          new EntityWrapper<ProjectContractEntity>().like( StringUtils.isNotBlank(ymKey),"contract_no", ymKey)
        );
        int maxsize = list.size();
        // 查找最大的合同编号后两位数
        for (ProjectContractEntity contractEntity : list){
            int cursize = Integer.parseInt(contractEntity.getcontractNo().replace(ymKey , ""));
            if(cursize > maxsize){
                maxsize = cursize;
            }
        }
        return ymKey + String.format("%02d", maxsize + 1);
    }
}