package io.renren.modules.perf.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 加减分年度评分
 *
 * @author ygg
 * @date 2020-05-26 17:32:17
 */
@TableName("perf_extra_scoring")
public class PerfExtraScoringEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 自增ID
	 */
	@TableId
	private Long id;
	/**
	 * 被考核人的ID
	 */
	private Long checkUserId;
	/**
	 * 被考核人的用户名
	 */
	private String checkUserName;
	/**
	 * 加减分项id
	 */
	private Long extraId;
	/**
	 * 加减计分个数
	 */
	private Long extraNum;
	/**
	 * 年份
	 */
	private Long year;
	/**
	 * 月份
	 */
	private Long month;

	/**
	 * 获取：自增ID
	 */
	public Long getId (){return this.id;};
	/**
	 * 设置：自增ID
	 */
	public void setId (Long id){this.id = id;};
	/**
	 * 获取：被考核人的ID
	 */
	public Long getCheckUserId (){return this.checkUserId;};
	/**
	 * 设置：被考核人的ID
	 */
	public void setCheckUserId (Long checkUserId){this.checkUserId = checkUserId;};
	/**
	 * 获取：被考核人的用户名
	 */
	public String getCheckUserName (){return this.checkUserName;};
	/**
	 * 设置：被考核人的用户名
	 */
	public void setCheckUserName (String checkUserName){this.checkUserName = checkUserName;};
	/**
	 * 获取：加减分项id
	 */
	public Long getExtraId (){return this.extraId;};
	/**
	 * 设置：加减分项id
	 */
	public void setExtraId (Long extraId){this.extraId = extraId;};
	/**
	 * 获取：加减计分个数
	 */
	public Long getExtraNum (){return this.extraNum;};
	/**
	 * 设置：加减计分个数
	 */
	public void setExtraNum (Long extraNum){this.extraNum = extraNum;};
	/**
	 * 获取：年份
	 */
	public Long getYear (){return this.year;};
	/**
	 * 设置：年份
	 */
	public void setYear (Long year){this.year = year;};
	/**
	 * 获取：月份
	 */
	public Long getMonth (){return this.month;};
	/**
	 * 设置：月份
	 */
	public void setMonth (Long month){this.month = month;};
}
