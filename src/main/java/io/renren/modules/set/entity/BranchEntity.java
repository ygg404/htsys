package io.renren.modules.set.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * 部门图表
 * 
 * @author ygg
 * @date 2020-02-21 15:03:54
 */
@TableName("sys_branch")
public class BranchEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 部门Id
	 */
	@TableId
	private Long id;
	/**
	 * 父部门Id
	 */
	private Long parentId;
	/**
	 * 部门名称
	 */
	private String branchName;
	/**
	 * 主负责人Id
	 */
	private Long mdeputyId;
	/**
	 * 副负责人Id
	 */
	private Long sdeputyId;
	/**
	 * 序号
	 */
	private Long orderNum;

	/**
	* 获取：部门Id
	*/
	public Long getid (){return this.id;};
	/**
	 * 设置：部门Id
	 */
	public void setid (Long id){this.id = id;};
	/**
	* 获取：父部门Id
	*/
	public Long getparentId (){return this.parentId;};
	/**
	 * 设置：父部门Id
	 */
	public void setparentId (Long parentId){this.parentId = parentId;};
	/**
	* 获取：部门名称
	*/
	public String getbranchName (){return this.branchName;};
	/**
	 * 设置：部门名称
	 */
	public void setbranchName (String branchName){this.branchName = branchName;};
	/**
	* 获取：主负责人Id
	*/
	public Long getmdeputyId (){return this.mdeputyId;};
	/**
	 * 设置：主负责人Id
	 */
	public void setmdeputyId (Long mdeputyId){this.mdeputyId = mdeputyId;};
	/**
	* 获取：副负责人Id
	*/
	public Long getsdeputyId (){return this.sdeputyId;};
	/**
	 * 设置：副负责人Id
	 */
	public void setsdeputyId (Long sdeputyId){this.sdeputyId = sdeputyId;};
	/**
	* 获取：序号
	*/
	public Long getorderNum (){return this.orderNum;};
	/**
	 * 设置：序号
	 */
	public void setorderNum (Long orderNum){this.orderNum = orderNum;};
}
