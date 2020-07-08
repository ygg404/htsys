package io.renren.modules.project.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 项目表
 * 
 * @author ygg
 * @date 2020-04-07 11:26:11
 */
@TableName("project")
public class ProjectEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 自增ID
	 */
	@TableId
	private Long id;
	/**
	 * 项目编号
	 */
	private String projectNo;
	/**
	 * 合同编号
	 */
	private String contractNo;
	/**
	 * 项目名称
	 */
	private String projectName;
	/**
	 * 项目金额
	 */
	private Float projectMoney;
	/**
	 * 委托单位
	 */
	private String projectAuthorize;
	/**
	 * 项目要求
	 */
	private String projectNote;
	/**
	 * 业务负责人
	 */
	private String projectBusiness;
	/**
	 * 1：正常2：回收站
	 */
	private String pStage;
	/**
	 * 审定内容
	 */
	private String examineNote;
	/**
	 * 审定人Id
	 */
	private Long examUserId;
	/**
	 * 审定人姓名
	 */
	private String examUsername;
	/**
	 * 项目类型
	 */
	private String projectType;
	/**
	 * 项目生产人
	 */
	private String projectProduce;
	/**
	 * 项目生产人账号
	 */
	private String projectProduceAccount;
	/**
	 * 项目生产人Id
	 */
	private Long projectProduceId;
	/**
	 * 项目生产人Id
	 */
	private Long produceGroupId;
	/**
	 * 预计产值备注
	 */
	private String outputRemark;
	/**
	 * 项目启动时间
	 */
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
	private Date projectStartDateTime;
	/**
	 * 项目创建时间
	 */
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
	private Date projectCreateDateTime;
	/**
	 * 创建的用户ID
	 */
	private Long createuserid;

	/**
	* 获取：自增ID
	*/
	public Long getId (){return this.id;};
	/**
	 * 设置：自增ID
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
	* 获取：合同编号
	*/
	public String getcontractNo (){return this.contractNo;};
	/**
	 * 设置：合同编号
	 */
	public void setcontractNo (String contractNo){this.contractNo = contractNo;};
	/**
	* 获取：项目名称
	*/
	public String getprojectName (){return this.projectName;};
	/**
	 * 设置：项目名称
	 */
	public void setprojectName (String projectName){this.projectName = projectName;};
	/**
	* 获取：项目金额
	*/
	public Float getprojectMoney (){return this.projectMoney;};
	/**
	 * 设置：项目金额
	 */
	public void setprojectMoney (Float projectMoney){this.projectMoney = projectMoney;};
	/**
	* 获取：委托单位
	*/
	public String getprojectAuthorize (){return this.projectAuthorize;};
	/**
	 * 设置：委托单位
	 */
	public void setprojectAuthorize (String projectAuthorize){this.projectAuthorize = projectAuthorize;};
	/**
	* 获取：项目要求
	*/
	public String getprojectNote (){return this.projectNote;};
	/**
	 * 设置：项目要求
	 */
	public void setprojectNote (String projectNote){this.projectNote = projectNote;};
	/**
	* 获取：业务负责人
	*/
	public String getprojectBusiness (){return this.projectBusiness;};
	/**
	 * 设置：业务负责人
	 */
	public void setprojectBusiness (String projectBusiness){this.projectBusiness = projectBusiness;};
	/**
	* 获取：1：正常2：回收站
	*/
	public String getpStage (){return this.pStage;};
	/**
	 * 设置：1：正常2：回收站
	 */
	public void setpStage (String pStage){this.pStage = pStage;};
	/**
	* 获取：审定内容
	*/
	public String getexamineNote (){return this.examineNote;};
	/**
	 * 设置：审定内容
	 */
	public void setexamineNote (String examineNote){this.examineNote = examineNote;};
	/**
	* 获取：审定人Id
	*/
	public Long getexamUserId (){return this.examUserId;};
	/**
	 * 设置：审定人Id
	 */
	public void setexamUserId (Long examUserId){this.examUserId = examUserId;};
	/**
	* 获取：审定人姓名
	*/
	public String getexamUsername (){return this.examUsername;};
	/**
	 * 设置：审定人姓名
	 */
	public void setexamUsername (String examUsername){this.examUsername = examUsername;};
	/**
	* 获取：项目类型
	*/
	public String getprojectType (){return this.projectType;};
	/**
	 * 设置：项目类型
	 */
	public void setprojectType (String projectType){this.projectType = projectType;};
	/**
	* 获取：项目生产人
	*/
	public String getprojectProduce (){return this.projectProduce;};
	/**
	 * 设置：项目生产人
	 */
	public void setprojectProduce (String projectProduce){this.projectProduce = projectProduce;};
	/**
	* 获取：项目生产人账号
	*/
	public String getprojectProduceAccount (){return this.projectProduceAccount;};
	/**
	 * 设置：项目生产人账号
	 */
	public void setprojectProduceAccount (String projectProduceAccount){this.projectProduceAccount = projectProduceAccount;};
	/**
	 * 获取：项目生产人ID
	 */
	public Long getProjectProduceId (){return this.projectProduceId;};
	/**
	 * 设置：项目生产人ID
	 */
	public void setProjectProduceId (Long projectProduceId){this.projectProduceId = projectProduceId;};
	/**
	 * 获取：生产部门ID
	 */
	public Long getProduceGroupId (){return this.produceGroupId;};
	/**
	 * 设置：生产部门ID
	 */
	public void setProduceGroupId (Long produceGroupId){this.produceGroupId = produceGroupId;};
	/**
	* 获取：预计产值备注
	*/
	public String getoutputRemark (){return this.outputRemark;};
	/**
	 * 设置：预计产值备注
	 */
	public void setoutputRemark (String outputRemark){this.outputRemark = outputRemark;};
	/**
	* 获取：项目启动时间
	*/
	public Date getprojectStartDateTime (){return this.projectStartDateTime;};
	/**
	 * 设置：项目启动时间
	 */
	public void setprojectStartDateTime (Date projectStartDateTime){this.projectStartDateTime = projectStartDateTime;};
	/**
	* 获取：项目创建时间
	*/
	public Date getprojectCreateDateTime (){return this.projectCreateDateTime;};
	/**
	 * 设置：项目创建时间
	 */
	public void setprojectCreateDateTime (Date projectCreateDateTime){this.projectCreateDateTime = projectCreateDateTime;};
	/**
	* 获取：创建的用户ID
	*/
	public Long getcreateuserid (){return this.createuserid;};
	/**
	 * 设置：创建的用户ID
	 */
	public void setcreateuserid (Long createuserid){this.createuserid = createuserid;};
}
