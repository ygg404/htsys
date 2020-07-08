package io.renren.modules.sys.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import io.renren.common.validator.group.AddGroup;
import io.renren.common.validator.group.UpdateGroup;

/**
 * 系统用户
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2016年9月18日 上午9:28:55
 */
@TableName("sys_user")
public class SysUserEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 用户ID
	 */
	@TableId
	private Long userId;

	/**
	 * 用户账号
	 */
	@NotBlank(message = "登录账号不能为空", groups = { AddGroup.class, UpdateGroup.class })
	private String useraccount;

	/**
	 * 用户名
	 */
	@NotBlank(message = "用户名不能为空", groups = { AddGroup.class, UpdateGroup.class })
	private String username;

	/**
	 * 密码
	 */
	@NotBlank(message = "密码不能为空", groups = AddGroup.class)
	private String password;

	/**
	 * 盐
	 */
	private String salt;

	/**
	 * 状态 0：禁用 1：正常
	 */
	private Integer status;

	/**
	 * 角色ID列表
	 */
	@TableField(exist = false)
	private Long WorkGroupID;


	/**
	 * 角色ID列表
	 */
	@TableField(exist = false)
	private List<Long> roleIdList;

	//用户组名称
	@TableField(exist = false)
	private String WorkGroupName;

	@TableField(exist = false)
	private String RName;
	/**
	 * 创建者ID
	 */
	private Long createUserId;

	/**
	 * 创建时间
	 */
	private Date createTime;

	/**
	 * 设置：
	 * 
	 * @param userId
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}

	/**
	 * 获取：
	 * 
	 * @return Long
	 */
	public Long getUserId() {
		return userId;
	}

	/**
	 * 设置：账号
	 * 
	 * @param useraccount 账号
	 */
	public void setUseraccount(String useraccount) {
		this.useraccount = useraccount;
	}

	/**
	 * 获取：账号
	 * 
	 * @return String
	 */
	public String getUseraccount() {
		return useraccount;
	}

	/**
	 * 设置：用户名
	 *
	 * @param username 用户名
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * 获取：用户名
	 *
	 * @return String
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * 设置：密码
	 * 
	 * @param password 密码
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * 获取：密码
	 * 
	 * @return String
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * 设置：状态 0：禁用 1：正常
	 * 
	 * @param status 状态 0：禁用 1：正常
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}

	/**
	 * 获取：状态 0：禁用 1：正常
	 * 
	 * @return Integer
	 */
	public Integer getStatus() {
		return status;
	}

	/**
	 * 设置：创建时间
	 * 
	 * @param createTime 创建时间
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * 获取：创建时间
	 * 
	 * @return Date
	 */
	public Date getCreateTime() {
		return createTime;
	}

	public List<Long> getRoleIdList() {
		return roleIdList;
	}

	public void setRoleIdList(List<Long> roleIdList) {
		this.roleIdList = roleIdList;
	}

	public Long getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(Long createUserId) {
		this.createUserId = createUserId;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public String getRName() {
		return RName;
	}

	public void setRName(String RName) {
		this.RName = RName;
	}

	public String getWorkGroupName() {
		return WorkGroupName;
	}

	public void setWorkGroupName(String workGroupName) {
		WorkGroupName = workGroupName;
	}

	public Long getWorkGroupID() {
		return WorkGroupID;
	}

	public void setWorkGroupID(Long workGroupID) {
		WorkGroupID = workGroupID;
	}
}
