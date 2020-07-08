package io.renren.modules.project.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;


/**
 * 项目阶段表
 * 
 * @author ygg
 * @date 2019-11-01 16:46:10
 */
@TableName("project_stage")
public class ProjectStageEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Integer id;
	/**
	 * 
	 */
	private String name;

	/**
	* 获取：
	*/
	public Integer getid (){return this.id;};
	/**
	 * 设置：
	 */
	public void setid (Integer id){this.id = id;};
	/**
	* 获取：
	*/
	public String getname (){return this.name;};
	/**
	 * 设置：
	 */
	public void setname (String name){this.name = name;};
}
