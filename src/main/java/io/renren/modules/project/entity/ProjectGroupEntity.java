package io.renren.modules.project.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;


/**
 * 项目安排分组
 * 
 * @author ygg
 * @date 2019-11-01 16:46:10
 */
@TableName("project_group")
public class ProjectGroupEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * ID
	 */
	@TableId
	private Integer id;
	/**
	 * 项目编号
	 */
	private String projectNo;
	/**
	 * 组id
	 */
	private Long groupId;
	/**
	 * 产值占比
	 */
	private Float outputRate;
	/**
	 * 最短工期
	 */
	private Float shortDateTime;
	/**
	 * 最迟工期
	 */
	private Float lastDateTime;
	/**
	 * 项目预算产值
	 */
	private Float projectOutput;
	/**
	 * 项目实际产值
	 */
	private Float projectActuallyOutput;

	/**
	* 获取：ID
	*/
	public Integer getid (){return this.id;};
	/**
	 * 设置：ID
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
	* 获取：组id
	*/
	public Long getgroupId (){return this.groupId;};
	/**
	 * 设置：组id
	 */
	public void setgroupId (Long groupId){this.groupId = groupId;};
	/**
	* 获取：产值占比
	*/
	public Float getoutputRate (){return this.outputRate;};
	/**
	 * 设置：产值占比
	 */
	public void setoutputRate (Float outputRate){this.outputRate = outputRate;};
	/**
	* 获取：最短工期
	*/
	public Float getshortDateTime (){return this.shortDateTime;};
	/**
	 * 设置：最短工期
	 */
	public void setshortDateTime (Float shortDateTime){this.shortDateTime = shortDateTime;};
	/**
	* 获取：最迟工期
	*/
	public Float getlastDateTime (){return this.lastDateTime;};
	/**
	 * 设置：最迟工期
	 */
	public void setlastDateTime (Float lastDateTime){this.lastDateTime = lastDateTime;};
	/**
	* 获取：项目预算产值
	*/
	public Float getprojectOutput (){return this.projectOutput;};
	/**
	 * 设置：项目预算产值
	 */
	public void setprojectOutput (Float projectOutput){this.projectOutput = projectOutput;};
	/**
	* 获取：项目实际产值
	*/
	public Float getprojectActuallyOutput (){return this.projectActuallyOutput;};
	/**
	 * 设置：项目实际产值
	 */
	public void setprojectActuallyOutput (Float projectActuallyOutput){this.projectActuallyOutput = projectActuallyOutput;};
}
