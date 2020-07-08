package io.renren.modules.finance.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 财务操作表
 * 
 * @author ygg
 * @date 2019-10-31 11:18:50
 */
@TableName("project_account")
public class ProjectAccountEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * ID
	 */
	@TableId
	private Long id;
	/**
	 * 合同编号
	 */
	private String contractNo;
	/**
	 * 收支内容
	 */
	private String accountNote;
	/**
	 * 收支金额
	 */
	private Float accountNum;
	/**
	 * 支出类型
	 */
	private String accountType;
	/**
	 * 支出类型名称
	 */
	private String accountTypeName;
	/**
	 * 账号收支添加时间
	 */
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
	private Date accountAddDateTime;

	//用于前端展示 名称
	@TableField(exist = false)
	private String contractName;

	/**
	* 获取：
	*/
	public Long getId (){return this.id;};
	/**
	 * 设置：
	 */
	public void setId (Long id){this.id = id;};
	/**
	* 获取：合同编号
	*/
	public String getcontractNo (){return this.contractNo;};
	/**
	 * 设置：合同编号
	 */
	public void setcontractNo (String contractNo){this.contractNo = contractNo;};
	/**
	* 获取：收支内容
	*/
	public String getaccountNote (){return this.accountNote;};
	/**
	 * 设置：收支内容
	 */
	public void setaccountNote (String accountNote){this.accountNote = accountNote;};
	/**
	* 获取：收支金额
	*/
	public Float getaccountNum (){return this.accountNum;};
	/**
	 * 设置：收支金额
	 */
	public void setaccountNum (Float accountNum){this.accountNum = accountNum;};
	/**
	* 获取：支出类型
	*/
	public String getaccountType (){return this.accountType;};
	/**
	 * 设置：支出类型
	 */
	public void setaccountType (String accountType){this.accountType = accountType;};
	/**
	* 获取：支出类型名称
	*/
	public String getaccountTypeName (){return this.accountTypeName;};
	/**
	 * 设置：支出类型名称
	 */
	public void setaccountTypeName (String accountTypeName){this.accountTypeName = accountTypeName;};
	/**
	* 获取：账号收支添加时间
	*/
	public Date getaccountAddDateTime (){return this.accountAddDateTime;};
	/**
	 * 设置：账号收支添加时间
	 */
	public void setaccountAddDateTime (Date accountAddDateTime){this.accountAddDateTime = accountAddDateTime;};



	public String getContractName() {
		return contractName;
	}

	public void setContractName(String contractName) {
		this.contractName = contractName;
	}
}
