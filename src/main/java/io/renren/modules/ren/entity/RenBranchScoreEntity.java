package io.renren.modules.ren.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * 部门与产值的考核分数
 * 
 * @author ygg
 * @date 2020-03-05 10:58:38
 */
@TableName("ren_branch_score")
public class RenBranchScoreEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 自增ID
	 */
	@TableId
	private Long id;
	/**
	 * 部门Id
	 */
	private Long branchId;
	/**
	 * 产值（万）
	 */
	private Long output;
	/**
	 * 分数
	 */
	private Long oscore;

	/**
	* 获取：自增ID
	*/
	public Long getid (){return this.id;};
	/**
	 * 设置：自增ID
	 */
	public void setid (Long id){this.id = id;};
	/**
	* 获取：部门Id
	*/
	public Long getbranchId (){return this.branchId;};
	/**
	 * 设置：部门Id
	 */
	public void setbranchId (Long branchId){this.branchId = branchId;};
	/**
	* 获取：产值（万）
	*/
	public Long getoutput (){return this.output;};
	/**
	 * 设置：产值（万）
	 */
	public void setoutput (Long output){this.output = output;};
	/**
	* 获取：分数
	*/
	public Long getoscore (){return this.oscore;};
	/**
	 * 设置：分数
	 */
	public void setoscore (Long oscore){this.oscore = oscore;};
}
