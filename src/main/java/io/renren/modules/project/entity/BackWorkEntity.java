package io.renren.modules.project.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * 返修表
 * 
 * @author ygg
 * @date 2019-11-28 11:52:47
 */
@TableName("back_work")
public class BackWorkEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * ID
	 */
	@TableId
	private Long id;
	/**
	 * 项目编号
	 */
	private String projectNo;
	/**
	 * 返修内容
	 */
	private String backNote;
	/**
	 * 返修创建时间
	 */
	private Date backCreateTime;
	/**
	 * 提交内容
	 */
	private String submitNote;
	/**
	 * 提交内容时间
	 */
	private Date submitCreateTime;
	/**
	 * 返修状态
	 */
	private Long backStage;
	/**
	 * 返修天数
	 */
	private Long backDateNum;
	/**
	 * 组ID
	 */
	private Long groupId;

	/**
	* 获取：ID
	*/
	public Long getid (){return this.id;};
	/**
	 * 设置：ID
	 */
	public void setid (Long id){this.id = id;};
	/**
	* 获取：项目编号
	*/
	public String getprojectNo (){return this.projectNo;};
	/**
	 * 设置：项目编号
	 */
	public void setprojectNo (String projectno){this.projectNo = projectno;};
	/**
	* 获取：返修内容
	*/
	public String getbackNote (){return this.backNote;};
	/**
	 * 设置：返修内容
	 */
	public void setbackNote (String backnote){this.backNote = backnote;};
	/**
	* 获取：返修创建时间
	*/
	public Date getbackCreateTime (){return this.backCreateTime;};
	/**
	 * 设置：返修创建时间
	 */
	public void setbackCreateTime (Date backcreatetime){this.backCreateTime = backcreatetime;};
	/**
	* 获取：提交内容
	*/
	public String getsubmitNote (){return this.submitNote;};
	/**
	 * 设置：提交内容
	 */
	public void setsubmitNote (String submitnote){this.submitNote = submitnote;};
	/**
	* 获取：提交内容时间
	*/
	public Date getsubmitCreateTime (){return this.submitCreateTime;};
	/**
	 * 设置：提交内容时间
	 */
	public void setsubmitCreateTime (Date submitcreatetime){this.submitCreateTime = submitcreatetime;};
	/**
	* 获取：返修状态
	*/
	public Long getbackStage (){return this.backStage;};
	/**
	 * 设置：返修状态
	 */
	public void setbackStage (Long backstage){this.backStage = backstage;};
	/**
	* 获取：返修天数
	*/
	public Long getbackDateNum (){return this.backDateNum;};
	/**
	 * 设置：返修天数
	 */
	public void setbackDateNum (Long backdatenum){this.backDateNum = backdatenum;};
	/**
	* 获取：组ID
	*/
	public Long getgroupId (){return this.groupId;};
	/**
	 * 设置：组ID
	 */
	public void setgroupId (Long groupid){this.groupId = groupid;};
}
