package io.renren.modules.set.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * 快捷短语(表)输入
 * 
 * @author ygg
 * @date 2020-05-12 10:32:38
 */
@TableName("wp_shortcut")
public class WpShortcutEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 自增id
	 */
	@TableId
	private Long id;
	/**
	 * 短语类型内容
	 */
	private String shortcutNote;
	/**
	 * 短语姓名
	 */
	private String shortcutName;
	/**
	 * 短语类型Id
	 */
	private String shortcutTypeId;
	/**
	 * 关联的项目类型
	 */
	private String projectType;
	/**
	 * 相关项目类型ID
	 */
	private String projectTypeId;

	/**
	* 获取：自增id
	*/
	public Long getId (){return this.id;};
	/**
	 * 设置：自增id
	 */
	public void setId (Long id){this.id = id;};
	/**
	* 获取：短语类型内容
	*/
	public String getShortcutNote (){return this.shortcutNote;};
	/**
	 * 设置：短语类型内容
	 */
	public void setShortcutNote (String shortcutNote){this.shortcutNote = shortcutNote;};
	/**
	* 获取：短语姓名
	*/
	public String getShortcutName (){return this.shortcutName;};
	/**
	 * 设置：短语姓名
	 */
	public void setShortcutName (String shortcutName){this.shortcutName = shortcutName;};
	/**
	* 获取：短语类型Id
	*/
	public String getShortcutTypeId (){return this.shortcutTypeId;};
	/**
	 * 设置：短语类型Id
	 */
	public void setShortcutTypeId (String shortcutTypeId){this.shortcutTypeId = shortcutTypeId;};
	/**
	* 获取：关联的项目类型
	*/
	public String getProjectType (){return this.projectType;};
	/**
	 * 设置：关联的项目类型
	 */
	public void setProjectType (String projectType){this.projectType = projectType;};
	/**
	* 获取：相关项目类型ID
	*/
	public String getProjectTypeId (){return this.projectTypeId;};
	/**
	 * 设置：相关项目类型ID
	 */
	public void setProjectTypeId (String projectTypeId){this.projectTypeId = projectTypeId;};
}
