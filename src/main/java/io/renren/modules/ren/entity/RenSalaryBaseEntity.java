package io.renren.modules.ren.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 基本工资表
 *
 * @author ygg
 * @date 2020-07-02 16:21:53
 */
@TableName("ren_salary_base")
public class RenSalaryBaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 自增ID
	 */
	@TableId
	private Long id;
	/**
	 * 部门ID
	 */
	private Long branchId;
	/**
	 * 用户ID
	 */
	private Long userId;
	/**
	 * 用户名
	 */
	private String username;
	/**
	 * 固定工资
	 */
	private Float fixedSalary;
	/**
	 * 效能工资
	 */
	private Float kbiSalary;
	/**
	 * 职称工资
	 */
	private Float titleSalary;
	/**
	 * 住房补贴
	 */
	private Long housingSalary;
	/**
	 * 电脑补贴
	 */
	private Float pcSalary;
	/**
	 * 餐补贴
	 */
	private Float mealSalary;
	/**
	 * 社保
	 */
	private Float socialSalary;
	/**
	 * 学制ID
	 */
	private Long edTypeid;
	/**
	 * 学制
	 */
	private String edTypename;
	/**
	 * 学历类型分数
	 */
	private Float edTypescore;
	/**
	 * 学历专业系数ID
	 */
	private Long edProid;
	/**
	 * 学历专业系数
	 */
	private String edProname;
	/**
	 * 学历专业系数分数
	 */
	private Float edProscore;
	/**
	 * 学历ID
	 */
	private Long edId;
	/**
	 * 学历名称
	 */
	private String edName;
	/**
	 * 学历分数
	 */
	private Float edScore;
	/**
	 * 学历基准分
	 */
	private Float edBasescore;
	/**
	 * 学历总得分
	 */
	private Long edAllscore;
	/**
	 * 职称专业系数ID
	 */
	private Long titleProid;
	/**
	 * 职称专业性质
	 */
	private String titleProname;
	/**
	 * 职称专业系数分数
	 */
	private Float titleProscore;
	/**
	 * 职称名称ID
	 */
	private Long titleId;
	/**
	 * 职称名称
	 */
	private String titleName;
	/**
	 * 职称基准分数
	 */
	private Float titleScore;
	/**
	 * 职称总分
	 */
	private Long titleAllscore;
	/**
	 * 司龄
	 */
	private Long firmYear;
	/**
	 * 他龄
	 */
	private Long otherYear;
	/**
	 * 工龄
	 */
	private Long workYear;
	/**
	 * 工龄分
	 */
	private Long workScore;
	/**
	 * 职务ID
	 */
	private Long dutyId;
	/**
	 * 职务名称
	 */
	private String dutyName;
	/**
	 * 职务分数
	 */
	private Long dutyScore;
	/**
	 * 职级Id
	 */
	private Long dutyLever;
	/**
	 * 职级名称
	 */
	private String jobRank;
	/**
	 * 审定后的效能分
	 */
	private Long kbiAuditScore;
	/**
	 * 效能基准分
	 */
	private Long kbiScore;
	/**
	 * 最终认定的效能分
	 */
	private Long kbiFinalScore;
	/**
	 * 工作类型（1-全职；2-兼职;3-实习）
	 */
	private Long jobType;
	/**
	 * 基本工资年份
	 */
	private Long salaryYear;
	/**
	 * 基本工资月份
	 */
	private Long salaryMonth;
	/**
	 * 修改时间
	 */
	private Date createDate;
	/**
	 * 参加工作时间
	 */
	@TableField(exist = false)
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
	private Date educationTime;
	/**
	 * 入职时间
	 */
	@TableField(exist = false)
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
	private Date entryTime;
	/**
	 * 获取：自增ID
	 */
	public Long getId (){return this.id;};
	/**
	 * 设置：自增ID
	 */
	public void setId (Long id){this.id = id;};
	/**
	 * 获取：部门ID
	 */
	public Long getBranchId (){return this.branchId;};
	/**
	 * 设置：部门ID
	 */
	public void setBranchId (Long branchId){this.branchId = branchId;};
	/**
	 * 获取：用户ID
	 */
	public Long getUserId (){return this.userId;};
	/**
	 * 设置：用户ID
	 */
	public void setUserId (Long userId){this.userId = userId;};
	/**
	 * 获取：用户名
	 */
	public String getUsername (){return this.username;};
	/**
	 * 设置：用户名
	 */
	public void setUsername (String username){this.username = username;};
	/**
	 * 获取：固定工资
	 */
	public Float getFixedSalary (){return this.fixedSalary;};
	/**
	 * 设置：固定工资
	 */
	public void setFixedSalary (Float fixedSalary){this.fixedSalary = fixedSalary;};
	/**
	 * 获取：效能工资
	 */
	public Float getKbiSalary (){return this.kbiSalary;};
	/**
	 * 设置：效能工资
	 */
	public void setKbiSalary (Float kbiSalary){this.kbiSalary = kbiSalary;};
	/**
	 * 获取：职称工资
	 */
	public Float getTitleSalary (){return this.titleSalary;};
	/**
	 * 设置：职称工资
	 */
	public void setTitleSalary (Float titleSalary){this.titleSalary = titleSalary;};
	/**
	 * 获取：住房补贴
	 */
	public Long getHousingSalary (){return this.housingSalary;};
	/**
	 * 设置：住房补贴
	 */
	public void setHousingSalary (Long housingSalary){this.housingSalary = housingSalary;};
	/**
	 * 获取：电脑补贴
	 */
	public Float getPcSalary (){return this.pcSalary;};
	/**
	 * 设置：电脑补贴
	 */
	public void setPcSalary (Float pcSalary){this.pcSalary = pcSalary;};
	/**
	 * 获取：餐补贴
	 */
	public Float getMealSalary (){return this.mealSalary;};
	/**
	 * 设置：餐补贴
	 */
	public void setMealSalary (Float mealSalary){this.mealSalary = mealSalary;};
	/**
	 * 获取：社保
	 */
	public Float getSocialSalary (){return this.socialSalary;};
	/**
	 * 设置：社保
	 */
	public void setSocialSalary (Float socialSalary){this.socialSalary = socialSalary;};
	/**
	 * 获取：学制ID
	 */
	public Long getEdTypeid (){return this.edTypeid;};
	/**
	 * 设置：学制ID
	 */
	public void setEdTypeid (Long edTypeid){this.edTypeid = edTypeid;};
	/**
	 * 获取：学制
	 */
	public String getEdTypename (){return this.edTypename;};
	/**
	 * 设置：学制
	 */
	public void setEdTypename (String edTypename){this.edTypename = edTypename;};
	/**
	 * 获取：学历类型分数
	 */
	public Float getEdTypescore (){return this.edTypescore;};
	/**
	 * 设置：学历类型分数
	 */
	public void setEdTypescore (Float edTypescore){this.edTypescore = edTypescore;};
	/**
	 * 获取：学历专业系数ID
	 */
	public Long getEdProid (){return this.edProid;};
	/**
	 * 设置：学历专业系数ID
	 */
	public void setEdProid (Long edProid){this.edProid = edProid;};
	/**
	 * 获取：学历专业系数
	 */
	public String getEdProname (){return this.edProname;};
	/**
	 * 设置：学历专业系数
	 */
	public void setEdProname (String edProname){this.edProname = edProname;};
	/**
	 * 获取：学历专业系数分数
	 */
	public Float getEdProscore (){return this.edProscore;};
	/**
	 * 设置：学历专业系数分数
	 */
	public void setEdProscore (Float edProscore){this.edProscore = edProscore;};
	/**
	 * 获取：学历ID
	 */
	public Long getEdId (){return this.edId;};
	/**
	 * 设置：学历ID
	 */
	public void setEdId (Long edId){this.edId = edId;};
	/**
	 * 获取：学历名称
	 */
	public String getEdName (){return this.edName;};
	/**
	 * 设置：学历名称
	 */
	public void setEdName (String edName){this.edName = edName;};
	/**
	 * 获取：学历分数
	 */
	public Float getEdScore (){return this.edScore;};
	/**
	 * 设置：学历分数
	 */
	public void setEdScore (Float edScore){this.edScore = edScore;};
	/**
	 * 获取：学历基准分
	 */
	public Float getEdBasescore (){return this.edBasescore;};
	/**
	 * 设置：学历基准分
	 */
	public void setEdBasescore (Float edBasescore){this.edBasescore = edBasescore;};
	/**
	 * 获取：学历总得分
	 */
	public Long getEdAllscore (){return this.edAllscore;};
	/**
	 * 设置：学历总得分
	 */
	public void setEdAllscore (Long edAllscore){this.edAllscore = edAllscore;};
	/**
	 * 获取：职称专业系数ID
	 */
	public Long getTitleProid (){return this.titleProid;};
	/**
	 * 设置：职称专业系数ID
	 */
	public void setTitleProid (Long titleProid){this.titleProid = titleProid;};
	/**
	 * 获取：职称专业性质
	 */
	public String getTitleProname (){return this.titleProname;};
	/**
	 * 设置：职称专业性质
	 */
	public void setTitleProname (String titleProname){this.titleProname = titleProname;};
	/**
	 * 获取：职称专业系数分数
	 */
	public Float getTitleProscore (){return this.titleProscore;};
	/**
	 * 设置：职称专业系数分数
	 */
	public void setTitleProscore (Float titleProscore){this.titleProscore = titleProscore;};
	/**
	 * 获取：职称名称ID
	 */
	public Long getTitleId (){return this.titleId;};
	/**
	 * 设置：职称名称ID
	 */
	public void setTitleId (Long titleId){this.titleId = titleId;};
	/**
	 * 获取：职称名称
	 */
	public String getTitleName (){return this.titleName;};
	/**
	 * 设置：职称名称
	 */
	public void setTitleName (String titleName){this.titleName = titleName;};
	/**
	 * 获取：职称基准分数
	 */
	public Float getTitleScore (){return this.titleScore;};
	/**
	 * 设置：职称基准分数
	 */
	public void setTitleScore (Float titleScore){this.titleScore = titleScore;};
	/**
	 * 获取：职称总分
	 */
	public Long getTitleAllscore (){return this.titleAllscore;};
	/**
	 * 设置：职称总分
	 */
	public void setTitleAllscore (Long titleAllscore){this.titleAllscore = titleAllscore;};
	/**
	 * 获取：司龄
	 */
	public Long getFirmYear (){return this.firmYear;};
	/**
	 * 设置：司龄
	 */
	public void setFirmYear (Long firmYear){this.firmYear = firmYear;};
	/**
	 * 获取：他龄
	 */
	public Long getOtherYear (){return this.otherYear;};
	/**
	 * 设置：他龄
	 */
	public void setOtherYear (Long otherYear){this.otherYear = otherYear;};
	/**
	 * 获取：工龄
	 */
	public Long getWorkYear (){return this.workYear;};
	/**
	 * 设置：工龄
	 */
	public void setWorkYear (Long workYear){this.workYear = workYear;};
	/**
	 * 获取：工龄分
	 */
	public Long getWorkScore (){return this.workScore;};
	/**
	 * 设置：工龄分
	 */
	public void setWorkScore (Long workScore){this.workScore = workScore;};
	/**
	 * 获取：职务ID
	 */
	public Long getDutyId (){return this.dutyId;};
	/**
	 * 设置：职务ID
	 */
	public void setDutyId (Long dutyId){this.dutyId = dutyId;};
	/**
	 * 获取：职务名称
	 */
	public String getDutyName (){return this.dutyName;};
	/**
	 * 设置：职务名称
	 */
	public void setDutyName (String dutyName){this.dutyName = dutyName;};
	/**
	 * 获取：职务分数
	 */
	public Long getDutyScore (){return this.dutyScore;};
	/**
	 * 设置：职务分数
	 */
	public void setDutyScore (Long dutyScore){this.dutyScore = dutyScore;};
	/**
	 * 获取：职级Id
	 */
	public Long getDutyLever (){return this.dutyLever;};
	/**
	 * 设置：职级Id
	 */
	public void setDutyLever (Long dutyLever){this.dutyLever = dutyLever;};
	/**
	 * 获取：职级名称
	 */
	public String getJobRank (){return this.jobRank;};
	/**
	 * 设置：职级名称
	 */
	public void setJobRank (String jobRank){this.jobRank = jobRank;};
	/**
	 * 获取：审定后的效能分
	 */
	public Long getKbiAuditScore (){return this.kbiAuditScore;};
	/**
	 * 设置：审定后的效能分
	 */
	public void setKbiAuditScore (Long kbiAuditScore){this.kbiAuditScore = kbiAuditScore;};
	/**
	 * 获取：效能基准分
	 */
	public Long getKbiScore (){return this.kbiScore;};
	/**
	 * 设置：效能基准分
	 */
	public void setKbiScore (Long kbiScore){this.kbiScore = kbiScore;};
	/**
	 * 获取：最终认定的效能分
	 */
	public Long getKbiFinalScore (){return this.kbiFinalScore;};
	/**
	 * 设置：最终认定的效能分
	 */
	public void setKbiFinalScore (Long kbiFinalScore){this.kbiFinalScore = kbiFinalScore;};
	/**
	 * 获取：工作类型（1-全职；2-兼职;3-实习）
	 */
	public Long getJobType (){return this.jobType;};
	/**
	 * 设置：工作类型（1-全职；2-兼职;3-实习）
	 */
	public void setJobType (Long jobType){this.jobType = jobType;};
	/**
	 * 获取：基本工资年份
	 */
	public Long getSalaryYear (){return this.salaryYear;};
	/**
	 * 设置：基本工资年份
	 */
	public void setSalaryYear (Long salaryYear){this.salaryYear = salaryYear;};
	/**
	 * 获取：基本工资月份
	 */
	public Long getSalaryMonth (){return this.salaryMonth;};
	/**
	 * 设置：基本工资月份
	 */
	public void setSalaryMonth (Long salaryMonth){this.salaryMonth = salaryMonth;};
	/**
	 * 获取：修改时间
	 */
	public Date getCreateDate (){return this.createDate;};
	/**
	 * 设置：修改时间
	 */
	public void setCreateDate (Date createDate){this.createDate = createDate;};
	/**
	 * 获取：参加工作时间
	 */
	public Date getEducationTime (){return this.educationTime;};
	/**
	 * 设置：参加工作时间
	 */
	public void setEducationTime (Date educationTime){this.educationTime = educationTime;};
	/**
	 * 获取：入职时间
	 */
	public Date getEntryTime (){return this.entryTime;};
	/**
	 * 设置：入职时间
	 */
	public void setEntryTime (Date entryTime){this.entryTime = entryTime;};
}
