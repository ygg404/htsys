package io.renren.modules.ren.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 考核目标与用户关系表
 * 
 * @author ygg
 * @date 2020-03-03 09:18:50
 */
@TableName("ren_access_user")
public class RenAccessUserEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 自增Id
	 */
	@TableId
	private Long id;
	/**
	 * 用户Id
	 */
	private Long userId;
	/**
	 * 目标ID
	 */
	private Long accessId;
	/**
	 * 目标ID列表
	 */
	@TableField(exist = false)
	private List<Long> accessIdList;

	/**
	* 获取：自增Id
	*/
	public Long getid (){return this.id;};
	/**
	 * 设置：自增Id
	 */
	public void setid (Long id){this.id = id;};
	/**
	* 获取：用户Id
	*/
	public Long getuserId (){return this.userId;};
	/**
	 * 设置：用户Id
	 */
	public void setuserId (Long userId){this.userId = userId;};
	/**
	* 获取：目标ID
	*/
	public Long getaccessId (){return this.accessId;};
	/**
	 * 设置：目标ID
	 */
	public void setaccessId (Long accessId){this.accessId = accessId;};
	/**
	 * 获取：目标ID列表
	 */
	public List<Long> getAccessIdList (){return this.accessIdList;};
	/**
	 * 设置：目标ID列表
	 */
	public void setAccessIdList ( List<Long> accessIdList){this.accessIdList = accessIdList;};
}
