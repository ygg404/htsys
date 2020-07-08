package io.renren.modules.project.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 项目作业表
 * 
 * @author ygg
 * @date 2019-11-01 16:46:10
 */
@TableName("project_work")
public class ProjectWorkEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * ID
	 */
	@TableId
	private Long id;
	/**
	 * 项目编号
	 */
	private String projectNo;
	/**
	 * 技术交底内容
	 */
	private String technicalDisclosureNote;
	/**
	 * 过程检查意见
	 */
	private String checkSuggestion;
	/**
	 * 上交资料
	 */
	private String dataName;
	/**
	 * 工作小结
	 */
	private String briefSummary;
	/**
	 * 工作量
	 */
	private String workLoad;
	/**
	 * 结束时间
	 */
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
	private Date finishDateTime;
	/**
	 * 返回状态
	 */
	private Integer backStage;
	/**
	 * 项目状态:0.启动，1.暂停
	 */
	private Integer projectStatus;
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
	 * 用户组id
	 */
	private Integer groupId;
	/**
	 * 
	 */
	private Date planWorkDate;

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
	* 获取：工作量
	*/
	public String getworkLoad (){return this.workLoad;};
	/**
	 * 设置：工作量
	 */
	public void setworkLoad (String workLoad){this.workLoad = workLoad;};
	/**
	* 获取：结束时间
	*/
	public Date getfinishDateTime (){return this.finishDateTime;};
	/**
	 * 设置：结束时间
	 */
	public void setfinishDateTime (Date finishDateTime){this.finishDateTime = finishDateTime;};
	/**
	* 获取：返回状态
	*/
	public Integer getbackStage (){return this.backStage;};
	/**
	 * 设置：返回状态
	 */
	public void setbackStage (Integer backStage){this.backStage = backStage;};
	/**
	* 获取：项目状态:0.启动，1.暂停
	*/
	public Integer getprojectStatus (){return this.projectStatus;};
	/**
	 * 设置：项目状态:0.启动，1.暂停
	 */
	public void setprojectStatus (Integer projectStatus){this.projectStatus = projectStatus;};
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
	* 获取：用户组id
	*/
	public Integer getgroupId (){return this.groupId;};
	/**
	 * 设置：用户组id
	 */
	public void setgroupId (Integer groupId){this.groupId = groupId;};
	/**
	* 获取：
	*/
	public Date getplanWorkDate (){return this.planWorkDate;};
	/**
	 * 设置：
	 */
	public void setplanWorkDate (Date planWorkDate){this.planWorkDate = planWorkDate;};
}
