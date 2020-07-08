package io.renren.modules.project.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;


/**
 * 进度表
 * 
 * @author ygg
 * @date 2019-11-01 16:46:10
 */
@TableName("project_schedule")
public class ProjectScheduleEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	@TableId
	private Integer id;
	/**
	 * 项目编号
	 */
	private String projectNo;
	/**
	 * 进度内容
	 */
	private String scheduleNote;
	/**
	 * 进度百分比
	 */
	private Integer scheduleRate;
	/**
	 * 创建时间
	 */
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
	private Date createTime;

	/**
	* 获取：id
	*/
	public Integer getid (){return this.id;};
	/**
	 * 设置：id
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
	* 获取：创建时间
	*/
	public Date getcreateTime (){return this.createTime;};
	/**
	 * 设置：创建时间
	 */
	public void setcreateTime (Date createTime){this.createTime = createTime;};
}
