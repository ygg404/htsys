package io.renren.modules.ren.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 应出勤天数表
 * 
 * @author ygg
 * @date 2020-03-10 15:32:12
 */
@TableName("ren_attend_days")
public class RenAttendDaysEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 出勤月份
	 */
	@TableId
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM")
	private Date date;
	/**
	 * 应出勤天数
	 */
	private float attendDays;

	/**
	* 获取：出勤月份
	*/
	public Date getdate (){return this.date;};
	/**
	 * 设置：出勤月份
	 */
	public void setdate (Date date){this.date = date;};
	/**
	* 获取：应出勤天数
	*/
	public float getattendDays (){return this.attendDays;};
	/**
	 * 设置：应出勤天数
	 */
	public void setattendDays (float attendDays){this.attendDays = attendDays;};
}
