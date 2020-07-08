package io.renren.modules.set.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 职务效能分计算方法表
 * 
 * @author ygg
 * @date 2020-06-29 14:17:35
 */
@TableName("set_score_kbi_duty")
public class SetScoreKbiDutyEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 职务ID
	 */
	@TableId
	private Long dutyId;
	/**
	 * 基准分
	 */
	private Long standardScore;
	/**
	 * 未考核时基准分
	 */
	private Long assessnoScore;
	/**
	 * 试用期基准分
	 */
	private Long trialScore;

	/**
	* 获取：职务ID
	*/
	public Long getDutyId (){return this.dutyId;};
	/**
	 * 设置：职务ID
	 */
	public void setDutyId (Long dutyId){this.dutyId = dutyId;};
	/**
	* 获取：基准分
	*/
	public Long getStandardScore (){return this.standardScore;};
	/**
	 * 设置：基准分
	 */
	public void setStandardScore (Long standardScore){this.standardScore = standardScore;};
	/**
	* 获取：未考核时基准分
	*/
	public Long getAssessnoScore (){return this.assessnoScore;};
	/**
	 * 设置：未考核时基准分
	 */
	public void setAssessnoScore (Long assessnoScore){this.assessnoScore = assessnoScore;};
	/**
	* 获取：试用期基准分
	*/
	public Long getTrialScore (){return this.trialScore;};
	/**
	 * 设置：试用期基准分
	 */
	public void setTrialScore (Long trialScore){this.trialScore = trialScore;};
}
