package io.renren.modules.ren.vo;

import io.renren.modules.ren.entity.RenKbiAuditEntity;

import java.io.Serializable;
import java.util.List;

/**
 * 审定后的效能工资复合对象
 */
public class RenKbiAuditVoEntity implements Serializable {

    /**
     * 年度
     */
    private Long year;
    /**
     * 月份
     */
    private Long month;
    /**
     * 审定后的效能分
     */
    private List<RenKbiAuditEntity> kbiAuditList;

    /**
     * 获取：年度
     */
    public Long getYear (){return this.year;};
    /**
     * 设置：年度
     */
    public void setYear (Long year){this.year = year;};
    /**
     * 获取：月份
     */
    public Long getMonth (){return this.month;};
    /**
     * 设置：月份
     */
    public void setMonth (Long month){this.month = month;};
    /**
     * 获取：审定后的效能分
     */
    public List<RenKbiAuditEntity> getKbiAuditList() {return this.kbiAuditList;}
    /**
     * 设置：审定后的效能分
     */
    public void setKbiAuditList(List<RenKbiAuditEntity> kbiAuditList) { this.kbiAuditList = kbiAuditList;}



}
