package io.renren.modules.project.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * 质量评分详情
 * 
 * @author ygg
 * @date 2019-11-16 11:21:53
 */
@TableName("quality_score")
public class QualityScoreEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * ID
	 */
	@TableId
	private Long id;
	/**
	 * 评分类型ID
	 */
	private Long typeId;
	/**
	 * 评分类型
	 */
	private String checkType;
	/**
	 * 
	 */
	private String checkResult;
	/**
	 * A类分
	 */
	private String checkA;
	/**
	 * B类分
	 */
	private String checkB;
	/**
	 * C类分
	 */
	private String checkC;
	/**
	 * D类分
	 */
	private String checkD;
	/**
	 * 项目编号
	 */
	private String projectNo;

	/**
	* 获取：ID
	*/
	public Long getid (){return this.id;};
	/**
	 * 设置：ID
	 */
	public void setid (Long id){this.id = id;};
	/**
	* 获取：评分类型ID
	*/
	public Long gettypeId (){return this.typeId;};
	/**
	 * 设置：评分类型ID
	 */
	public void settypeId (Long typeId){this.typeId = typeId;};
	/**
	* 获取：评分类型
	*/
	public String getcheckType (){return this.checkType;};
	/**
	 * 设置：评分类型
	 */
	public void setcheckType (String checkType){this.checkType = checkType;};
	/**
	* 获取：
	*/
	public String getcheckResult (){return this.checkResult;};
	/**
	 * 设置：
	 */
	public void setcheckResult (String checkResult){this.checkResult = checkResult;};
	/**
	* 获取：A类分
	*/
	public String getcheckA (){return this.checkA;};
	/**
	 * 设置：A类分
	 */
	public void setcheckA (String checkA){this.checkA = checkA;};
	/**
	* 获取：B类分
	*/
	public String getcheckB (){return this.checkB;};
	/**
	 * 设置：B类分
	 */
	public void setcheckB (String checkB){this.checkB = checkB;};
	/**
	* 获取：C类分
	*/
	public String getcheckC (){return this.checkC;};
	/**
	 * 设置：C类分
	 */
	public void setcheckC (String checkC){this.checkC = checkC;};
	/**
	* 获取：D类分
	*/
	public String getcheckD (){return this.checkD;};
	/**
	 * 设置：D类分
	 */
	public void setcheckD (String checkD){this.checkD = checkD;};
	/**
	* 获取：项目编号
	*/
	public String getprojectNo (){return this.projectNo;};
	/**
	 * 设置：项目编号
	 */
	public void setprojectNo (String projectNo){this.projectNo = projectNo;};
}
