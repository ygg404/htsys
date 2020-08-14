package io.renren.modules.set.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 质量评分表设置
 * 
 * @author ygg
 * @date 2020-08-14 10:17:40
 */
@TableName("set_quality_score")
public class SetQualityScoreEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 评分选项ID
	 */
	@TableId
	private Long typeId;
	/**
	 * 评分权重
	 */
	private Float scoreRadio;
	/**
	 * A类错误名
	 */
	private String typeaName;
	/**
	 * B类错误名
	 */
	private String typebName;
	/**
	 * C类错误名
	 */
	private String typecName;
	/**
	 * D类错误名
	 */
	private String typedName;
	/**
	 * 质量元素类别
	 */
	private String qualityCate;
	/**
	 * 文件编号
	 */
	private Long fileNo;

	/**
	* 获取：评分选项ID
	*/
	public Long getTypeId (){return this.typeId;};
	/**
	 * 设置：评分选项ID
	 */
	public void setTypeId (Long typeId){this.typeId = typeId;};
	/**
	* 获取：评分权重
	*/
	public Float getScoreRadio (){return this.scoreRadio;};
	/**
	 * 设置：评分权重
	 */
	public void setScoreRadio (Float scoreRadio){this.scoreRadio = scoreRadio;};
	/**
	* 获取：A类错误名
	*/
	public String getTypeaName (){return this.typeaName;};
	/**
	 * 设置：A类错误名
	 */
	public void setTypeaName (String typeaName){this.typeaName = typeaName;};
	/**
	* 获取：B类错误名
	*/
	public String getTypebName (){return this.typebName;};
	/**
	 * 设置：B类错误名
	 */
	public void setTypebName (String typebName){this.typebName = typebName;};
	/**
	* 获取：C类错误名
	*/
	public String getTypecName (){return this.typecName;};
	/**
	 * 设置：C类错误名
	 */
	public void setTypecName (String typecName){this.typecName = typecName;};
	/**
	* 获取：D类错误名
	*/
	public String getTypedName (){return this.typedName;};
	/**
	 * 设置：D类错误名
	 */
	public void setTypedName (String typedName){this.typedName = typedName;};
	/**
	* 获取：质量元素类别
	*/
	public String getQualityCate (){return this.qualityCate;};
	/**
	 * 设置：质量元素类别
	 */
	public void setQualityCate (String qualityCate){this.qualityCate = qualityCate;};
	/**
	* 获取：文件编号
	*/
	public Long getFileNo (){return this.fileNo;};
	/**
	 * 设置：文件编号
	 */
	public void setFileNo (Long fileNo){this.fileNo = fileNo;};
}
