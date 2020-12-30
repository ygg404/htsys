package io.renren.modules.project.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;


/**
 * 质量检查表
 * 
 * @author ygg
 * @date 2019-11-16 09:22:12
 */
@TableName("check_quality")
public class CheckQualityEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * ID
	 */
	@TableId
	private Long id;
	/**
	 * 过程检查意见
	 */
	private String finalCheckSuggestion;
	/**
	 * 质量综述
	 */
	private String qualityNote;
	/**
	 * 质检反馈报告
	 */
	private String qualityReport;
	/**
	 * 质检评分
	 */
	private Float qualityScore;
	/**
	 * 项目编号
	 */
	private String projectNo;
	/**
	 * 质检提交时间
	 */
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
	private Date finishDateTime;
	/**
	 * 结算时间
	 */
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM")
	private Date cutOffTime;
	/**
	 * 
	 */
	private Long groupid;
	/**
	 * 质检用户账号
	 */
	private String qualityUseraccount;
	/**
	 * 质检用户名
	 */
	private String qualityUsername;
	/**
	 * 质检审核账号
	 */
	private String qualityConfirmaccount;
	/**
	 * 质检审核姓名
	 */
	private String qualityConfirmname;
	/**
	* 获取：ID
	*/
	public Long getId (){return this.id;};
	/**
	 * 设置：ID
	 */
	public void setId (Long id){this.id = id;};
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
	 * 获取：质量反馈
	 */
	public String getqualityReport (){return this.qualityReport;};
	/**
	 * 设置：质量反馈
	 */
	public void setqualityReport (String qualityReport){this.qualityReport = qualityReport;};
	/**
	* 获取：质检评分
	*/
	public Float getqualityScore (){return this.qualityScore;};
	/**
	 * 设置：质检评分
	 */
	public void setqualityScore (Float qualityScore){this.qualityScore = qualityScore;};
	/**
	* 获取：项目编号
	*/
	public String getprojectNo (){return this.projectNo;};
	/**
	 * 设置：项目编号
	 */
	public void setprojectNo (String projectNo){this.projectNo = projectNo;};
	/**
	* 获取：质检提交时间
	*/
	public Date getfinishDateTime (){return this.finishDateTime;};
	/**
	 * 设置：质检提交时间
	 */
	public void setfinishDateTime (Date finishDateTime){this.finishDateTime = finishDateTime;};
	/**
	* 获取：结算时间
	*/
	public Date getcutOffTime (){return this.cutOffTime;};
	/**
	 * 设置：结算时间
	 */
	public void setcutOffTime (Date cutOffTime){this.cutOffTime = cutOffTime;};
	/**
	* 获取：
	*/
	public Long getgroupid (){return this.groupid;};
	/**
	 * 设置：
	 */
	public void setgroupid (Long groupid){this.groupid = groupid;};
	/**
	* 获取：质检用户账号
	*/
	public String getqualityUseraccount (){return this.qualityUseraccount;};
	/**
	 * 设置：质检用户账号
	 */
	public void setqualityUseraccount (String qualityUseraccount){this.qualityUseraccount = qualityUseraccount;};
	/**
	 * 获取：质检用户名
	 */
	public String getQualityUsername (){return this.qualityUsername;};
	/**
	 * 设置：质检用户名
	 */
	public void setQualityUsername (String qualityUsername){this.qualityUsername = qualityUsername;};
	/**
	* 获取：质检审核账号
	*/
	public String getqualityConfirmaccount (){return this.qualityConfirmaccount;};
	/**
	 * 设置：质检审核账号
	 */
	public void setqualityConfirmaccount (String qualityConfirmaccount){this.qualityConfirmaccount = qualityConfirmaccount;};
	/**
	 * 获取：质检审核姓名
	 */
	public String getQualityConfirmname (){return this.qualityConfirmname;};
	/**
	 * 设置：质检审核姓名
	 */
	public void setQualityConfirmname (String qualityConfirmname){this.qualityConfirmname = qualityConfirmname;};
}
