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
     * 上下半年(0-上半年，1-下半年 )
     */
    private Long updown;
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
     * 获取：上下半年(0-上半年，1-下半年 )
     */
    public Long getUpdown (){return this.updown;};
    /**
     * 设置：上下半年(0-上半年，1-下半年 )
     */
    public void setUpdown (Long updown){this.updown = updown;};
    /**
     * 获取：审定后的效能分
     */
    public List<RenKbiAuditEntity> getKbiAuditList() {return this.kbiAuditList;}
    /**
     * 设置：审定后的效能分
     */
    public void setKbiAuditList(List<RenKbiAuditEntity> kbiAuditList) { this.kbiAuditList = kbiAuditList;}



}
