package io.renren.modules.set.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 职称效能分计算方法表
 * 
 * @author ygg
 * @date 2020-06-29 14:17:35
 */
@TableName("set_score_kbi_title")
public class SetScoreKbiTitleEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Long titleId;
	/**
	 * 效能分
	 */
	private Long standardScore;
	/**
	 * 未考核效能分
	 */
	private Long assessnoScore;
	/**
	 * 试用期效能分
	 */
	private Long trialScore;

	/**
	* 获取：
	*/
	public Long getTitleId (){return this.titleId;};
	/**
	 * 设置：
	 */
	public void setTitleId (Long titleId){this.titleId = titleId;};
	/**
	* 获取：效能分
	*/
	public Long getStandardScore (){return this.standardScore;};
	/**
	 * 设置：效能分
	 */
	public void setStandardScore (Long standardScore){this.standardScore = standardScore;};
	/**
	* 获取：未考核效能分
	*/
	public Long getAssessnoScore (){return this.assessnoScore;};
	/**
	 * 设置：未考核效能分
	 */
	public void setAssessnoScore (Long assessnoScore){this.assessnoScore = assessnoScore;};
	/**
	* 获取：试用期效能分
	*/
	public Long getTrialScore (){return this.trialScore;};
	/**
	 * 设置：试用期效能分
	 */
	public void setTrialScore (Long trialScore){this.trialScore = trialScore;};
}
