package io.renren.modules.sys.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 效能考核项
 * 
 * @author ygg
 * @date 2020-05-15 11:14:45
 */
@TableName("sys_kbi")
public class SysKbiEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 考核项ID
	 */
	@TableId
	private Long id;
	/**
	 * 考核项名称
	 */
	private String kbiName;
	/**
	 * 序号
	 */
	private Long orderNum;

	/**
	* 获取：考核项ID
	*/
	public Long getId (){return this.id;};
	/**
	 * 设置：考核项ID
	 */
	public void setId (Long id){this.id = id;};
	/**
	* 获取：考核项名称
	*/
	public String getKbiName (){return this.kbiName;};
	/**
	 * 设置：考核项名称
	 */
	public void setKbiName (String kbiName){this.kbiName = kbiName;};
	/**
	* 获取：序号
	*/
	public Long getOrderNum (){return this.orderNum;};
	/**
	 * 设置：序号
	 */
	public void setOrderNum (Long orderNum){this.orderNum = orderNum;};
}
