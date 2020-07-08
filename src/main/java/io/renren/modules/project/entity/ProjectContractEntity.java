package io.renren.modules.project.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


/**
 * 合同表
 * 
 * @author ygg
 * @date 2019-10-30 15:40:10
 */
@TableName("project_contract")
public class ProjectContractEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	@TableId
	private Long id;
	/**
	 * 合同编号
	 */
	private String contractNo;
	/**
	 * 合同添加时间
	 */
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
	private Date contractAddTime;
	/**
	 * 合同创建时间
	 */
	private Date contractCreateTime;
	/**
	 * 合同委托单位
	 */
	private String contractAuthorize;
	/**
	 * 合同名称
	 */
	private String contractName;
	/**
	 * 合同类型: 0.合同委托 1.一般委托
	 */
	private Integer contractType;
	/**
	 * 合同内容
	 */
	private String contractNote;
	/**
	 * 业务负责人
	 */
	private String contractBusiness;
	/**
	 * 合同状态: 1.正常2.回收站
	 */
	private Integer contractStage;
	/**
	 * 合同金额
	 */
	private Float contractMoney;
	/**
	 * 项目类型
	 */
	private String projectType;
	/**
	 * 文件
	 */
	private String filename;
	/**
	 * 联系人电话
	 */
	private String userPhone;
	/**
	 * 联系人名称
	 */
	private String userName;
	/**
	 * 合同关联的项目列表
	 */
	@TableField(exist = false)
	private List<ProjectEntity> projectList;

	/**
	* 获取：id
	*/
	public Long getid (){return this.id;};
	/**
	 * 设置：id
	 */
	public void setid (Long id){this.id = id;};
	/**
	* 获取：合同编号
	*/
	public String getcontractNo (){return this.contractNo;};
	/**
	 * 设置：合同编号
	 */
	public void setcontractNo (String contractNo){this.contractNo = contractNo;};
	/**
	* 获取：合同添加时间
	*/
	public Date getcontractAddTime (){return this.contractAddTime;};
	/**
	 * 设置：合同添加时间
	 */
	public void setcontractAddTime (Date contractAddTime){this.contractAddTime = contractAddTime;};
	/**
	* 获取：合同创建时间
	*/
	public Date getcontractCreateTime (){return this.contractCreateTime;};
	/**
	 * 设置：合同创建时间
	 */
	public void setcontractCreateTime (Date contractCreateTime){this.contractCreateTime = contractCreateTime;};
	/**
	* 获取：合同委托单位
	*/
	public String getcontractAuthorize (){return this.contractAuthorize;};
	/**
	 * 设置：合同委托单位
	 */
	public void setcontractAuthorize (String contractAuthorize){this.contractAuthorize = contractAuthorize;};
	/**
	* 获取：合同名称
	*/
	public String getcontractName (){return this.contractName;};
	/**
	 * 设置：合同名称
	 */
	public void setcontractName (String contractName){this.contractName = contractName;};
	/**
	* 获取：合同类型: 0.合同委托 1.一般委托
	*/
	public Integer getcontractType (){return this.contractType;};
	/**
	 * 设置：合同类型: 0.合同委托 1.一般委托
	 */
	public void setcontractType (Integer contractType){this.contractType = contractType;};
	/**
	* 获取：合同内容
	*/
	public String getcontractNote (){return this.contractNote;};
	/**
	 * 设置：合同内容
	 */
	public void setcontractNote (String contractNote){this.contractNote = contractNote;};
	/**
	* 获取：业务负责人
	*/
	public String getcontractBusiness (){return this.contractBusiness;};
	/**
	 * 设置：业务负责人
	 */
	public void setcontractBusiness (String contractBusiness){this.contractBusiness = contractBusiness;};
	/**
	* 获取：合同状态: 1.正常2.回收站
	*/
	public Integer getcontractStage (){return this.contractStage;};
	/**
	 * 设置：合同状态: 1.正常2.回收站
	 */
	public void setcontractStage (Integer contractStage){this.contractStage = contractStage;};
	/**
	* 获取：合同金额
	*/
	public Float getcontractMoney (){return this.contractMoney;};
	/**
	 * 设置：合同金额
	 */
	public void setcontractMoney (Float contractMoney){this.contractMoney = contractMoney;};
	/**
	* 获取：项目类型
	*/
	public String getprojectType (){return this.projectType;};
	/**
	 * 设置：项目类型
	 */
	public void setprojectType (String projectType){this.projectType = projectType;};
	/**
	* 获取：文件
	*/
	public String getfilename (){return this.filename;};
	/**
	 * 设置：文件
	 */
	public void setfilename (String filename){this.filename = filename;};
	/**
	* 获取：联系人电话
	*/
	public String getuserPhone (){return this.userPhone;};
	/**
	 * 设置：联系人电话
	 */
	public void setuserPhone (String userPhone){this.userPhone = userPhone;};
	/**
	* 获取：联系人名称
	*/
	public String getuserName (){return this.userName;};
	/**
	 * 设置：联系人名称
	 */
	public void setuserName (String userName){this.userName = userName;};
	/**
	 * 获取：合同关联的项目列表
	 */
	public List<ProjectEntity> getprojectList() { return  this.projectList;};
	/**
	 * 设置：合同关联的项目列表
	 */
	public void setprojectList( List<ProjectEntity> projectList) { this.projectList = projectList;};
}
