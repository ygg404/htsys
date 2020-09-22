package io.renren.modules.project.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 项目成果表
 * 
 * @author ygg
 * @date 2020-09-18 10:56:51
 */
@TableName("project_archives")
public class ProjectArchivesEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 自增ID
	 */
	@TableId
	private Long id;
	/**
	 * 项目编号
	 */
	private String projectNo;
	/**
	 * 签名图片
	 */
	private String sigImage;
	/**
	 * 创建用户Id
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
	* 获取：自增ID
	*/
	public Long getId (){return this.id;};
	/**
	 * 设置：自增ID
	 */
	public void setId (Long id){this.id = id;};
	/**
	* 获取：项目编号
	*/
	public String getProjectNo (){return this.projectNo;};
	/**
	 * 设置：项目编号
	 */
	public void setProjectNo (String projectNo){this.projectNo = projectNo;};
	/**
	* 获取：签名图片
	*/
	public String getSigImage (){return this.sigImage;};
	/**
	 * 设置：签名图片
	 */
	public void setSigImage (String sigImage){this.sigImage = sigImage;};
	/**
	* 获取：创建用户Id
	*/
	public Long getCreateUserId (){return this.createUserId;};
	/**
	 * 设置：创建用户Id
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
}
