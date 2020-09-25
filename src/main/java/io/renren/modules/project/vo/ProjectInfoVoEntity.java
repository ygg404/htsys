package io.renren.modules.project.vo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;


/**
 * 项目基本信息
 */
public class ProjectInfoVoEntity implements Serializable {
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
     * 委托要求
     */
    private String projectNote;
    /**
     * 审定内容
     */
    private String examineNote;
    /**
     * 业务负责人
     */
    private String projectProduce;
    /**
     * 业务负责人
     */
    private String contractBusiness;
    /**
     * 项目立项人
     */
    private String projectWriter;
    /**
     * 项目负责人
     */
    private String projectCharge;
    /**
     * 项目类型
     */
    private String projectType;
    /**
     * 预计产值备注
     */
    private String outputRemark;
    /**
     * 联系人电话
     */
    private String userPhone;
    /**
     * 联系人名称
     */
    private String userName;
    /**
     * 执行标准
     */
    private String executeStandard;
    /**
     * 作业内容
     */
    private String workNote;
    /**
     * 技术要求
     */
    private String workRequire;
    /**
     * 安全要求
     */
    private String safeRequire;
    /**
     * 项目开工时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
    private Date projectBegunDateTime;
    /**
     * 作业工期
     */
    private Float projectWorkDate;
    /**
     * 质检工期
     */
    private Float projectQualityDate;
    /**
     * 上交资料
     */
    private String dataName;
    /**
     * 工作小结
     */
    private String briefSummary;
    /**
     * 技术交底内容
     */
    private String technicalDisclosureNote;
    /**
     * 过程检查意见
     */
    private String checkSuggestion;
    /**
     * 项目预计产值
     */
    private String projectOutput;
    /**
     * 工作量
     */
    private String projectWorkload;
    /**
     * 作业工作量
     */
    private String workLoad;
    /**
     * 过程检查意见
     */
    private String finalCheckSuggestion;
    /**
     * 质量综述
     */
    private String qualityNote;
    /**
     * 质检评分
     */
    private Float qualityScore;
    /**
     * 创建的用户ID
     */
    private Long createuserid;
    /**
     * 项目创建用户名（立项人）
     */
    private String createUserName;
    /**
     * 最短工期
     */
    private Float shortDateTime;
    /**
     * 最迟工期
     */
    private Float lastDateTime;
    /**
     * 工作组名
     */
    private String groupName;
    /**
     * 点位中误差
     */
    private Float errorPoint;
    /**
     * 间距中误差
     */
    private Float errorSpace;
    /**
     * 高程中误差
     */
    private Float errorHeigh;

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
     * 获取：审定内容
     */
    public String getexamineNote (){return this.examineNote;};
    /**
     * 设置：审定内容
     */
    public void setexamineNote (String examineNote){this.examineNote = examineNote;};
    /**
     * 获取：委托单位
     */
    public String getprojectAuthorize (){return this.projectAuthorize;};
    /**
     * 设置：委托单位
     */
    public void setprojectAuthorize (String projectAuthorize){this.projectAuthorize = projectAuthorize;};
    /**
     * 获取：委托要求
     */
    public String getprojectNote (){return this.projectNote;};
    /**
     * 设置：委托要求
     */
    public void setprojectNote (String projectNote){this.projectNote = projectNote;};
    /**
     * 获取：生产负责人
     */
    public String getprojectProduce (){return this.projectProduce;};
    /**
     * 设置：生产负责人
     */
    public void setprojectProduce (String projectProduce){this.projectProduce = projectProduce;};
    /**
     * 获取：业务负责人
     */
    public String getcontractBusiness (){return this.contractBusiness;};
    /**
     * 设置：业务负责人
     */
    public void setcontractBusiness (String contractBusiness){this.contractBusiness = contractBusiness;};
    /**
     * 获取：项目立项人
     */
    public String getprojectWriter (){return this.projectWriter;};
    /**
     * 设置：项目立项人
     */
    public void setprojectWriter (String projectWriter){this.projectWriter = projectWriter;};
    /**
     * 获取：项目负责人
     */
    public String getprojectCharge (){return this.projectCharge;};
    /**
     * 设置：项目负责人
     */
    public void setprojectCharge (String projectCharge){this.projectCharge = projectCharge;};
    /**
     * 获取：作业工期
     */
    public Float getprojectWorkDate (){return this.projectWorkDate;};
    /**
     * 设置：作业工期
     */
    public void setprojectWorkDate (Float projectWorkDate){this.projectWorkDate = projectWorkDate;};
    /**
     * 获取：技术交底内容
     */
    public String gettechnicalDisclosureNote (){return this.technicalDisclosureNote;};
    /**
     * 设置：技术交底内容
     */
    public void settechnicalDisclosureNote (String technicalDisclosureNote){this.technicalDisclosureNote = technicalDisclosureNote;};
    /**
     * 获取：过程检查意见
     */
    public String getcheckSuggestion (){return this.checkSuggestion;};
    /**
     * 设置：过程检查意见
     */
    public void setcheckSuggestion (String checkSuggestion){this.checkSuggestion = checkSuggestion;};
    /**
     * 获取：质检工期
     */
    public Float getprojectQualityDate (){return this.projectQualityDate;};
    /**
     * 设置：质检工期
     */
    public void setprojectQualityDate (Float projectQualityDate){this.projectQualityDate = projectQualityDate;};
    /**
     * 获取：项目类型
     */
    public String getprojectType (){return this.projectType;};
    /**
     * 设置：项目类型
     */
    public void setprojectType (String projectType){this.projectType = projectType;};
    /**
     * 获取：联系人电话
     */
    public String getuserPhone (){return this.userPhone;};
    /**
     * 设置：联系人电话
     */
    public void setuserPhone (String userPhone){this.userPhone = userPhone;};
    /**
     * 获取：联系人名称
     */
    public String getuserName (){return this.userName;};
    /**
     * 设置：联系人名称
     */
    public void setuserName (String userName){this.userName = userName;};
    /**
     * 获取：执行标准
     */
    public String getexecuteStandard (){return this.executeStandard;};
    /**
     * 设置：执行标准
     */
    public void setexecuteStandard (String executeStandard){this.executeStandard = executeStandard;};
    /**
     * 获取：作业内容
     */
    public String getworkNote (){return this.workNote;};
    /**
     * 设置：作业内容
     */
    public void setworkNote (String workNote){this.workNote = workNote;};
    /**
     * 获取：技术要求
     */
    public String getworkRequire (){return this.workRequire;};
    /**
     * 设置：技术要求
     */
    public void setworkRequire (String workRequire){this.workRequire = workRequire;};
    /**
     * 获取：安全技术交底
     */
    public String getSafeRequire (){return this.safeRequire;};
    /**
     * 设置：安全技术交底
     */
    public void setSafeRequire (String safeRequire) {this.safeRequire = safeRequire;};
    /**
     * 获取：项目开工时间
     */
    public Date getprojectBegunDateTime (){return this.projectBegunDateTime;};
    /**
     * 设置：项目开工时间
     */
    public void setprojectBegunDateTime (Date projectBegunDateTime){this.projectBegunDateTime = projectBegunDateTime;};
    /**
     * 获取：上交资料
     */
    public String getdataName (){return this.dataName;};
    /**
     * 设置：上交资料
     */
    public void setdataName (String dataName){this.dataName = dataName;};
    /**
     * 获取：工作小结
     */
    public String getbriefSummary (){return this.briefSummary;};
    /**
     * 设置：工作小结
     */
    public void setbriefSummary (String briefSummary){this.briefSummary = briefSummary;};
    /**
     * 获取：项目预计产值
     */
    public String getprojectOutput (){return this.projectOutput;};
    /**
     * 设置：项目预计产值
     */
    public void setprojectOutput (String projectOutput){this.projectOutput = projectOutput;};
    /**
     * 获取：预计产值备注
     */
    public String getoutputRemark (){return this.outputRemark;};
    /**
     * 设置：预计产值备注
     */
    public void setoutputRemark (String outputRemark){this.outputRemark = outputRemark;};
    /**
     * 获取：工作量
     */
    public String getprojectWorkload (){return this.projectWorkload;};
    /**
     * 设置：工作量
     */
    public void setprojectWorkload (String projectWorkload){this.projectWorkload = projectWorkload;};
    /**
     * 获取：作业工作量
     */
    public String getworkLoad (){return this.workLoad;};
    /**
     * 设置：作业工作量
     */
    public void setworkLoad (String workLoad){this.workLoad = workLoad;};
    /**
     * 获取：过程检查意见
     */
    public String getfinalCheckSuggestion (){return this.finalCheckSuggestion;};
    /**
     * 设置：过程检查意见
     */
    public void setfinalCheckSuggestion (String finalCheckSuggestion){this.finalCheckSuggestion = finalCheckSuggestion;};
    /**
     * 获取：质量综述
     */
    public String getqualityNote (){return this.qualityNote;};
    /**
     * 设置：质量综述
     */
    public void setqualityNote (String qualityNote){this.qualityNote = qualityNote;};
    /**
     * 获取：质检评分
     */
    public Float getqualityScore (){return this.qualityScore;};
    /**
     * 设置：质检评分
     */
    public void setqualityScore (Float qualityScore){this.qualityScore = qualityScore;};
    /**
     * 获取：创建的用户ID
     */
    public Long getcreateuserid (){return this.createuserid;};
    /**
     * 设置：创建的用户ID
     */
    public void setcreateuserid (Long createuserid){this.createuserid = createuserid;};
    /**
     * 获取：创建的用户名
     */
    public String getcreateUserName (){return this.createUserName;};
    /**
     * 设置：创建的用户名
     */
    public void setcreateUserName (String createUserName){this.createUserName = createUserName;};
    /**
     * 获取：最短工期
     */
    public Float getshortDateTime (){return this.shortDateTime;};
    /**
     * 设置：最短工期
     */
    public void setshortDateTime (Float shortDateTime){this.shortDateTime = shortDateTime;};
    /**
     * 获取：最迟工期
     */
    public Float getlastDateTime (){return this.lastDateTime;};
    /**
     * 设置：最迟工期
     */
    public void setlastDateTime (Float lastDateTime){this.lastDateTime = lastDateTime;};
    /**
     * 获取：工作组名
     */
    public String getGroupName (){return this.groupName;};
    /**
     * 设置：工作组名
     */
    public void setGroupName (String groupName){this.groupName = groupName;};
    /**
     * 获取：点位中误差
     */
    public Float getErrorPoint (){return this.errorPoint;};
    /**
     * 设置：点位中误差
     */
    public void setErrorPoint (Float errorPoint){this.errorPoint = errorPoint;};
    /**
     * 获取：间距中误差
     */
    public Float getErrorSpace (){return this.errorSpace;};
    /**
     * 设置：间距中误差
     */
    public void setErrorSpace (Float errorSpace){this.errorSpace = errorSpace;};
    /**
     * 获取：高程中误差
     */
    public Float getErrorHeigh (){return this.errorHeigh;};
    /**
     * 设置：高程中误差
     */
    public void setErrorHeigh (Float errorHeigh){this.errorHeigh = errorHeigh;};
}
