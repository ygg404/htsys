package io.renren.modules.sys.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * 效能考核项设置
 * 
 * @author ygg
 * @date 2020-05-15 10:26:46
 */
@TableName("sys_role_kbi")
public class SysRoleKbiEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 自增ID
	 */
	@TableId
	private Long id;
	/**
	 * 岗位ID
	 */
	private Long roleId;
	/**
	 * 岗位ID
	 */
	private Long kbiId;
	/**
	 * 考核占比
	 */
	private Long kbiRatio;

	/**
	* 获取：自增ID
	*/
	public Long getId (){return this.id;};
	/**
	 * 设置：自增ID
	 */
	public void setId (Long id){this.id = id;};
	/**
	* 获取：岗位ID
	*/
	public Long getRoleId (){return this.roleId;};
	/**
	 * 设置：岗位ID
	 */
	public void setRoleId (Long roleId){this.roleId = roleId;};
	/**
	* 获取：岗位ID
	*/
	public Long getKbiId (){return this.kbiId;};
	/**
	 * 设置：岗位ID
	 */
	public void setKbiId (Long kbiId){this.kbiId = kbiId;};
	/**
	* 获取：考核占比
	*/
	public Long getKbiRatio (){return this.kbiRatio;};
	/**
	 * 设置：考核占比
	 */
	public void setKbiRatio (Long kbiRatio){this.kbiRatio = kbiRatio;};
}
