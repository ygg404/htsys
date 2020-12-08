package io.renren.modules.project.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 项目清单表
 * 
 * @author ygg
 * @date 2020-12-08 11:07:29
 */
@TableName("project_bill")
public class ProjectBillEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Long id;
	/**
	 * 项目编号
	 */
	private String projectNo;
	/**
	 * 项目名称
	 */
	private String billName;
	/**
	 * 规格
	 */
	private String spec;
	/**
	 * 数量
	 */
	private Long num;
	/**
	 * 备注
	 */
	private String remark;

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
	public String getProjectNo (){return this.projectNo;};
	/**
	 * 设置：项目编号
	 */
	public void setProjectNo (String projectNo){this.projectNo = projectNo;};
	/**
	* 获取：项目名称
	*/
	public String getBillName (){return this.billName;};
	/**
	 * 设置：项目名称
	 */
	public void setBillName (String billName){this.billName = billName;};
	/**
	* 获取：规格
	*/
	public String getSpec (){return this.spec;};
	/**
	 * 设置：规格
	 */
	public void setSpec (String spec){this.spec = spec;};
	/**
	* 获取：数量
	*/
	public Long getNum (){return this.num;};
	/**
	 * 设置：数量
	 */
	public void setNum (Long num){this.num = num;};
	/**
	* 获取：备注
	*/
	public String getRemark (){return this.remark;};
	/**
	 * 设置：备注
	 */
	public void setRemark (String remark){this.remark = remark;};
}
