package io.renren.modules.project.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 质检报告误差
 * 
 * @author ygg
 * @date 2020-08-19 17:13:37
 */
@TableName("check_error")
public class CheckErrorEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 项目编号
	 */
	@TableId
	private String projectNo;
	/**
	 * 点位中误差
	 */
	private Float errorPoint;
	/**
	 * 间距中误差
	 */
	private Float errorSpace;
	/**
	 * 高程中误差
	 */
	private Float errorHeigh;

	/**
	* 获取：项目编号
	*/
	public String getProjectNo (){return this.projectNo;};
	/**
	 * 设置：项目编号
	 */
	public void setProjectNo (String projectNo){this.projectNo = projectNo;};
	/**
	* 获取：点位中误差
	*/
	public Float getErrorPoint (){return this.errorPoint;};
	/**
	 * 设置：点位中误差
	 */
	public void setErrorPoint (Float errorPoint){this.errorPoint = errorPoint;};
	/**
	* 获取：间距中误差
	*/
	public Float getErrorSpace (){return this.errorSpace;};
	/**
	 * 设置：间距中误差
	 */
	public void setErrorSpace (Float errorSpace){this.errorSpace = errorSpace;};
	/**
	* 获取：高程中误差
	*/
	public Float getErrorHeigh (){return this.errorHeigh;};
	/**
	 * 设置：高程中误差
	 */
	public void setErrorHeigh (Float errorHeigh){this.errorHeigh = errorHeigh;};
}
