package io.renren.modules.ren.vo;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import io.renren.common.utils.R;
import io.renren.modules.ren.entity.RenKbiPersonEntity;

import java.io.Serializable;
import java.util.List;

/**
 * 效能分年度参评人员
 * 
 * @author ygg
 * @date 2020-07-04 10:33:57
 */
public class RenKbiPersonVoEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 用户Id
	 */
	private Long userId;
	/**
	 * 用户名
	 */
	private String username;
	/**
	 * 是否参评（0- 不参评； 1- 参评）
	 */
	private Long isAttend;
	/**
	 * 参评年度
	 */
	private Long year;
	/**
	 * 上下半年(0-上半年，1-下半年 )
	 */
	private Long updown;
	/**
	 * 参评列表
	 */
	private List<RenKbiPersonEntity> kbiPersonList;


	/**
	* 获取：用户名
	*/
	public String getUsername (){return this.username;};
	/**
	 * 设置：用户名
	 */
	public void setUsername (String username){this.username = username;};
	/**
	* 获取：用户Id
	*/
	public Long getUserId (){return this.userId;};
	/**
	 * 设置：用户Id
	 */
	public void setUserId (Long userId){this.userId = userId;};
	/**
	 * 获取：参评
	 */
	public Long getIsAttend  (){return this.isAttend;};
	/**
	 * 设置：参评
	 */
	public void setIsAttend (Long isAttend){this.isAttend = isAttend;};
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
	/**
	 *获取： 参评列表
	 */
	public List<RenKbiPersonEntity> getKbiPersonList() { return this.kbiPersonList;};
	/**
	 *设置： 参评列表
	 */
	public void  setKbiPersonList(List<RenKbiPersonEntity> kbiPersonList) { this.kbiPersonList = kbiPersonList;};

}
