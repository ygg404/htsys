package io.renren.modules.set.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 职级及其房补表
 * 
 * @author ygg
 * @date 2020-06-11 09:29:42
 */
@TableName("set_score_house")
public class SetScoreHouseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 自增Id
	 */
	@TableId
	private Long id;
	/**
	 * 分数下限
	 */
	private Long lowScore;
	/**
	 * 分数上限
	 */
	private Long highScore;
	/**
	 * 职级名称
	 */
	private String jobRank;
	/**
	 * 住房补贴
	 */
	private Long houseAdd;
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
	* 获取：分数下限
	*/
	public Long getLowScore (){return this.lowScore;};
	/**
	 * 设置：分数下限
	 */
	public void setLowScore (Long lowScore){this.lowScore = lowScore;};
	/**
	* 获取：分数上限
	*/
	public Long getHighScore (){return this.highScore;};
	/**
	 * 设置：分数上限
	 */
	public void setHighScore (Long highScore){this.highScore = highScore;};
	/**
	* 获取：职级名称
	*/
	public String getJobRank (){return this.jobRank;};
	/**
	 * 设置：职级名称
	 */
	public void setJobRank (String jobRank){this.jobRank = jobRank;};
	/**
	* 获取：住房补贴
	*/
	public Long getHouseAdd (){return this.houseAdd;};
	/**
	 * 设置：住房补贴
	 */
	public void setHouseAdd (Long houseAdd){this.houseAdd = houseAdd;};
	/**
	* 获取：顺序号
	*/
	public Long getOrderNum (){return this.orderNum;};
	/**
	 * 设置：顺序号
	 */
	public void setOrderNum (Long orderNum){this.orderNum = orderNum;};
}
