package io.renren.modules.project.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;


/**
 * 项目安排表
 * 
 * @author ygg
 * @date 2019-11-01 16:46:10
 */
@TableName("project_plan")
public class ProjectPlanEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * ID
	 */
	@TableId
	private Long id;
	/**
	 * 执行标准
	 */
	private String executeStandard;
	/**
	 * 作业内容
	 */
	private String workNote;
	/**
	 * 技术要求
	 */
	private String workRequire;
	/**
	 * 项目编写人
	 */
	private String projectWriter;
	/**
	 * 项目编号
	 */
	private String projectNo;
	/**
	 * 项目负责人
	 */
	private String projectCharge;
	/**
	 * 项目负责人账号
	 */
	private String projectChargeAccount;
	/**
	 * 项目预计产值
	 */
	private String projectOutput;
	/**
	 * 产值预算明细
	 */
	private String projectOutputNote;
	/**
	 * 工作量
	 */
	private String projectWorkload;
	/**
	 * 项目开工时间
	 */
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
	private Date projectBegunDateTime;
	/**
	 * 作业工期
	 */
	private Float projectWorkDate;
	/**
	 * 质检工期
	 */
	private Float projectQualityDate;
	/**
	 * 项目实际产值
	 */
	private Float projectActuallyOutput;


	/**
	* 获取：ID
	*/
	public Long getId (){return this.id;};
	/**
	 * 设置：ID
	 */
	public void setId (Long id){this.id = id;};
	/**
	* 获取：执行标准
	*/
	public String getexecuteStandard (){return this.executeStandard;};
	/**
	 * 设置：执行标准
	 */
	public void setexecuteStandard (String executeStandard){this.executeStandard = executeStandard;};
	/**
	* 获取：作业内容
	*/
	public String getworkNote (){return this.workNote;};
	/**
	 * 设置：作业内容
	 */
	public void setworkNote (String workNote){this.workNote = workNote;};
	/**
	* 获取：技术要求
	*/
	public String getworkRequire (){return this.workRequire;};
	/**
	 * 设置：技术要求
	 */
	public void setworkRequire (String workRequire){this.workRequire = workRequire;};
	/**
	* 获取：项目编写人
	*/
	public String getprojectWriter (){return this.projectWriter;};
	/**
	 * 设置：项目编写人
	 */
	public void setprojectWriter (String projectWriter){this.projectWriter = projectWriter;};
	/**
	* 获取：项目编号
	*/
	public String getprojectNo (){return this.projectNo;};
	/**
	 * 设置：项目编号
	 */
	public void setprojectNo (String projectNo){this.projectNo = projectNo;};
	/**
	* 获取：项目负责人
	*/
	public String getprojectCharge (){return this.projectCharge;};
	/**
	 * 设置：项目负责人
	 */
	public void setprojectCharge (String projectCharge){this.projectCharge = projectCharge;};
	/**
	* 获取：项目负责人账号
	*/
	public String getprojectChargeAccount (){return this.projectChargeAccount;};
	/**
	 * 设置：项目负责人账号
	 */
	public void setprojectChargeAccount (String projectChargeAccount){this.projectChargeAccount = projectChargeAccount;};
	/**
	* 获取：项目预计产值
	*/
	public String getprojectOutput (){return this.projectOutput;};
	/**
	 * 设置：项目预计产值
	 */
	public void setprojectOutput (String projectOutput){this.projectOutput = projectOutput;};
	/**
	* 获取：产值预算明细
	*/
	public String getprojectOutputNote (){return this.projectOutputNote;};
	/**
	 * 设置：产值预算明细
	 */
	public void setprojectOutputNote (String projectOutputNote){this.projectOutputNote = projectOutputNote;};
	/**
	* 获取：工作量
	*/
	public String getprojectWorkload (){return this.projectWorkload;};
	/**
	 * 设置：工作量
	 */
	public void setprojectWorkload (String projectWorkload){this.projectWorkload = projectWorkload;};
	/**
	* 获取：项目开工时间
	*/
	public Date getprojectBegunDateTime (){return this.projectBegunDateTime;};
	/**
	 * 设置：项目开工时间
	 */
	public void setprojectBegunDateTime (Date projectBegunDateTime){this.projectBegunDateTime = projectBegunDateTime;};
	/**
	* 获取：作业工期
	*/
	public Float getprojectWorkDate (){return this.projectWorkDate;};
	/**
	 * 设置：作业工期
	 */
	public void setprojectWorkDate (Float projectWorkDate){this.projectWorkDate = projectWorkDate;};
	/**
	* 获取：质检工期
	*/
	public Float getprojectQualityDate (){return this.projectQualityDate;};
	/**
	 * 设置：质检工期
	 */
	public void setprojectQualityDate (Float projectQualityDate){this.projectQualityDate = projectQualityDate;};
	/**
	* 获取：项目实际产值
	*/
	public Float getprojectActuallyOutput (){return this.projectActuallyOutput;};
	/**
	 * 设置：项目实际产值
	 */
	public void setprojectActuallyOutput (Float projectActuallyOutput){this.projectActuallyOutput = projectActuallyOutput;};
}
