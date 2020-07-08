package io.renren.modules.finance.service.impl;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;
import io.renren.common.utils.R;
import io.renren.modules.finance.dao.FinanceOperationDao;
import io.renren.modules.finance.dao.ProjectAccountDao;
import io.renren.modules.finance.entity.ProjectAccountEntity;
import io.renren.modules.finance.service.FinanceOperationService;

import io.renren.modules.finance.service.ProjectAccountService;
import io.renren.modules.finance.vo.FinanceOperationVoEntity;
import io.renren.modules.project.entity.ProjectContractEntity;
import io.renren.modules.project.service.ProjectContractService;
import io.renren.modules.project.vo.ProjectVoEntity;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service("FinanceOperationService")
public class FinanceOperationServiceImpl  extends ServiceImpl<FinanceOperationDao, FinanceOperationVoEntity> implements FinanceOperationService {

    //结算 操作
    @Autowired
    ProjectAccountService projectAccountService;

    //合同
    @Autowired
    ProjectContractService projectContractService;

    @Override
    public List<FinanceOperationVoEntity> getFinanceList(Map<String,Object>params){

        Page<FinanceOperationVoEntity> pagenation = new Page<FinanceOperationVoEntity>();
        pagenation.setSize(new Long(this.baseMapper.getContractCount()).intValue()  );
        return this.baseMapper.getFinanceOperationList(pagenation ,params);
    }

    @Override
    public PageUtils getFinanceOperationPage(Map<String,Object>params){
        Page<FinanceOperationVoEntity> pagnation = new Query<FinanceOperationVoEntity>(params).getPage();
        pagnation = pagnation.setRecords( baseMapper.getFinanceOperationList(pagnation , params ) );
        return new PageUtils(pagnation);
//        int curpage = Integer.parseInt((String)params.get("page"));
//        int pagesize = Integer.parseInt((String)params.get("limit"));
//        Page<FinanceOperationVoEntity> Respage = new Page<FinanceOperationVoEntity>();
//        List<FinanceOperationVoEntity> list = this.baseMapper.getFinanceOperationList(params);
//        Respage.setRecords(list);
//        PageUtils ResPageUtils = new PageUtils(Respage);
//        //总记录数
//        ResPageUtils.setTotalCount(list.size());
//        //每页固定记录数
//        ResPageUtils.setPageSize(pagesize);
//        //当前页
//        ResPageUtils.setCurrPage(curpage);
//        int totalpage = list.size() % pagesize == 0 ? list.size() / pagesize:list.size() / pagesize + 1;
//        ResPageUtils.setTotalPage(totalpage);
//        return ResPageUtils;
    }

}
