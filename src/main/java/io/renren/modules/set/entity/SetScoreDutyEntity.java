package io.renren.modules.set.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 职务评分表
 * 
 * @author ygg
 * @date 2020-06-11 09:29:42
 */
@TableName("set_score_duty")
public class SetScoreDutyEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 自增Id
	 */
	@TableId
	private Long id;
	/**
	 * 职务
	 */
	private String duty;
	/**
	 * 分数
	 */
	private Long score;
	/**
	 * 顺序号
	 */
	private Long orderNum;

	/**
	* 获取：自增Id
	*/
	public Long getId (){return this.id;};
	/**
	 * 设置：自增Id
	 */
	public void setId (Long id){this.id = id;};
	/**
	* 获取：职务
	*/
	public String getDuty (){return this.duty;};
	/**
	 * 设置：职务
	 */
	public void setDuty (String duty){this.duty = duty;};
	/**
	* 获取：分数
	*/
	public Long getScore (){return this.score;};
	/**
	 * 设置：分数
	 */
	public void setScore (Long score){this.score = score;};
	/**
	* 获取：顺序号
	*/
	public Long getOrderNum (){return this.orderNum;};
	/**
	 * 设置：顺序号
	 */
	public void setOrderNum (Long orderNum){this.orderNum = orderNum;};
}
