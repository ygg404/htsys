package io.renren.modules.ren.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.renren.modules.ren.entity.*;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 成员档案复合类
 */
public class RenRecordTempVoEntity implements Serializable {
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
     * 最高学历
     */
    private Long education;
    /**
     * 教育类型
     */
    private Long educationType;
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
     * 职务id
     */
    private Long dutyId;
    /**
     * 职称等级
     */
    private Long titleLever;
    /**
     * 职称专业系数
     */
    private Long titlePro;
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
     * 是否审核（0-未审核; 1-已审核；2-审核不通过;）
     */
    private Long isAudit;
    /**
     * 审核驳回内容
     */
    private String auditMsg;
    /**
     * 个人档案（临时）
     */
    private RenRecordTempEntity renRecordTempEntity;
    /**
     * 教育背景列表
     */
    private List<RenRecordEducationTempEntity> edBackgroundList;
    /**
     * 工作经验列表
     */
    private List<RenRecordWorkTempEntity> workBackgroundList;


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
     * 获取：性别（1-男；2-女）
     */
    public Long getSex (){return this.sex;};
    /**
     * 设置：性别（1-男；2-女）
     */
    public void setSex (Long sex){this.sex = sex;};
    /**
     * 获取：工作类型
     */
    public Long getjobType (){return this.jobType;};
    /**
     * 设置：工作类型
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
     * 获取：最高学历
     */
    public Long geteducation (){return this.education;};
    /**
     * 设置：最高学历
     */
    public void seteducation (Long education){this.education = education;};
    /**
     * 获取：教育类型
     */
    public Long getEducationType (){return this.educationType;};
    /**
     * 设置：教育类型
     */
    public void setEducationType (Long educationType){this.educationType = educationType;};
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
     * 获取：职务id
     */
    public Long getDutyId (){return this.dutyId;};
    /**
     * 设置：职务id
     */
    public void setDutyId (Long dutyId){this.dutyId = dutyId;};
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
    public void setauditMsg (String auditMsg){this.auditMsg = auditMsg;};

    /**
     * 获取：个人档案（临时）
     */
    public RenRecordTempEntity getRenRecordTempEntity () {return this.renRecordTempEntity;};
    /**
     * 设置： 个人档案（临时）
     */
    public void setRenRecordTempEntity(RenRecordTempEntity renRecordTempEntity){
        this.renRecordTempEntity = renRecordTempEntity;
    }
    /**
     * 获取：教育背景列表
     */
    public List<RenRecordEducationTempEntity> getEdBackgroundList (){return this.edBackgroundList;};
    /**
     * 设置：教育背景列表
     */
    public void setEdBackgroundList (List<RenRecordEducationTempEntity> edBackgroundList){this.edBackgroundList = edBackgroundList;};
    /**
     * 获取：工作经验列表
     */
    public List<RenRecordWorkTempEntity> getWorkBackgroundList(){return this.workBackgroundList;};
    /**
     * 设置：工作经验列表
     */
    public void setWorkBackgroundList (List<RenRecordWorkTempEntity> workBackgroundList){this.workBackgroundList = workBackgroundList;};
}
