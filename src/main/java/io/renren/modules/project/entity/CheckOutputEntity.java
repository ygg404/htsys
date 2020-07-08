package io.renren.modules.project.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;


/**
 * 产值核算表
 * 
 * @author ygg
 * @date 2019-11-18 15:04:00
 */
@TableName("check_output")
public class CheckOutputEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * ID
	 */
	@TableId
	private Long id;
	/**
	 * 工作类型ID
	 */
	private Long typeId;
	/**
	 * 项目编号
	 */
	private String projectNo;
	/**
	 * 项目占比
	 */
	private Float projectRatio;
	/**
	 * 工作量
	 */
	private Float workLoad;
	/**
	 * 工作组ID
	 */
	private Long groupId;

	/**
	* 获取：ID
	*/
	public Long getid (){return this.id;};
	/**
	 * 设置：ID
	 */
	public void setid (Long id){this.id = id;};
	/**
	* 获取：工作类型ID
	*/
	public Long gettypeId (){return this.typeId;};
	/**
	 * 设置：工作类型ID
	 */
	public void settypeId (Long typeId){this.typeId = typeId;};
	/**
	* 获取：项目编号
	*/
	public String getprojectNo (){return this.projectNo;};
	/**
	 * 设置：项目编号
	 */
	public void setprojectNo (String projectNo){this.projectNo = projectNo;};
	/**
	* 获取：项目占比
	*/
	public Float getprojectRatio (){return this.projectRatio;};
	/**
	 * 设置：项目占比
	 */
	public void setprojectRatio (Float projectRatio){this.projectRatio = projectRatio;};
	/**
	* 获取：工作量
	*/
	public Float getworkLoad (){return this.workLoad;};
	/**
	 * 设置：工作量
	 */
	public void setworkLoad (Float workLoad){this.workLoad = workLoad;};
	/**
	* 获取：工作组ID
	*/
	public Long getgroupId (){return this.groupId;};
	/**
	 * 设置：工作组ID
	 */
	public void setgroupId (Long groupId){this.groupId = groupId;};
}
