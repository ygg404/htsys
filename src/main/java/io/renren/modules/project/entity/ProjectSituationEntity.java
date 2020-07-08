package io.renren.modules.project.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * 项目情况表
 * 
 * @author ygg
 * @date 2019-11-26 09:06:04
 */
@TableName("project_situation")
public class ProjectSituationEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 自增ID
	 */
	@TableId
	private Long Id;
	/**
	 * 项目编号
	 */
	private String projectNo;
	/**
	 * 是否安排（0:未安排，1:已经安排）
	 */
	private Long isPlan;
	/**
	 * 是否工作（0:未工作，1:已工作）
	 */
	private Long isWork;
	/**
	 * 是否质检（0:未质检，1:已质检）
	 */
	private Long isCheck;
	/**
	 * 是否质审核（0:未质审，1:已质审）
	 */
	private Long isQauth;
	/**
	 * 是否核算（0:未核算，1:已核算）
	 */
	private Long isOutput;
	/**
	 * 是否审核（0:未审核，1:已审核）
	 */
	private Long isAuthorize;

	/**
	* 获取：自增ID
	*/
	public Long getId (){return this.Id;};
	/**
	 * 设置：自增ID
	 */
	public void setId (Long id){this.Id = id;};
	/**
	* 获取：项目编号
	*/
	public String getprojectNo (){return this.projectNo;};
	/**
	 * 设置：项目编号
	 */
	public void setprojectNo (String projectNo){this.projectNo = projectNo;};
	/**
	* 获取：是否安排（0:未安排，1:已经安排）
	*/
	public Long getisPlan (){return this.isPlan;};
	/**
	 * 设置：是否安排（0:未安排，1:已经安排）
	 */
	public void setisPlan (Long isPlan){this.isPlan = isPlan;};
	/**
	* 获取：是否工作（0:未工作，1:已工作）
	*/
	public Long getisWork (){return this.isWork;};
	/**
	 * 设置：是否工作（0:未工作，1:已工作）
	 */
	public void setisWork (Long isWork){this.isWork = isWork;};
	/**
	* 获取：是否质检（0:未质检，1:已质检）
	*/
	public Long getisCheck (){return this.isCheck;};
	/**
	 * 设置：是否质检（0:未质检，1:已质检）
	 */
	public void setisCheck (Long isCheck){this.isCheck = isCheck;};
	/**
	 * 获取：是否质审（0:未质审，1:已质审）
	 */
	public Long getisQauth (){return this.isQauth;};
	/**
	 * 设置：是否质审（0:未质检，1:已质审）
	 */
	public void setisQauth (Long isQauth){this.isQauth = isQauth;};
	/**
	* 获取：是否核算（0:未核算，1:已核算）
	*/
	public Long getisOutput (){return this.isOutput;};
	/**
	 * 设置：是否核算（0:未核算，1:已核算）
	 */
	public void setisOutput (Long isOutput){this.isOutput = isOutput;};
	/**
	* 获取：是否审核（0:未审核，1:已审核）
	*/
	public Long getisAuthorize (){return this.isAuthorize;};
	/**
	 * 设置：是否审核（0:未审核，1:已审核）
	 */
	public void setisAuthorize (Long isAuthorize){this.isAuthorize = isAuthorize;};
}
