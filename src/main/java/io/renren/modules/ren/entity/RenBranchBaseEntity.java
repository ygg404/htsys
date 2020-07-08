package io.renren.modules.ren.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 部门考核基本信息
 * 
 * @author ygg
 * @date 2020-03-05 10:58:38
 */
@TableName("ren_branch_base")
public class RenBranchBaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Long branchId;
	/**
	 * 奖惩办法
	 */
	private String method;
	/**
	 * 指标明细
	 */
	private String detail;
	/**
	 * 考核人Id
	 */
	private Long assessorid;
	/**
	 * 考核人姓名
	 */
	private String accessname;
	/**
	 * 得分
	 */
	private Long getScore;
	/**
	 * 产值分数列表
	 */
	@TableField(exist = false)
	private List<RenBranchScoreEntity> scoreList;

	/**
	* 获取：
	*/
	public Long getbranchId (){return this.branchId;};
	/**
	 * 设置：
	 */
	public void setbranchId (Long branchId){this.branchId = branchId;};
	/**
	* 获取：奖惩办法
	*/
	public String getmethod (){return this.method;};
	/**
	 * 设置：奖惩办法
	 */
	public void setmethod (String method){this.method = method;};
	/**
	* 获取：指标明细
	*/
	public String getdetail (){return this.detail;};
	/**
	 * 设置：指标明细
	 */
	public void setdetail (String detail){this.detail = detail;};
	/**
	* 获取：考核人Id
	*/
	public Long getassessorid (){return this.assessorid;};
	/**
	 * 设置：考核人Id
	 */
	public void setassessorid (Long assessorid){this.assessorid = assessorid;};
	/**
	* 获取：考核人姓名
	*/
	public String getaccessname (){return this.accessname;};
	/**
	 * 设置：考核人姓名
	 */
	public void setaccessname (String accessname){this.accessname = accessname;};
	/**
	* 获取：得分
	*/
	public Long getgetScore (){return this.getScore;};
	/**
	 * 设置：得分
	 */
	public void setgetScore (Long getScore){this.getScore = getScore;};
	/**
	 * 获取：产值分数列表
	 */
	public List<RenBranchScoreEntity> getScoreList() { return  this.scoreList;}
	/**
	 * 设置：产值分数列表
	 */
	public void setScoreList(List<RenBranchScoreEntity> scoreList) {  this.scoreList = scoreList;}

}
