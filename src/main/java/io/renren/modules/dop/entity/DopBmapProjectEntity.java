package io.renren.modules.dop.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 百度地图项目名称
 * 
 * @author ygg
 * @date 2020-10-22 10:56:31
 */
@TableName("dop_bmap_project")
public class DopBmapProjectEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 自增ID
	 */
	@TableId
	private Long id;
	/**
	 * 项目名称
	 */
	private String projectName;
	/**
	 * 创建用户ID
	 */
	private Long createUserId;
	/**
	 * 创建用户名
	 */
	private String createUserName;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 项目下的标注点
	 */
	@TableField(exist = false)
	private List<DopBmapEntity> bmapList;

	/**
	* 获取：自增ID
	*/
	public Long getId (){return this.id;};
	/**
	 * 设置：自增ID
	 */
	public void setId (Long id){this.id = id;};
	/**
	* 获取：项目名称
	*/
	public String getProjectName (){return this.projectName;};
	/**
	 * 设置：项目名称
	 */
	public void setProjectName (String projectName){this.projectName = projectName;};
	/**
	* 获取：创建用户ID
	*/
	public Long getCreateUserId (){return this.createUserId;};
	/**
	 * 设置：创建用户ID
	 */
	public void setCreateUserId (Long createUserId){this.createUserId = createUserId;};
	/**
	* 获取：创建用户名
	*/
	public String getCreateUserName (){return this.createUserName;};
	/**
	 * 设置：创建用户名
	 */
	public void setCreateUserName (String createUserName){this.createUserName = createUserName;};
	/**
	* 获取：创建时间
	*/
	public Date getCreateTime (){return this.createTime;};
	/**
	 * 设置：创建时间
	 */
	public void setCreateTime (Date createTime){this.createTime = createTime;};
	/**
	 * 获取：项目下的标注点
	 */
	public List<DopBmapEntity> getBmapList() {return this.bmapList;}
	/**
	 * 设置：项目下的标注点
	 */
	public void setBmapList(List<DopBmapEntity> bmapList) {this.bmapList = bmapList;}
}
