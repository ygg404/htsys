package io.renren.modules.finance.service;

import io.renren.modules.finance.vo.SettlementMoneyVoEntity;

import java.util.List;

public interface SettlementMoneyService {
    //通过合同编号获得对应的金额结算
    SettlementMoneyVoEntity GetSettlementByContractNo(String ContractNo);
}
