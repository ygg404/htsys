package io.renren.modules.project.vo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

public class ChartOutputVoEntity implements Serializable {
    /**
     * id
     */
    private Long Id;
    /**
     * 项目编号
     */
    private String projectNo;
    /**
     * 项目名称
     */
    private String projectName;
    /**
     * 项目启动时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
    private Date projectStartDateTime;
    /**
     * 组id
     */
    private Long groupId;
    /**
     * 组名
     */
    private String groupName;
    /**
     * 项目预算产值
     */
    private Float projectOutput;
    /**
     * 项目负责人
     */
    private String projectCharge;
    /**
     * 项目实际产值
     */
    private Float projectActuallyOutput;
    /**
     * 项目开工时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
    private Date projectBegunDateTime;
    /**
     * 作业完成时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
    private Date wFinishDateTime;
    /**
     * 质检完成时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
    private Date qFinishDateTime;
    /**
     * 结算时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
    private Date cutOffTime;
    /**
     * 项目状态
     */
    private Long projectStatus;

    /**
     * 获取：id
     */
    public Long getId (){return this.Id;};
    /**
     * 设置：id
     */
    public void setId (Long Id){this.Id = Id;};
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
     * 获取：组id
     */
    public Long getgroupId (){return this.groupId;};
    /**
     * 设置：组id
     */
    public void setgroupId (Long groupId){this.groupId = groupId;};
    /**
     * 获取：组名
     */
    public String getgroupName (){return this.groupName;};
    /**
     * 设置：组名
     */
    public void setgroupName (String groupName){this.groupName = groupName;};
    /**
     * 获取：项目负责人
     */
    public String getProjectCharge (){return this.projectCharge;};
    /**
     * 设置：项目负责人
     */
    public void setProjectCharge (String projectCharge){this.projectCharge = projectCharge;};
    /**
     * 获取：项目启动时间
     */
    public Date getprojectStartDateTime (){return this.projectStartDateTime;};
    /**
     * 设置：项目开工时间
     */
    public void setprojectStartDateTime (Date projectStartDateTime){this.projectStartDateTime = projectStartDateTime;};
    /**
     * 获取：项目预算产值
     */
    public Float getprojectOutput (){return this.projectOutput;};
    /**
     * 设置：项目预算产值
     */
    public void setprojectOutput (Float projectOutput){this.projectOutput = projectOutput;};
    /**
     * 获取：项目实际产值
     */
    public Float getprojectActuallyOutput (){return this.projectActuallyOutput;};
    /**
     * 设置：项目实际产值
     */
    public void setprojectActuallyOutput (Float projectActuallyOutput){this.projectActuallyOutput = projectActuallyOutput;};
    /**
     * 获取：项目开工时间
     */
    public Date getprojectBegunDateTime (){return this.projectBegunDateTime;};
    /**
     * 设置：项目开工时间
     */
    public void setprojectBegunDateTime (Date projectBegunDateTime){this.projectBegunDateTime = projectBegunDateTime;};
    /*
    /**
     * 获取：作业完成时间
     */
    public Date getwFinishDateTime (){return this.wFinishDateTime;};
    /**
     * 设置：作业完成时间
     */
    public void setwFinishDateTime (Date wfinishDateTime){this.wFinishDateTime = wfinishDateTime;};
    /**
     * 获取：质检完成时间
     */
    public Date getqFinishDateTime (){return this.qFinishDateTime;};
    /**
     * 设置：结束时间
     */
    public void setqFinishDateTime (Date qfinishDateTime){this.qFinishDateTime = qfinishDateTime;};
    /**
     * 获取：结算时间
     */
    public Date getCutOffTime (){return this.cutOffTime;};
    /**
     * 设置：结算时间
     */
    public void setCutOffTime (Date cutOffTime){this.cutOffTime = cutOffTime;};
    /**
     * 获取：项目状态
     */
    public Long getProjectStatus (){return this.projectStatus;};
    /**
     * 设置：项目状态
     */
    public void setProjectStatus (Long projectStatus){this.projectStatus = projectStatus;};
}
