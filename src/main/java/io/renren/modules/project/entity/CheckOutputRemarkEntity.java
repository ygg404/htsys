package io.renren.modules.project.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 产值核算备注
 * 
 * @author ygg
 * @date 2020-09-22 10:48:54
 */
@TableName("check_output_remark")
public class CheckOutputRemarkEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 自增Id
	 */
	@TableId
	private Long id;
	/**
	 * 项目编号
	 */
	private String projectNo;
	/**
	 * 工作组id
	 */
	private Long groupId;
	/**
	 * 备注
	 */
	private String remark;

	/**
	* 获取：自增Id
	*/
	public Long getId (){return this.id;};
	/**
	 * 设置：自增Id
	 */
	public void setId (Long id){this.id = id;};
	/**
	* 获取：项目编号
	*/
	public String getProjectNo (){return this.projectNo;};
	/**
	 * 设置：项目编号
	 */
	public void setProjectNo (String projectNo){this.projectNo = projectNo;};
	/**
	* 获取：工作组id
	*/
	public Long getGroupId (){return this.groupId;};
	/**
	 * 设置：工作组id
	 */
	public void setGroupId (Long groupId){this.groupId = groupId;};
	/**
	* 获取：备注
	*/
	public String getRemark (){return this.remark;};
	/**
	 * 设置：备注
	 */
	public void setRemark (String remark){this.remark = remark;};
}
