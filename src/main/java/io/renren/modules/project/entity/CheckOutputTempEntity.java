package io.renren.modules.project.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;
/**
 * 产值明细预算表
 * 
 * @author ygg
 * @date 2019-12-26 16:01:03
 */
@TableName("check_output_temp")
public class CheckOutputTempEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * ID
	 */
	@TableId
	private Long id;
	/**
	 * 工作类型id
	 */
	private Long typeId;
	/**
	 * 项目编号
	 */
	private String projectNo;
	/**
	 * 难度系数
	 */
	private Float projectRatio;
	/**
	 * 工作量
	 */
	private Float workLoad;

	/**
	* 获取：ID
	*/
	public Long getid (){return this.id;};
	/**
	 * 设置：ID
	 */
	public void setid (Long id){this.id = id;};
	/**
	* 获取：工作类型id
	*/
	public Long gettypeId (){return this.typeId;};
	/**
	 * 设置：工作类型id
	 */
	public void settypeId (Long typeId){this.typeId = typeId;};
	/**
	* 获取：项目编号
	*/
	public String getprojectNo (){return this.projectNo;};
	/**
	 * 设置：项目编号
	 */
	public void setprojectNo (String projectNo){this.projectNo = projectNo;};
	/**
	* 获取：难度系数
	*/
	public Float getprojectRatio (){return this.projectRatio;};
	/**
	 * 设置：难度系数
	 */
	public void setprojectRatio (Float projectRatio){this.projectRatio = projectRatio;};
	/**
	* 获取：工作量
	*/
	public Float getworkLoad (){return this.workLoad;};
	/**
	 * 设置：工作量
	 */
	public void setworkLoad (Float workLoad){this.workLoad = workLoad;};
}
