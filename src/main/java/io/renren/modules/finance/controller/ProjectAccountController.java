package io.renren.modules.finance.controller;

import java.util.*;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import io.renren.common.annotation.SysLog;

import io.renren.modules.finance.service.FinanceOperationService;

import io.renren.modules.finance.service.SettlementMoneyService;
import io.renren.modules.finance.vo.FinanceOperationVoEntity;
import io.renren.modules.finance.vo.SettlementMoneyVoEntity;
import io.renren.modules.project.entity.ProjectContractEntity;

import io.renren.modules.sys.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.renren.modules.finance.entity.ProjectAccountEntity;
import io.renren.modules.finance.service.ProjectAccountService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;
import io.renren.modules.project.service.ProjectContractService;
/**
 * 财务操作表
 */
@RestController
@RequestMapping("finance/account")
public class ProjectAccountController {
    @Autowired
    private ProjectAccountService projectAccountService;
    @Autowired
    private ProjectContractService projectContractService;
    @Autowired
    private FinanceOperationService financeoperationService;
    @Autowired
    private SettlementMoneyService settlementMoneyService;
    @Autowired
    private SysUserService sysUserService;

    //收费统计数据列表
    @RequestMapping("/getFinanceList")
    public R GetChargeChartList(@RequestParam Map<String, Object> params) {
        List<FinanceOperationVoEntity> list = financeoperationService.getFinanceList(params);
        return R.ok().put("list", list);
    }

    @RequestMapping("/getFinanceOperationPage")
    public R GetFinanceOperationPage(@RequestParam Map<String, Object> params) {
        PageUtils page = financeoperationService.getFinanceOperationPage(params);
        //需要修改
        return R.ok().put("page", page);
    }


    //查看该合同的财务操作列表  (参数: 合同编号)
    @RequestMapping("GetAccountListByContract/{ContractNo}")
    public R info(@PathVariable("ContractNo") String ContractNo) {
        Map<String, Object> params = new HashMap<>();
        params.put("contract_no", ContractNo);
        //操作列表
        PageUtils page = projectAccountService.getProjectAccountByContractPage(params);
        return R.ok().put("page", page);
    }

    //查看具体的某次财务操作(参数: 财务操作 id)
    @RequestMapping("GetSpecificAccountById/{id}")
    public R SpecificAccountinfo(@PathVariable("id") Long id) {
        ProjectAccountEntity projectAccount = projectAccountService.selectById(id);
        ProjectContractEntity PCEntity = projectContractService.selectOne(
                new EntityWrapper<ProjectContractEntity>().eq("contract_no", projectAccount.getcontractNo())
        );
        if (PCEntity != null) {
            //设置合同名称
            projectAccount.setContractName(PCEntity.getcontractName());
        }
        return R.ok().put("specicontract_businessficprojectAccount", projectAccount);
    }


    @SysLog("保存财务操作")
    @RequestMapping("/save")
    public R save(@RequestBody ProjectAccountEntity projectAccount) {

        if (projectAccount.getaccountType().equals("0")) {
            projectAccount.setaccountTypeName("收款");
        }
        if (projectAccount.getaccountType().equals("1")) {
            projectAccount.setaccountTypeName("支出");
        }
        projectAccountService.save(projectAccount);
        // projectAccount.setCreateDateTime(new Date());
        return R.ok();
    }


    @SysLog("修改财务操作")
    @RequestMapping("/update")
    public R update(@RequestBody ProjectAccountEntity projectAccount) {
        projectAccountService.updateById(projectAccount);
        return R.ok();
    }

    //
//    @SysLog("通过合同编号查看财务操作的金额结算")
    @RequestMapping("GetSettlementMoneyByContractNo/{ContractNo}")
    public R GetSettlementMoneyByContractNo(@PathVariable("ContractNo") String ContractNo) {
        SettlementMoneyVoEntity SMVEntity = settlementMoneyService.GetSettlementByContractNo(ContractNo);
        return R.ok().put("settlementMoneyVoEntity", SMVEntity);
    }

//    @SysLog("通过合同编号查看合同信息")
    @RequestMapping("getContractByContractNo/{ContractNo}")
    public R GetContractByContractNo(@PathVariable("ContractNo") String ContractNo) {
       ProjectContractEntity PCEntity =  projectContractService.selectOne(new EntityWrapper<ProjectContractEntity>().eq("contract_no",ContractNo) );
       // SettlementMoneyVoEntity SMVEntity = settlementMoneyService.GetSettlementByContractNo(ContractNo);
       return R.ok().put("ContractInfo", PCEntity);
    }

    //删除具体的某次财务操作(参数: 财务操作 id)
    @SysLog("删除具体的某次财务操作")
    @RequestMapping("/delete")
    public R delete(@RequestBody ProjectAccountEntity projectAccount) {
        projectAccountService.deleteById(projectAccount.getId());
        return R.ok();
    }

    //合同的业务负责人列表
//    @SysLog("合同的业务负责人列表")
    @RequestMapping("/getContractBusinessList")
    public R getContractBusiness() {
        List<String> list =  projectAccountService.getBusinessList();
       return R.ok().put("list", list);
    }


}