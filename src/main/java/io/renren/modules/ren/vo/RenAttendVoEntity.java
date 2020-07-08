package io.renren.modules.ren.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.renren.modules.ren.entity.RenAttendDaysEntity;
import io.renren.modules.ren.entity.RenAttendEntity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class RenAttendVoEntity implements Serializable {

    /**
     * 用户Id
     */
    private Long userId;
    /**
     * 用户名
     */
    private String userName;
    /**
     * 部门ID
     */
    private Long branchId;
    /**
     * 部门名称
     */
    private String branchName;
    /**
     * 外业天数
     */
    private Float outDay;
    /**
     * 内业天数
     */
    private Float inDay;
    /**
     * 加班天数
     */
    private Float overtime;
    /**
     * 请假天数
     */
    private Float leave;
    /**
     * 合计出勤天数
     */
    private Float allDay;
    /**
     * 病假日期（某日）
     */
    private String sicklist;
    /**
     * 事假日期（某日）
     */
    private String affairlist;
    /**
     * 年假日期（某日）
     */
    private String annuallist;
    /**
     * 产假日期（某日）
     */
    private String babylist;
    /**
     * 陪产假日期（某日）
     */
    private String paternlist;
    /**
     * 婚假日期（某日）
     */
    private String maritallist;
    /**
     * 备注
     */
    private String remark;
    /**
     * 出勤月份
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM")
    private Date attendTime;

    /**
     * 获取：用户Id
     */
    public Long getuserId (){return this.userId;};
    /**
     * 设置：用户Id
     */
    public void setuserId (Long userId){this.userId = userId;};
    /**
     * 获取：用户名
     */
    public String getuserName (){return this.userName;};
    /**
     * 设置：用户名
     */
    public void setuserName (String userName){this.userName = userName;};
    /**
     * 获取：部门ID
     */
    public Long getBranchId (){return this.branchId;};
    /**
     * 设置：部门ID
     */
    public void setBranchId (Long branchId){this.branchId = branchId;};
    /**
     * 获取：部门名称
     */
    public String getBranchName (){return this.branchName;};
    /**
     * 设置：部门名称
     */
    public void setBranchName (String branchName){this.branchName = branchName;};
    /**
     * 获取：外业天数
     */
    public Float getoutDay (){return this.outDay;};
    /**
     * 设置：外业天数
     */
    public void setoutDay (Float outDay){this.outDay = outDay;};
    /**
     * 获取：内业天数
     */
    public Float getinDay (){return this.inDay;};
    /**
     * 设置：内业天数
     */
    public void setinDay (Float inDay){this.inDay = inDay;};
    /**
     * 获取：加班天数
     */
    public Float getovertime (){return this.overtime;};
    /**
     * 设置：加班天数
     */
    public void setovertime (Float overtime){this.overtime = overtime;};
    /**
     * 获取：请假天数
     */
    public Float getleave (){return this.leave;};
    /**
     * 设置：请假天数
     */
    public void setleave (Float leave){this.leave = leave;};
    /**
     * 获取：合计出勤天数
     */
    public Float getallDay (){return this.allDay;};
    /**
     * 设置：合计出勤天数
     */
    public void setallDay (Float allDay){this.allDay = allDay;};
    /**
     * 获取：病假日期（某日）
     */
    public String getsicklist (){return this.sicklist;};
    /**
     * 设置：病假日期（某日）
     */
    public void setsicklist (String sicklist){this.sicklist = sicklist;};
    /**
     * 获取：产假日期（某日）
     */
    public String getbabylist (){return this.babylist;};
    /**
     * 设置：产假日期（某日）
     */
    public void setbabylist (String babylist){this.babylist = babylist;};
    /**
     * 获取：事假日期（某日）
     */
    public String getaffairlist (){return this.affairlist;};
    /**
     * 设置：事假日期（某日）
     */
    public void setaffairlist (String affairlist){this.affairlist = affairlist;};
    /**
     * 获取：年假日期（某日）
     */
    public String getannuallist (){return this.annuallist;};
    /**
     * 设置：年假日期（某日）
     */
    public void setannuallist (String annuallist){this.annuallist = annuallist;};
    /**
     * 获取：陪产假日期（某日）
     */
    public String getpaternlist (){return this.paternlist;};
    /**
     * 设置：陪产假日期（某日）
     */
    public void setpaternlist (String paternlist){this.paternlist = paternlist;};
    /**
     * 获取：婚假日期（某日）
     */
    public String getmaritallist (){return this.maritallist;};
    /**
     * 设置：婚假日期（某日）
     */
    public void setmaritallist (String maritallist){this.maritallist = maritallist;};
    /**
     * 获取：备注
     */
    public String getremark (){return this.remark;};
    /**
     * 设置：备注
     */
    public void setremark (String remark){this.remark = remark;};
    /**
     * 获取：出勤月份
     */
    public Date getattendTime (){return this.attendTime;};
    /**
     * 设置：出勤月份
     */
    public void setattendTime (Date attendTime){this.attendTime = attendTime;};

}
