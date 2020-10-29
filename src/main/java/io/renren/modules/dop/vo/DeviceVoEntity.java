package io.renren.modules.dop.vo;

import com.baomidou.mybatisplus.annotations.TableId;

import java.io.Serializable;
import java.util.Date;

public class DeviceVoEntity implements Serializable {

    /**
     * 自增Id
     */
    private Long id;
    /**
     * 仪器Id
     */
    private Long deviceId;
    /**
     * 出厂编号
     */
    private String factoryNum;
    /**
     * 仪器名称
     */
    private String deviceName;
    /**
     * 设备状况（0-闲置中；1-出借中；2-维修中）
     */
    private Long devStation;
    /**
     * 借物人Id
     */
    private Long borrowerId;
    /**
     * 借物人名称
     */
    private String borrowerName;
    /**
     * 出借人Id
     */
    private Long lenderId;
    /**
     * 出借人名称
     */
    private String lenderName;
    /**
     * 预借天数
     */
    private Long lendDays;
    /**
     * 出借情况（0-撤销申请,1-申请出借,2-批准出借,3-归还仪器, 4-确定归还）
     */
    private Long rentStatus;
    /**
     * 备注
     */
    private String remark;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 流程ID
     */
    private String processId;

    /**
     * 获取：自增Id
     */
    public Long getId (){return this.id;};
    /**
     * 设置：自增Id
     */
    public void setId (Long id){this.id = id;};
    /**
     * 获取：仪器Id
     */
    public Long getDeviceId (){return this.deviceId;};
    /**
     * 获取：出厂编号
     */
    public String getFactoryNum (){return this.factoryNum;};
    /**
     * 设置：出厂编号
     */
    public void setFactoryNum (String factoryNum){this.factoryNum = factoryNum;};
    /**
     * 获取：设备状况（0-闲置中；1-出借中；2-维修中）
     */
    public Long getDevStation (){return this.devStation;};
    /**
     * 设置：设备状况（0-闲置中；1-出借中；2-维修中）
     */
    public void setDevStation (Long devStation){this.devStation = devStation;};
    /**
     * 获取：仪器名称
     */
    public String getDeviceName (){return this.deviceName;};
    /**
     * 设置：仪器名称
     */
    public void setDeviceName (String deviceName){this.deviceName = deviceName;};
    /**
     * 设置：仪器Id
     */
    public void setDeviceId (Long deviceId){this.deviceId = deviceId;};
    /**
     * 获取：借物人Id
     */
    public Long getBorrowerId (){return this.borrowerId;};
    /**
     * 设置：借物人Id
     */
    public void setBorrowerId (Long borrowerId){this.borrowerId = borrowerId;};
    /**
     * 获取：借物人名称
     */
    public String getBorrowerName (){return this.borrowerName;};
    /**
     * 设置：借物人名称
     */
    public void setBorrowerName (String borrowerName){this.borrowerName = borrowerName;};
    /**
     * 获取：出借人Id
     */
    public Long getLenderId (){return this.lenderId;};
    /**
     * 设置：出借人Id
     */
    public void setLenderId (Long lenderId){this.lenderId = lenderId;};
    /**
     * 获取：出借人名称
     */
    public String getLenderName (){return this.lenderName;};
    /**
     * 设置：出借人名称
     */
    public void setLenderName (String lenderName){this.lenderName = lenderName;};
    /**
     * 获取：预借天数
     */
    public Long getLendDays (){return this.lendDays;};
    /**
     * 设置：预借天数
     */
    public void setLendDays (Long lendDays){this.lendDays = lendDays;};
    /**
     * 获取：出借情况（1-已出借；2-已归还）
     */
    public Long getRentStatus (){return this.rentStatus;};
    /**
     * 设置：出借情况（1-已出借；2-已归还）
     */
    public void setRentStatus (Long rentStatus){this.rentStatus = rentStatus;};
    /**
     * 获取：备注
     */
    public String getRemark (){return this.remark;};
    /**
     * 设置：备注
     */
    public void setRemark (String remark){this.remark = remark;};
    /**
     * 获取：创建时间
     */
    public Date getCreateTime (){return this.createTime;};
    /**
     * 设置：创建时间
     */
    public void setCreateTime (Date createTime){this.createTime = createTime;};
    /**
     * 获取：流程ID
     */
    public String getProcessId() {return this.processId;}
    /**
     * 设置： 流程ID
     */
    public void setProcessId(String processId) {this.processId = processId;}
}
