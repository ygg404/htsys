package io.renren.modules.ren.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * 考核目标
 * 
 * @author ygg
 * @date 2020-02-28 16:07:08
 */
@TableName("ren_access")
public class RenAccessEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * ID
	 */
	@TableId
	private Long id;
	/**
	 * 父Id
	 */
	private Long parentId;
	/**
	 * 目标名称
	 */
	private String accessName;
	/**
	 * 目标分数
	 */
	private Long accessScore;

	/**
	* 获取：ID
	*/
	public Long getid (){return this.id;};
	/**
	 * 设置：ID
	 */
	public void setid (Long id){this.id = id;};
	/**
	* 获取：父Id
	*/
	public Long getparentId (){return this.parentId;};
	/**
	 * 设置：父Id
	 */
	public void setparentId (Long parentId){this.parentId = parentId;};
	/**
	* 获取：目标名称
	*/
	public String getaccessName (){return this.accessName;};
	/**
	 * 设置：目标名称
	 */
	public void setaccessName (String accessName){this.accessName = accessName;};
	/**
	* 获取：目标分数
	*/
	public Long getaccessScore (){return this.accessScore;};
	/**
	 * 设置：目标分数
	 */
	public void setaccessScore (Long accessScore){this.accessScore = accessScore;};
}
