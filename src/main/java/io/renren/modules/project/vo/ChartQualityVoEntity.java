package io.renren.modules.project.vo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 质量统计表
 */
public class ChartQualityVoEntity implements Serializable {
    /**
     * ID
     */
    private Long id;
    /**
     * 项目编号
     */
    private String projectNo;
    /**
     * 项目名称
     */
    private String projectName;
    /**
     * 项目负责人
     */
    private String projectCharge;
    /**
     * 质检评分
     */
    private Float qualityScore;
    /**
     * 质检提交时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
    private Date finishDateTime;
    /**
     *  组ID
     */
    private Long groupId;
    /**
     *  组名
     */
    private String groupName;
    /**
     * 质检用户
     */
    private String qualityUseraccount;
    /**
     * 质检用户名
     */
    private String qualityUserName;
    /**
     * 质检审核账号
     */
    private String qualityConfirmaccount;
    /**
     * 质检审核用户名
     */
    private String qualityConfirmName;

    /**
     * 获取：
     */
    public Long getId (){return this.id;};
    /**
     * 设置：
     */
    public void setId (Long id){this.id = id;};
    /**
     * 获取：项目编号
     */
    public String getprojectNo (){return this.projectNo;};
    /**
     * 设置：项目编号
     */
    public void setprojectNo (String projectNo){this.projectNo = projectNo;};
    /**
     * 获取：项目名称
     */
    public String getprojectName (){return this.projectName;};
    /**
     * 设置：项目名称
     */
    public void setprojectName (String projectName){this.projectName = projectName;};
    /**
     * 获取：项目负责人
     */
    public String getprojectCharge (){return this.projectCharge;};
    /**
     * 设置：项目负责人
     */
    public void setprojectCharge (String projectCharge){this.projectCharge = projectCharge;};
    /**
     * 获取：
     */
    public Long getgroupId (){return this.groupId;};
    /**
     * 设置：
     */
    public void setgroupId (Long groupid){this.groupId = groupid;};
    /**
     * 获取：组名
     */
    public String getgroupName (){return this.groupName;};
    /**
     * 设置：组名
     */
    public void setgroupName (String groupName){this.groupName = groupName;};
    /**
     * 获取：质检评分
     */
    public Float getqualityScore (){return this.qualityScore;};
    /**
     * 设置：质检评分
     */
    public void setqualityScore (Float qualityScore){this.qualityScore = qualityScore;};
    /**
     * 获取：质检提交时间
     */
    public Date getfinishDateTime (){return this.finishDateTime;};
    /**
     * 设置：质检提交时间
     */
    public void setfinishDateTime (Date finishDateTime){this.finishDateTime = finishDateTime;};
    /**
     * 获取：质检用户
     */
    public String getqualityUseraccount (){return this.qualityUseraccount;};
    /**
     * 设置：质检用户
     */
    public void setqualityUseraccount (String qualityUseraccount){this.qualityUseraccount = qualityUseraccount;};
    /**
     * 获取：质检用户名
     */
    public String getqualityUserName (){return this.qualityUserName;};
    /**
     * 设置：质检用户名
     */
    public void setqualityUserName (String qualityUserName){this.qualityUserName = qualityUserName;};
    /**
     * 获取：质检审核账号
     */
    public String getqualityConfirmaccount (){return this.qualityConfirmaccount;};
    /**
     * 设置：质检审核账号
     */
    public void setqualityConfirmaccount (String qualityConfirmaccount){this.qualityConfirmaccount = qualityConfirmaccount;};
    /**
     * 获取：质检审核用户名
     */
    public String getqualityConfirmName (){return this.qualityConfirmName;};
    /**
     * 设置：质检审核用户名
     */
    public void setqualityConfirmName (String qualityConfirmName){this.qualityConfirmName = qualityConfirmName;};
}
