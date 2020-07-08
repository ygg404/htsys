package io.renren.modules.project.vo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 项目视图类
 */
public class ProjectVoEntity implements Serializable {
    /**
     * ID
     */
    private Integer id;
    /**
     * 项目编号
     */
    private String projectNo;
    /**
     * 项目名称
     */
    private String projectName;
    /**
     * 委托单位
     */
    private String projectAuthorize;
    /**
     * 业务负责人
     */
    private String projectBusiness;
    /**
     * 项目类型
     */
    private String projectType;
    /**
     * 项目阶段
     */
    private Integer projectStage;
    /**
     * 项目生产人
     */
    private String projectProduce;
    /**
     * 项目生产人账号
     */
    private String projectProduceAccount;
    /**
     * 项目启动时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
    private Date projectStartDateTime;
    /**
     * 创建的用户ID
     */
    private Long createuserid;
    /**
     * 项目负责人
     */
    private String projectCharge;
    /**
     * 项目负责人账号
     */
    private String projectChargeAccount;
    /**
     * 进度内容
     */
    private String scheduleNote;
    /**
     * 进度百分比
     */
    private Integer scheduleRate;
    /**
     * 作业工期
     */
    private Float projectWorkDate;
    /**
     * 质检工期
     */
    private Float projectQualityDate;
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
     * 暂停时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    private Date suspendTime;
    /**
     * 暂停缘由
     */
    private String suspendExcuse;
    /**
     * 暂停天数
     */
    private Long suspendDay;
    /**
     * 是否安排（0:未安排，1:已经安排）
     */
    private Long isPlan;
    /**
     * 是否工作（0:未工作，1:已工作）
     */
    private Long isWork;
    /**
     * 是否质检（0:未质检，1:已质检）
     */
    private Long isCheck;
    /**
     * 是否质审核（0:未质审，1:已质审）
     */
    private Long isQauth;
    /**
     * 是否核算（0:未核算，1:已核算）
     */
    private Long isOutput;
    /**
     * 是否审核（0:未审核，1:已审核）
     */
    private Long isAuthorize;
    /**
     * 返修ID号
     */
    private Long backId;
    /**
     * 提交内容
     */
    private String submitNote;
    /**
     * 返修天数
     */
    private Long backDateNum;
    /**
     * 作业人员是否是 项目负责人
     */
    private Long isCharge;


    /**
     * 获取：
     */
    public Integer getid (){return this.id;};
    /**
     * 设置：
     */
    public void setid (Integer id){this.id = id;};
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
     * 获取：委托单位
     */
    public String getprojectAuthorize (){return this.projectAuthorize;};
    /**
     * 设置：委托单位
     */
    public void setprojectAuthorize (String projectAuthorize){this.projectAuthorize = projectAuthorize;};
    /**
     * 获取：业务负责人
     */
    public String getprojectBusiness (){return this.projectBusiness;};
    /**
     * 设置：业务负责人
     */
    public void setprojectBusiness (String projectBusiness){this.projectBusiness = projectBusiness;};
    /**
     * 获取：项目类型
     */
    public String getprojectType (){return this.projectType;};
    /**
     * 设置：项目类型
     */
    public void setprojectType (String projectType){this.projectType = projectType;};
    /**
     * 获取：项目阶段
     */
    public Integer getprojectStage (){return this.projectStage;};
    /**
     * 设置：项目阶段
     */
    public void setprojectStage (Integer projectStage){this.projectStage = projectStage;};
    /**
     * 获取：项目生产人
     */
    public String getprojectProduce (){return this.projectProduce;};
    /**
     * 设置：项目生产人
     */
    public void setprojectProduce (String projectProduce){this.projectProduce = projectProduce;};
    /**
     * 获取：项目生产人账号
     */
    public String getprojectProduceAccount (){return this.projectProduceAccount;};
    /**
     * 设置：项目生产人账号
     */
    public void setprojectProduceAccount (String projectChargeAccount){this.projectProduceAccount = projectChargeAccount;};
    /**
     * 获取：项目开始时间
     */
    public Date getprojectStartDateTime (){return this.projectStartDateTime;};
    /**
     * 设置：项目开始时间
     */
    public void setprojectStartDateTime (Date projectStartDateTime){this.projectStartDateTime = projectStartDateTime;};
    /**
     * 获取：创建的用户ID
     */
    public Long getcreateuserid (){return this.createuserid;};
    /**
     * 设置：创建的用户ID
     */
    public void setcreateuserid (Long createuserid){this.createuserid = createuserid;};
    /**
     * 获取：项目负责人
     */
    public String getprojectCharge (){return this.projectCharge;};
    /**
     * 设置：项目负责人
     */
    public void setprojectCharge (String projectCharge){this.projectCharge = projectCharge;};
    /**
     * 获取：项目负责人账号
     */
    public String getprojectChargeAccount (){return this.projectChargeAccount;};
    /**
     * 设置：项目负责人账号
     */
    public void setprojectChargeAccount (String projectChargeAccount){this.projectChargeAccount = projectChargeAccount;};
    /**
     * 获取：进度内容
     */
    public String getscheduleNote (){return this.scheduleNote;};
    /**
     * 设置：进度内容
     */
    public void setscheduleNote (String scheduleNote){this.scheduleNote = scheduleNote;};
    /**
     * 获取：进度百分比
     */
    public Integer getscheduleRate (){return this.scheduleRate;};
    /**
     * 设置：进度百分比
     */
    public void setscheduleRate (Integer scheduleRate){this.scheduleRate = scheduleRate;};
    /**
     * 获取：项目开工时间
     */
    public Date getprojectBegunDateTime (){return this.projectBegunDateTime;};
    /**
     * 设置：项目开工时间
     */
    public void setprojectBegunDateTime (Date projectBegunDateTime){this.projectBegunDateTime = projectBegunDateTime;};
    /**
     * 获取：作业工期
     */
    public Float getprojectWorkDate (){return this.projectWorkDate;};
    /**
     * 设置：作业工期
     */
    public void setprojectWorkDate (Float projectWorkDate){this.projectWorkDate = projectWorkDate;};
    /**
     * 获取：质检工期
     */
    public Float getprojectQualityDate (){return this.projectQualityDate;};
    /**
     * 设置：质检工期
     */
    public void setprojectQualityDate (Float projectQualityDate){this.projectQualityDate = projectQualityDate;};
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
    /**
     * 获取：暂停时间
     */
    public Date getsuspendTime (){return this.suspendTime;};
    /**
     * 设置：暂停时间
     */
    public void setsuspendTime (Date suspendTime){this.suspendTime = suspendTime;};
    /**
     * 获取：暂停缘由
     */
    public String getsuspendExcuse (){return this.suspendExcuse;};
    /**
     * 设置：暂停缘由
     */
    public void setsuspendExcuse (String suspendExcuse){this.suspendExcuse = suspendExcuse;};
    /**
     * 获取：暂停天数
     */
    public Long getsuspendDay (){return this.suspendDay;};
    /**
     * 设置：暂停天数
     */
    public void setsuspendDay (Long suspendDay){this.suspendDay = suspendDay;};
    /**
     * 获取：是否安排（0:未安排，1:已经安排）
     */
    public Long getisPlan (){return this.isPlan;};
    /**
     * 设置：是否安排（0:未安排，1:已经安排）
     */
    public void setisPlan (Long isPlan){this.isPlan = isPlan;};
    /**
     * 获取：是否工作（0:未工作，1:已工作）
     */
    public Long getisWork (){return this.isWork;};
    /**
     * 设置：是否工作（0:未工作，1:已工作）
     */
    public void setisWork (Long isWork){this.isWork = isWork;};
    /**
     * 获取：是否质检（0:未质检，1:已质检）
     */
    public Long getisCheck (){return this.isCheck;};
    /**
     * 设置：是否质检（0:未质检，1:已质检）
     */
    public void setisCheck (Long isCheck){this.isCheck = isCheck;};
    /**
     * 获取：是否质审（0:未质审，1:已质审）
     */
    public Long getisQauth (){return this.isQauth;};
    /**
     * 设置：是否质审（0:未质检，1:已质审）
     */
    public void setisQauth (Long isQauth){this.isQauth = isQauth;};
    /**
     * 获取：是否核算（0:未核算，1:已核算）
     */
    public Long getisOutput (){return this.isOutput;};
    /**
     * 设置：是否核算（0:未核算，1:已核算）
     */
    public void setisOutput (Long isOutput){this.isOutput = isOutput;};
    /**
     * 获取：是否审核（0:未审核，1:已审核）
     */
    public Long getisAuthorize (){return this.isAuthorize;};
    /**
     * 设置：是否审核（0:未审核，1:已审核）
     */
    public void setisAuthorize (Long isAuthorize){this.isAuthorize = isAuthorize;};
    /**
     *  获取： 返修ID号
     */
    public Long getbackId (){return this.backId;}
    /**
     *  设置： 返修ID号
     */
    public void setbackId (Long backId){this.backId = backId;}
    /**
     * 获取：提交内容
     */
    public String getsubmitNote (){return this.submitNote;};
    /**
     * 设置：提交内容
     */
    public void setsubmitNote (String submitnote){this.submitNote = submitnote;};
    /**
     * 获取：返修天数
     */
    public Long getbackDateNum (){return this.backDateNum;};
    /**
     * 设置：返修天数
     */
    public void setbackDateNum (Long backdatenum){this.backDateNum = backdatenum;};
    /**
     * 获取：作业人员是否是 项目负责人
     */
    public Long getIsCharge () { return this.isCharge;}
    /**
     * 设置：作业人员是否是 项目负责人
     */
    public void setIsCharge (Long isCharge) { this.isCharge = isCharge;}
}
