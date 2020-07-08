package io.renren.modules.finance.vo;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;


import java.io.Serializable;
import java.util.Date;
import java.util.List;

//财务操作混合类
public class FinanceOperationVoEntity implements Serializable {

    //ID 合同 关联 project_contract 表 的 id
    private Long id;

    //对应的财务操作 ID List
    private List<Long> OperationIDList;

    //合同编号 关联 project_contract 表 的 contract_no
    private String contractNo;

    //合同名称  关联 project_contract 表 的 contract_name
    private String contractName;

    // 委托单位 关联 project_contract 表 的 contract_authorize
    private String contractAuthorize;

    //应收 数据库返回数据  关联 project_contract 表 的 contract_money
    private float contractMoney;

    //业务负责人 关联 project_contract 表 的 contract_business
    private String contractBusiness;

    //合同添加时间 关联 project_contract 表 的 contract_add_time
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
    private Date contractAddTime;

    //启动时间 合同创建时间  关联 project_contract 表 的 contract_create_time
    private Date contractCreateTime;

    //项目类型
    private String projectType;

    //联系人名称
    private String username;
    //联系人电话
    private String userphone;
    //实收  根据支出类型累计
    private float projectActuallyReceipts;

    //支出 根据支出类型累计
    private float projectExpenditure;

    //未收 应收 - 实收
    private float projectNotReceipts;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserphone() {
        return userphone;
    }

    public void setUserphone(String userphone) {
        this.userphone = userphone;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Long> getOperationIDList() {
        return OperationIDList;
    }

    public void setOperationIDList(List<Long> operationIDList) {
        OperationIDList = operationIDList;
    }

    public String getContractNo() {
        return contractNo;
    }

    public void setContractNo(String contractNo) {
        this.contractNo = contractNo;
    }

    public String getContractName() {
        return contractName;
    }

    public void setContractName(String contractName) {
        this.contractName = contractName;
    }

    public String getContractAuthorize() {
        return contractAuthorize;
    }

    public void setContractAuthorize(String contractAuthorize) {
        this.contractAuthorize = contractAuthorize;
    }

    public float getContractMoney() {
        return contractMoney;
    }

    public void setContractMoney(float contractMoney) {
        this.contractMoney = contractMoney;
    }

    public String getContractBusiness() {
        return contractBusiness;
    }

    public void setContractBusiness(String contractBusiness) {
        this.contractBusiness = contractBusiness;
    }

    public Date getContractAddTime() {
        return contractAddTime;
    }

    public void setContractAddTime(Date contractAddTime) {
        this.contractAddTime = contractAddTime;
    }

    public Date getContractCreateTime() {
        return contractCreateTime;
    }

    public void setContractCreateTime(Date contractCreateTime) {
        this.contractCreateTime = contractCreateTime;
    }

    public float getProjectActuallyReceipts() {
        return projectActuallyReceipts;
    }

    public void setProjectActuallyReceipts(float projectActuallyReceipts) {
        this.projectActuallyReceipts = projectActuallyReceipts;
    }

    public float getProjectExpenditure() {
        return projectExpenditure;
    }

    public void setProjectExpenditure(float projectExpenditure) {
        this.projectExpenditure = projectExpenditure;
    }

    public float getProjectNotReceipts() {
        return projectNotReceipts;
    }

    public void setProjectNotReceipts(float projectNotReceipts) {
        this.projectNotReceipts = projectNotReceipts;
    }

    public String getProjectType() {
        return projectType;
    }

    public void setProjectType(String projectType) {
        this.projectType = projectType;
    }
}
