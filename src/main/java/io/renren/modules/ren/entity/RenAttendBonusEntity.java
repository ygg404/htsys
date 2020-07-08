package io.renren.modules.ren.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * 年度全勤奖天数
 * 
 * @author ygg
 * @date 2020-03-21 14:24:14
 */
@TableName("ren_attend_bonus")
public class RenAttendBonusEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 年度
	 */
	@TableId
	private Long year;
	/**
	 * 全勤奖天数
	 */
	private Long bonusDay;

	/**
	* 获取：年度
	*/
	public Long getYear (){return this.year;};
	/**
	 * 设置：年度
	 */
	public void setYear (Long year){this.year = year;};
	/**
	* 获取：全勤奖天数
	*/
	public Long getbonusDay (){return this.bonusDay;};
	/**
	 * 设置：全勤奖天数
	 */
	public void setbonusDay (Long bonusDay){this.bonusDay = bonusDay;};
}
