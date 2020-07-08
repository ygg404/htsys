package io.renren.modules.set.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * 部门与成员关系表
 * 
 * @author ygg
 * @date 2020-02-24 09:58:46
 */
@TableName("sys_branch_user")
public class BranchUserEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * Id
	 */
	@TableId
	private Long id;
	/**
	 * 部门ID
	 */
	private Long branchId;
	/**
	 * 用户ID
	 */
	private Long userId;

	/**
	* 获取：
	*/
	public Long getid (){return this.id;};
	/**
	 * 设置：
	 */
	public void setid (Long id){this.id = id;};
	/**
	* 获取：部门ID
	*/
	public Long getbranchId (){return this.branchId;};
	/**
	 * 设置：部门ID
	 */
	public void setbranchId (Long branchId){this.branchId = branchId;};
	/**
	* 获取：用户ID
	*/
	public Long getuserId (){return this.userId;};
	/**
	 * 设置：用户ID
	 */
	public void setuserId (Long userId){this.userId = userId;};
}
