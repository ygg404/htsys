package io.renren.modules.ren.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 职工档案教育背景表(正式)
 * 
 * @author ygg
 * @date 2020-02-13 11:59:22
 */
@TableName("ren_record_education")
public class RenRecordEducationEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 档案教育背景ID
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
	 * 学历
	 */
	private String educationBackground;
	/**
	 * 毕业院校
	 */
	private String educationSchool;
	/**
	 * 专业
	 */
	private String major;

	/**
	* 获取：档案教育背景ID
	*/
	public Long getid (){return this.id;};
	/**
	 * 设置：档案教育背景ID
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
	* 获取：学历
	*/
	public String geteducationBackground (){return this.educationBackground;};
	/**
	 * 设置：学历
	 */
	public void seteducationBackground (String educationBackground){this.educationBackground = educationBackground;};
	/**
	* 获取：毕业院校
	*/
	public String geteducationSchool (){return this.educationSchool;};
	/**
	 * 设置：毕业院校
	 */
	public void seteducationSchool (String educationSchool){this.educationSchool = educationSchool;};
	/**
	* 获取：专业
	*/
	public String getmajor (){return this.major;};
	/**
	 * 设置：专业
	 */
	public void setmajor (String major){this.major = major;};
}
