package io.renren.modules.finance.service.impl;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import io.renren.modules.finance.entity.ProjectAccountEntity;
import io.renren.modules.finance.service.ProjectAccountService;
import io.renren.modules.finance.service.SettlementMoneyService;
import io.renren.modules.finance.vo.SettlementMoneyVoEntity;
import io.renren.modules.project.entity.ProjectContractEntity;
import io.renren.modules.project.service.ProjectContractService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("SettlementMoneyService")
public class SettlementMoneyServiceImpl implements SettlementMoneyService {
@Autowired
ProjectAccountService projectAccountService;
@Autowired
    ProjectContractService projectContractService;
    //通过合同编号获得对应的金额结算
    @Override
    public SettlementMoneyVoEntity GetSettlementByContractNo(String ContractNo){

        List<ProjectAccountEntity> PAList = projectAccountService.GetDataListByContractNo(ContractNo);
        ProjectContractEntity PCEntity = projectContractService.selectOne(
                new EntityWrapper<ProjectContractEntity>().eq("contract_no",ContractNo )
        );

        float Receivable = 0f; //应收
        float ActuallyReceipts = 0f; //实收
        float NotReceipts = 0f; //已收
        float Expenditure = 0f; //支出
        String ProjectName = null;
        if(PCEntity != null){
            //设置应收  (合同金额)
            Receivable = PCEntity.getcontractMoney();
            //设置该结算的项目名称
            ProjectName = PCEntity.getcontractName();
        }
        for(ProjectAccountEntity PAEntity: PAList){
            //支出
             if(PAEntity.getaccountType().equals("1")){
                Expenditure += PAEntity.getaccountNum();
            }
            //已收
            if(PAEntity.getaccountType().equals("0")){
                ActuallyReceipts += PAEntity.getaccountNum();
            }
        }
          //未收 = 应收 - 已收
        NotReceipts = Receivable - ActuallyReceipts;
        SettlementMoneyVoEntity SMVEntity = new SettlementMoneyVoEntity();
        SMVEntity.setReceivable(Receivable); //应收
        SMVEntity.setActuallyReceipts(ActuallyReceipts);  //实收
        SMVEntity.setNotReceipts(NotReceipts); //未收
        SMVEntity.setExpenditure(Expenditure); //支出

        SMVEntity.setProjectName(ProjectName);
        return SMVEntity;
    }

}
