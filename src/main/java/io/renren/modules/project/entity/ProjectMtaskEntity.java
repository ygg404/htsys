package io.renren.modules.project.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 作业任务分工表
 * 
 * @author ygg
 * @date 2020-12-29 15:41:51
 */
@TableName("project_mtask")
public class ProjectMtaskEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Long id;
	/**
	 * 项目编号
	 */
	private String projectNo;
	/**
	 * 项目技术负责人
	 */
	private String projectCharge;
	/**
	 * 任务分工
	 */
	private String chargeTask;
	/**
	 * 项目组成员1
	 */
	private String projectMenber1;
	/**
	 * 任务分工
	 */
	private String menber1Task;
	/**
	 * 项目组成员2
	 */
	private String projectMenber2;
	/**
	 * 任务分工
	 */
	private String menber2Task;
	/**
	 * 项目组成员3
	 */
	private String projectMenber3;
	/**
	 * 任务分工
	 */
	private String menber3Task;

	/**
	* 获取：
	*/
	public Long getId (){return this.id;};
	/**
	 * 设置：
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
	* 获取：项目技术负责人
	*/
	public String getProjectCharge (){return this.projectCharge;};
	/**
	 * 设置：项目技术负责人
	 */
	public void setProjectCharge (String projectCharge){this.projectCharge = projectCharge;};
	/**
	* 获取：任务分工
	*/
	public String getChargeTask (){return this.chargeTask;};
	/**
	 * 设置：任务分工
	 */
	public void setChargeTask (String chargeTask){this.chargeTask = chargeTask;};
	/**
	* 获取：项目组成员1
	*/
	public String getProjectMenber1 (){return this.projectMenber1;};
	/**
	 * 设置：项目组成员1
	 */
	public void setProjectMenber1 (String projectMenber1){this.projectMenber1 = projectMenber1;};
	/**
	* 获取：任务分工
	*/
	public String getMenber1Task (){return this.menber1Task;};
	/**
	 * 设置：任务分工
	 */
	public void setMenber1Task (String menber1Task){this.menber1Task = menber1Task;};
	/**
	* 获取：项目组成员2
	*/
	public String getProjectMenber2 (){return this.projectMenber2;};
	/**
	 * 设置：项目组成员2
	 */
	public void setProjectMenber2 (String projectMenber2){this.projectMenber2 = projectMenber2;};
	/**
	* 获取：任务分工
	*/
	public String getMenber2Task (){return this.menber2Task;};
	/**
	 * 设置：任务分工
	 */
	public void setMenber2Task (String menber2Task){this.menber2Task = menber2Task;};
	/**
	* 获取：项目组成员3
	*/
	public String getProjectMenber3 (){return this.projectMenber3;};
	/**
	 * 设置：项目组成员3
	 */
	public void setProjectMenber3 (String projectMenber3){this.projectMenber3 = projectMenber3;};
	/**
	* 获取：任务分工
	*/
	public String getMenber3Task (){return this.menber3Task;};
	/**
	 * 设置：任务分工
	 */
	public void setMenber3Task (String menber3Task){this.menber3Task = menber3Task;};
}
