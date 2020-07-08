package io.renren.modules.set.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;


/**
 * 项目类型表
 * 
 * @author ygg
 * @date 2019-10-29 10:39:04
 */
@TableName("project_type")
public class ProjectTypeEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * ID
	 */
	@TableId
	private Long id;
	/**
	 * 
	 */
	private String name;


	/**
	* 获取：
	*/
	public Long getId (){return this.id;};
	/**
	 * 设置：
	 */
	public void setId (Long id){this.id = id;};
	/**
	* 获取：
	*/
	public String getName (){return this.name;};
	/**
	 * 设置：
	 */
	public void setName (String name){this.name = name;};

}
