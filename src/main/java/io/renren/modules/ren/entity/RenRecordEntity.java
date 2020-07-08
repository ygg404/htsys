package io.renren.modules.ren.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 职工档案表（正式）
 *
 * @author ygg
 * @date 2020-06-09 17:17:36
 */
@TableName("ren_record")
public class RenRecordEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 用户id
	 */
	@TableId
	private Long userId;
	/**
	 * 性别（1-男；2-女）
	 */
	private Long sex;
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
	 * 工作类型（1-全职；2-兼职;3-实习）
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
	 * 职务ID
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
	 * 获取：用户id
	 */
	public Long getUserId (){return this.userId;};
	/**
	 * 设置：用户id
	 */
	public void setUserId (Long userId){this.userId = userId;};
	/**
	 * 获取：性别（1-男；2-女）
	 */
	public Long getSex (){return this.sex;};
	/**
	 * 设置：性别（1-男；2-女）
	 */
	public void setSex (Long sex){this.sex = sex;};
	/**
	 * 获取：身份证号
	 */
	public String getIdNo (){return this.idNo;};
	/**
	 * 设置：身份证号
	 */
	public void setIdNo (String idNo){this.idNo = idNo;};
	/**
	 * 获取：出生日期
	 */
	public Date getBirthday (){return this.birthday;};
	/**
	 * 设置：出生日期
	 */
	public void setBirthday (Date birthday){this.birthday = birthday;};
	/**
	 * 获取：入职时间
	 */
	public Date getEntryTime (){return this.entryTime;};
	/**
	 * 设置：入职时间
	 */
	public void setEntryTime (Date entryTime){this.entryTime = entryTime;};
	/**
	 * 获取：工作类型（1-全职；2-兼职;3-实习）
	 */
	public Long getJobType (){return this.jobType;};
	/**
	 * 设置：工作类型（1-全职；2-兼职;3-实习）
	 */
	public void setJobType (Long jobType){this.jobType = jobType;};
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
	public Long getEducation (){return this.education;};
	/**
	 * 设置：最高学历
	 */
	public void setEducation (Long education){this.education = education;};
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
	public void setProRatio (Long proRatio){this.proRatio = proRatio;};
	/**
	 * 获取：职务id
	 */
	public Long getDutyId (){return this.dutyId;};
	/**
	 * 设置：职务id
	 */
	public void setDutyId (Long dutyId){this.dutyId = dutyId;};
	/**
	 * 获取：职称等级
	 */
	public Long getTitleLever (){return this.titleLever;};
	/**
	 * 获取：职称专业系数
	 */
	public Long getTitlePro(){ return this.titlePro; }
	/**
	 * 设置：职称专业系数
	 */
	public void setTitlePro (Long titlePro){this.titlePro = titlePro;};
	/**
	 * 设置：职称等级
	 */
	public void setTitleLever (Long titleLever){this.titleLever = titleLever;};
	/**
	 * 获取：邮箱
	 */
	public String getEmail (){return this.email;};
	/**
	 * 设置：邮箱
	 */
	public void setEmail (String email){this.email = email;};
	/**
	 * 获取：手机号
	 */
	public String getMobile (){return this.mobile;};
	/**
	 * 设置：手机号
	 */
	public void setMobile (String mobile){this.mobile = mobile;};
	/**
	 * 获取：试用期（月）
	 */
	public Long getTrialPeriod (){return this.trialPeriod;};
	/**
	 * 设置：试用期（月）
	 */
	public void setTrialPeriod (Long trialPeriod){this.trialPeriod = trialPeriod;};
	/**
	 * 获取：籍贯（省）
	 */
	public String getNativeProvince (){return this.nativeProvince;};
	/**
	 * 设置：籍贯（省）
	 */
	public void setNativeProvince (String nativeProvince){this.nativeProvince = nativeProvince;};
	/**
	 * 获取：籍贯（市）
	 */
	public String getNativeCity (){return this.nativeCity;};
	/**
	 * 设置：籍贯（市）
	 */
	public void setNativeCity (String nativeCity){this.nativeCity = nativeCity;};
	/**
	 * 获取：婚姻状况(0-未婚；1-已婚；2-离异；3-丧偶)
	 */
	public Long getMaritalStatus (){return this.maritalStatus;};
	/**
	 * 设置：婚姻状况(0-未婚；1-已婚；2-离异；3-丧偶)
	 */
	public void setMaritalStatus (Long maritalStatus){this.maritalStatus = maritalStatus;};
	/**
	 * 获取：头像
	 */
	public String getHeadImg (){return this.headImg;};
	/**
	 * 设置：头像
	 */
	public void setHeadImg (String headImg){this.headImg = headImg;};
}
