package io.renren.modules.set.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 项目类型表
 *
 * @author ygg
 * @date 2020-08-14 17:17:05
 */
@TableName("project_type")
public class ProjectTypeEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 项目ID
	 */
	@TableId
	private Long id;
	/**
	 * 项目类型名称
	 */
	private String name;
	/**
	 *
	 */
	private String nameCode;
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
	 * 获取：项目ID
	 */
	public Long getId (){return this.id;};
	/**
	 * 设置：项目ID
	 */
	public void setId (Long id){this.id = id;};
	/**
	 * 获取：项目类型名称
	 */
	public String getName (){return this.name;};
	/**
	 * 设置：项目类型名称
	 */
	public void setName (String name){this.name = name;};
	/**
	 * 获取：
	 */
	public String getNameCode (){return this.nameCode;};
	/**
	 * 设置：
	 */
	public void setNameCode (String nameCode){this.nameCode = nameCode;};
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
