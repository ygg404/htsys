package io.renren.modules.ren.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 职工档案工作经验表（正式）
 * 
 * @author ygg
 * @date 2020-02-13 11:59:22
 */
@TableName("ren_record_work")
public class RenRecordWorkEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 档案工作经历ID
	 */
	@TableId
	private Long id;
	/**
	 * 用户id
	 */
	private Long userId;
	/**
	 * 开始时间
	 */
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM")
	private Date startDate;
	/**
	 * 结束时间
	 */
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM")
	private Date endDate;
	/**
	 * 公司
	 */
	private String company;
	/**
	 * 职位
	 */
	private String jobPosition;
	/**
	 * 工作描述
	 */
	private String jobDescription;

	/**
	* 获取：档案工作经历ID
	*/
	public Long getid (){return this.id;};
	/**
	 * 设置：档案工作经历ID
	 */
	public void setid (Long id){this.id = id;};
	/**
	* 获取：用户id
	*/
	public Long getuserId (){return this.userId;};
	/**
	 * 设置：用户id
	 */
	public void setuserId (Long userId){this.userId = userId;};
	/**
	* 获取：开始时间
	*/
	public Date getstartDate (){return this.startDate;};
	/**
	 * 设置：开始时间
	 */
	public void setstartDate (Date startDate){this.startDate = startDate;};
	/**
	* 获取：结束时间
	*/
	public Date getendDate (){return this.endDate;};
	/**
	 * 设置：结束时间
	 */
	public void setendDate (Date endDate){this.endDate = endDate;};
	/**
	* 获取：公司
	*/
	public String getcompany (){return this.company;};
	/**
	 * 设置：公司
	 */
	public void setcompany (String company){this.company = company;};
	/**
	* 获取：职位
	*/
	public String getjobPosition (){return this.jobPosition;};
	/**
	 * 设置：职位
	 */
	public void setjobPosition (String jobPosition){this.jobPosition = jobPosition;};
	/**
	* 获取：工作描述
	*/
	public String getjobDescription (){return this.jobDescription;};
	/**
	 * 设置：工作描述
	 */
	public void setjobDescription (String jobDescription){this.jobDescription = jobDescription;};
}
