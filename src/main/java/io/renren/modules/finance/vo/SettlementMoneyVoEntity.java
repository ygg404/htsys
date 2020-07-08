package io.renren.modules.finance.vo;

import java.io.Serializable;

//结算类型
public class SettlementMoneyVoEntity implements Serializable{

    //项目名称
    private String ProjectName;
    //应收
    private Float Receivable;
    //实收
    private Float ActuallyReceipts;
    //未收
    private Float NotReceipts;
    //支出
    private Float Expenditure;

    public String getProjectName() {
        return ProjectName;
    }

    public void setProjectName(String projectName) {
        ProjectName = projectName;
    }

    public Float getReceivable() {
        return Receivable;
    }

    public void setReceivable(Float receivable) {
        Receivable = receivable;
    }

    public Float getActuallyReceipts() {
        return ActuallyReceipts;
    }

    public void setActuallyReceipts(Float actuallyReceipts) {
        ActuallyReceipts = actuallyReceipts;
    }

    public Float getNotReceipts() {
        return NotReceipts;
    }

    public void setNotReceipts(Float notReceipts) {
        NotReceipts = notReceipts;
    }

    public Float getExpenditure() {
        return Expenditure;
    }

    public void setExpenditure(Float expenditure) {
        Expenditure = expenditure;
    }
}
