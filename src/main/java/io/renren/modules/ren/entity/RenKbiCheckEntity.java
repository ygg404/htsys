package io.renren.modules.ren.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 效能分年度考核人员
 * 
 * @author ygg
 * @date 2020-08-26 09:38:17
 */
@TableName("ren_kbi_check")
public class RenKbiCheckEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 自增ID
	 */
	@TableId
	private Long id;
	/**
	 * 用户Id
	 */
	private Long userId;
	/**
	 * 参评年度
	 */
	private Long year;
	/**
	 * 参评月份
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
	* 获取：用户Id
	*/
	public Long getUserId (){return this.userId;};
	/**
	 * 设置：用户Id
	 */
	public void setUserId (Long userId){this.userId = userId;};
	/**
	* 获取：参评年度
	*/
	public Long getYear (){return this.year;};
	/**
	 * 设置：参评年度
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
