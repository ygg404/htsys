package io.renren.modules.ren.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 效能分年度参评人员
 * 
 * @author ygg
 * @date 2020-07-04 10:33:57
 */
@TableName("ren_kbi_person")
public class RenKbiPersonEntity implements Serializable {
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
	 * 上下半年(0-上半年，1-下半年 )
	 */
	private Long updown;

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
	* 获取：上下半年(0-上半年，1-下半年 )
	*/
	public Long getUpdown (){return this.updown;};
	/**
	 * 设置：上下半年(0-上半年，1-下半年 )
	 */
	public void setUpdown (Long updown){this.updown = updown;};
}
