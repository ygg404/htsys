package io.renren.modules.ren.vo;

import com.baomidou.mybatisplus.annotations.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.renren.modules.ren.entity.RenRecordEducationEntity;
import io.renren.modules.ren.entity.RenRecordEntity;
import io.renren.modules.ren.entity.RenRecordWorkEntity;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 成员档案复合类
 */
public class RenRecordVoEntity implements Serializable {
    /**
     * 用户id
     */
    private Long userId;
    /**
     * 用户账号
     */
    private String useraccount;
    /**
     * 用户名
     */
    private String username;
    /**
     * 身份证号
     */
    private String idNo;
    /**
     * 出生日期
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
    private Date birthday;
    /**
     * 入职时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
    private Date entryTime;
    /**
     * 性别（1-男；2-女）
     */
    private Long sex;
    /**
     * 工作类型（1-全职；2-兼职）
     */
    private Long jobType;
    /**
     * 住房类型（1-本地居民；2-租房）
     */
    private Long houseType;
    /**
     * 最高学历(0-无;1-小学;2-初中;3-中专/高中/职高;4-专科;5-本科;6-硕士研究生;7-博士研究生)
     */
    private Long education;
    /**
     * 学历名称
     */
    private String edName;
    /**
     * 教育类型
     */
    private Long educationType;
    /**
     * 教育类型名称
     */
    private String edTypeName;
    /**
     * 毕业时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
    private Date educationTime;
    /**
     * 专业系数
     */
    private Long proRatio;
    /**
     * 专业系数名称
     */
    private String proName;
    /**
     * 职务id
     */
    private Long dutyId;
    /**
     * 职务名称
     */
    private String dutyName;
    /**
     * 职称等级
     */
    private Long titleLever;
    /**
     * 职称专业系数
     */
    private Long titlePro;
    /**
     * 职称专业相关名称
     */
    private String titleProname;
    /**
     * 职称名称
     */
    private String titleName;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 手机号
     */
    private String mobile;
    /**
     * 试用期（月）
     */
    private Long trialPeriod;
    /**
     * 籍贯（省）
     */
    private String nativeProvince;
    /**
     * 籍贯（市）
     */
    private String nativeCity;
    /**
     * 婚姻状况(0-未婚；1-已婚；2-离异；3-丧偶)
     */
    private Long maritalStatus;
    /**
     * 头像
     */
    private String headImg;
    /**
     * 学历基准分
     */
    private Float edScore;
    /**
     * 学制基准分
     */
    private Float edTypescore;
    /**
     * 学历专业相关基准分
     */
    private Float edProscore;
    /**
     * 职称基准分
     */
    private Float titleScore;
    /**
     * 职称专业相关分
     */
    private Float titleProscore;
    /**
     * 职务基准分
     */
    private Long dutyScore;
    /**
     * 职务的效能基准分
     */
    private Float dutyStandScore;
    /**
     * 职务的未考核效能基准分
     */
    private Float dutyAssessScore;
    /**
     * 职务的试用期效能基准分
     */
    private Float dutyTrialScore;
    /**
     * 职称的效能基准分
     */
    private Float titleStandScore;
    /**
     * 职称的未考核效能基准分
     */
    private Float titleAssessScore;
    /**
     * 职称的试用期效能基准分
     */
    private Float titleTrialScore;
    /**
     * 审定后的效能基准分
     */
    private Long kbiAuditScore;


    /**
     * 是否审核（0-未审核; 1-已审核；2-审核不通过;）
     */
    private Long isAudit;
    /**
     * 审核驳回内容
     */
    private String auditMsg;
    /**
     * 个人档案
     */
    private RenRecordEntity renRecordEntity;
    /**
     * 教育背景列表
     */
    private List<RenRecordEducationEntity> edBackgroundList;
    /**
     * 工作经验列表
     */
    private List<RenRecordWorkEntity> workBackgroundList;


    /**
     * 获取：用户id
     */
    public Long getuserId (){return this.userId;};
    /**
     * 设置：用户id
     */
    public void setuserId (Long userId){this.userId = userId;};
    /**
     * 设置：账号
     *
     * @param useraccount 账号
     */
    public void setUseraccount(String useraccount) {
        this.useraccount = useraccount;
    }

    /**
     * 获取：账号
     *
     * @return String
     */
    public String getUseraccount() {
        return useraccount;
    }

    /**
     * 设置：用户名
     *
     * @param username 用户名
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * 获取：用户名
     *
     * @return String
     */
    public String getUsername() {
        return username;
    }
    /**
     * 获取：身份证号
     */
    public String getidNo (){return this.idNo;};
    /**
     * 设置：身份证号
     */
    public void setidNo (String idNo){this.idNo = idNo;};
    /**
     * 获取：出生日期
     */
    public Date getbirthday (){return this.birthday;};
    /**
     * 设置：出生日期
     */
    public void setbirthday (Date birthday){this.birthday = birthday;};
    /**
     * 获取：入职时间
     */
    public Date getentryTime (){return this.entryTime;};
    /**
     * 设置：入职时间
     */
    public void setentryTime (Date entryTime){this.entryTime = entryTime;};
    /**
     * 获取：职务id
     */
    public Long getDutyId (){return this.dutyId;};
    /**
     * 设置：职务id
     */
    public void setDutyId (Long dutyId){this.dutyId = dutyId;};
    /**
     * 获取：职务
     */
    public String getDutyName (){return this.dutyName;};
    /**
     * 设置：职务
     */
    public void setDutyName (String dutyName){this.dutyName = dutyName;};
    /**
     * 获取：性别（1-男；2-女）
     */
    public Long getSex (){return this.sex;};
    /**
     * 设置：性别（1-男；2-女）
     */
    public void setSex (Long sex){this.sex = sex;};
    /**
     * 获取：工作类型（1-全职；2-兼职）
     */
    public Long getjobType (){return this.jobType;};
    /**
     * 设置：工作类型（1-全职；2-兼职）
     */
    public void setjobType (Long jobType){this.jobType = jobType;};
    /**
     * 获取：住房类型（1-本地居民；2-租房）
     */
    public Long getHouseType (){return this.houseType;};
    /**
     * 设置：住房类型（1-本地居民；2-租房）
     */
    public void setHouseType (Long houseType){this.houseType = houseType;};
    /**
     * 获取：最高学历(0-无;1-小学;2-初中;3-中专/高中/职高;4-专科;5-本科;6-硕士研究生;7-博士研究生)
     */
    public Long geteducation (){return this.education;};
    /**
     * 设置：最高学历(0-无;1-小学;2-初中;3-中专/高中/职高;4-专科;5-本科;6-硕士研究生;7-博士研究生)
     */
    public void seteducation (Long education){this.education = education;};
    /**
     * 获取：最高学历名称
     */
    public String getEdName (){return this.edName;};
    /**
     * 设置：最高学历名称
     */
    public void setEdName (String edName){this.edName = edName;};
    /**
     * 获取：教育类型
     */
    public Long getEducationType (){return this.educationType;};
    /**
     * 设置：教育类型
     */
    public void setEducationType (Long educationType){this.educationType = educationType;};
    /**
     * 获取：教育类型名称
     */
    public String getEdTypeName (){return this.edTypeName;};
    /**
     * 设置：教育类型名称
     */
    public void setEdTypeName (String edTypeName){this.edTypeName = edTypeName;};
    /**
     * 获取：毕业时间
     */
    public Date getEducationTime (){return this.educationTime;};
    /**
     * 设置：毕业时间
     */
    public void setEducationTime (Date educationTime){this.educationTime = educationTime;};
    /**
     * 获取：专业系数
     */
    public Long getProRatio (){return this.proRatio;};
    /**
     * 设置：专业系数
     */
    public void setProRatio (Long proRatio){this.proRatio = proRatio;}
    /**
     * 获取：专业系数
     */
    public String getProName (){return this.proName;};
    /**
     * 设置：专业系数
     */
    public void setProName (String proName){this.proName = proName;};
    /**
     * 获取： 职称专业相关名称
     */
    public String getTitleProname() {return  this.titleProname;};
    /**
     * 设置： 职称专业相关名称
     */
    public void setTitleProname(String titleProname) {this.titleProname = titleProname;};
    /**
     * 获取： 职称基准分
     */
    public Float getTitleScore(){ return this.titleScore;};
    /**
     * 设置：  职称基准分
     */
    public void setTitleScore(Float titleScore){ this.titleScore = titleScore;};
    /**
     *  获取：职称专业相关分
     */
    public Float getTitleProscore() { return this.titleProscore;};
    /**
     *  设置：职称专业相关分
     */
    public void setTitleProscore(Float titleProscore) { this.titleProscore = titleProscore;}
    /**
     * 获取：职称等级(0-无;1-技术员;2-助理工程师;3-中级工程师;4-高级工程师;5-正高级工程师)
     */
    public Long gettitleLever (){return this.titleLever;};
    /**
     * 设置：职称等级(0-无;1-技术员;2-助理工程师;3-中级工程师;4-高级工程师;5-正高级工程师)
     */
    public void settitleLever (Long titleLever){this.titleLever = titleLever;};
    /**
     * 获取：职称专业系数
     */
    public Long getTitlePro(){ return this.titlePro; }
    /**
     * 设置：职称专业系数
     */
    public void setTitlePro (Long titlePro){this.titlePro = titlePro;};
    /**
     * 获取：职称名称
     */
    public String getTitleName (){return this.titleName;};
    /**
     * 设置：职称名称
     */
    public void setTitleName (String titleName){this.titleName = titleName;};
    /**
     * 获取：邮箱
     */
    public String getemail (){return this.email;};
    /**
     * 设置：邮箱
     */
    public void setemail (String email){this.email = email;};
    /**
     * 获取：手机号
     */
    public String getmobile (){return this.mobile;};
    /**
     * 设置：手机号
     */
    public void setmobile (String mobile){this.mobile = mobile;};
    /**
     * 获取：试用期（月）
     */
    public Long gettrialPeriod (){return this.trialPeriod;};
    /**
     * 设置：试用期（月）
     */
    public void settrialPeriod (Long trialPeriod){this.trialPeriod = trialPeriod;};
    /**
     * 获取：籍贯（省）
     */
    public String getnativeProvince (){return this.nativeProvince;};
    /**
     * 设置：籍贯（省）
     */
    public void setnativeProvince (String nativeProvince){this.nativeProvince = nativeProvince;};
    /**
     * 获取：籍贯（市）
     */
    public String getnativeCity (){return this.nativeCity;};
    /**
     * 设置：籍贯（市）
     */
    public void setnativeCity (String nativeCity){this.nativeCity = nativeCity;};
    /**
     * 获取：婚姻状况(0-未婚；1-已婚；2-离异；3-丧偶)
     */
    public Long getmaritalStatus (){return this.maritalStatus;};
    /**
     * 设置：婚姻状况(0-未婚；1-已婚；2-离异；3-丧偶)
     */
    public void setmaritalStatus (Long maritalStatus){this.maritalStatus = maritalStatus;};
    /**
     * 获取：头像
     */
    public String getheadImg (){return this.headImg;};
    /**
     * 设置：头像
     */
    public void setheadImg (String headImg){this.headImg = headImg;};
    /**
     * 获取：学历基准分
     */
    public Float getEdScore() { return edScore; }
    /**
     * 设置：学历基准分
     */
    public void setEdScore( Float edScore) { this.edScore = edScore;};
    /**
     * 获取： 学制基准分
     */
    public Float getEdTypescore() { return this.edTypescore; };
    /**
     * 获取： 学制基准分
     */
    public void setEdTypescore(Float edTypescore) { this.edTypescore = edTypescore; };
    /**
     * 获取：学历系数基准分
     */
    public Float getEdProscore() { return this.edProscore; }
    /**
     * 设置：学历系数基准分
     */
    public void setEdProscore(Float edProscore) { this.edProscore = edProscore;}
    /**
     * 获取：职务基准分
     */
    public Long getDutyScore() { return this.dutyScore;}
    /**
     * 设置：职务基准分
     */
    public void setDutyScore(Long dutyScore) { this.dutyScore = dutyScore;}
    /**
     * 获取：职务的效能基准分
     */
    public Float getDutyStandScore() { return this.dutyStandScore;}
    /**
     * 设置：职务的效能基准分
     */
    public void setDutyStandScore(Float dutyStandScore) { this.dutyStandScore = dutyStandScore;}
    /**
     * 获取：职务的未考核效能基准分
     */
    public Float getDutyAssessScore() { return this.dutyAssessScore;}
    /**
     * 设置：职务的未考核效能基准分
     */
    public void setDutyAssessScore(Float dutyAssessScore) { this.dutyAssessScore = dutyAssessScore;}
    /**
     * 获取：职务的试用期效能基准分
     */
    public Float getDutyTrialScore() { return this.dutyTrialScore;};
    /**
     * 设置：职务的试用期效能基准分
     */
    public void setDutyTrialScore(Float dutyTrialScore) { this.dutyTrialScore = dutyTrialScore;};
    /**
     *获取： 职称的效能基准分
     */
    public Float getTitleStandScore() {return  this.titleStandScore;};
    /**
     *设置： 职称的效能基准分
     */
    public void setTitleStandScore(Float titleAssessScore) {this.titleStandScore = titleAssessScore;};
    /**
     *获取： 职称的未考核效能基准分
     */
    public Float getTitleAssessScore() {return  this.titleAssessScore;};
    /**
     *设置： 职称的未考核效能基准分
     */
    public void setTitleAssessScore(Float titleAssessScore) { this.titleAssessScore = titleAssessScore;};
    /**
     * 获取：职称的试用期效能基准分
     */
    public Float getTitleTrialScore() {return  this.titleTrialScore;};
    /**
     * 设置：职称的试用期效能基准分
     */
    public void setTitleTrialScore(Float titleTrialScore) { this.titleTrialScore = titleTrialScore;};
    /**
     * 获取：审定后的效能基准分
     */
    public Long getKbiAuditScore() {return this.kbiAuditScore;};
    /**
     * 设置：审定后的效能基准分
     */
    public void setKbiAuditScore(Long kbiAuditScore) {this.kbiAuditScore = kbiAuditScore;};
    /**
     * 获取：是否审核（0-未审核; 1-已审核；2-审核不通过;）
     */
    public Long getisAudit (){return this.isAudit;};
    /**
     * 设置：是否审核（0-未审核; 1-已审核；2-审核不通过;）
     */
    public void setisAudit (Long isAudit){this.isAudit = isAudit;};
    /**
     * 获取：审核驳回内容
     */
    public String getauditMsg (){return this.auditMsg;};
    /**
     * 设置：审核驳回内容
     */
    public void setauditMsg (String aduitMsg){this.auditMsg = aduitMsg;};
    /**
     * 获取：个人档案
     */
    public RenRecordEntity getRenRecordEntity () {return this.renRecordEntity;};
    /**
     * 设置： 个人档案
     */
    public void setRenRecordEntity(RenRecordEntity renRecordEntity){
        this.renRecordEntity = renRecordEntity;
    }
    /**
     * 获取：教育背景列表
     */
    public List<RenRecordEducationEntity> getEdBackgroundList (){return this.edBackgroundList;};
    /**
     * 设置：教育背景列表
     */
    public void setEdBackgroundList (List<RenRecordEducationEntity> edBackgroundList){this.edBackgroundList = edBackgroundList;};
    /**
     * 获取：工作经验列表
     */
    public List<RenRecordWorkEntity> getWorkBackgroundList(){return this.workBackgroundList;};
    /**
     * 设置：工作经验列表
     */
    public void setWorkBackgroundList (List<RenRecordWorkEntity> workBackgroundList){this.workBackgroundList = workBackgroundList;};
}
