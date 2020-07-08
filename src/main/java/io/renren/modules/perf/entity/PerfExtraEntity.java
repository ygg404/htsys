package io.renren.modules.perf.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 加减分表
 * 
 * @author ygg
 * @date 2020-05-25 17:36:28
 */
@TableName("perf_extra")
public class PerfExtraEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 序号
	 */
	@TableId
	private Long id;
	/**
	 * 加减分项
	 */
	private String extraItem;
	/**
	 * 计分标准
	 */
	private String standard;
	/**
	 * 说明
	 */
	private String remark;
	/**
	 * 加/减类型(0-加分类型；1-减分类型)
	 */
	private Long extraType;

	/**
	* 获取：序号
	*/
	public Long getId (){return this.id;};
	/**
	 * 设置：序号
	 */
	public void setId (Long id){this.id = id;};
	/**
	* 获取：加减分项
	*/
	public String getExtraItem (){return this.extraItem;};
	/**
	 * 设置：加减分项
	 */
	public void setExtraItem (String extraItem){this.extraItem = extraItem;};
	/**
	* 获取：计分标准
	*/
	public String getStandard (){return this.standard;};
	/**
	 * 设置：计分标准
	 */
	public void setStandard (String standard){this.standard = standard;};
	/**
	* 获取：说明
	*/
	public String getRemark (){return this.remark;};
	/**
	 * 设置：说明
	 */
	public void setRemark (String remark){this.remark = remark;};
	/**
	* 获取：加/减类型(0-加分类型；1-减分类型)
	*/
	public Long getExtraType (){return this.extraType;};
	/**
	 * 设置：加/减类型(0-加分类型；1-减分类型)
	 */
	public void setExtraType (Long extraType){this.extraType = extraType;};
}
