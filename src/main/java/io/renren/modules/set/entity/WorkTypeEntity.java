package io.renren.modules.set.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import io.renren.modules.set.entity.ProjectTypeEntity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 作业类型表
 *
 * @author ygg
 * @date 2020-03-26 09:31:24
 */
@TableName("work_type")
public class WorkTypeEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 自增Id
	 */
	@TableId
	private Long id;
	/**
	 * 工作类型名称
	 */
	private String typeName;
	/**
	 * 单位
	 */
	private String unit;
	/**
	 * 单位产值
	 */
	private Float unitOutput;
	/**
	 * 创建时间
	 */
	private Date startDateTime;
	/**
	 * 更新时间
	 */
	private Date updateDateTime;
	/**
	 * 排序号
	 */
	private Long orderNum;

	/**
	 *项目类型ID数组  1 对 多
	 */
	@TableField(exist = false)
	private List<Long> ProjectTypeIdList;
	/**
	 *项目类型列表
	 */
	@TableField(exist = false)
	private List<ProjectTypeEntity> ProjectTypeList;

	/**
	 * 获取：自增Id
	 */
	public Long getId (){return this.id;};
	/**
	 * 设置：自增Id
	 */
	public void setId (Long id){this.id = id;};
	/**
	 * 获取：工作类型名称
	 */
	public String getTypeName (){return this.typeName;};
	/**
	 * 设置：工作类型名称
	 */
	public void setTypeName (String typeName){this.typeName = typeName;};
	/**
	 * 获取：单位
	 */
	public String getUnit (){return this.unit;};
	/**
	 * 设置：单位
	 */
	public void setUnit (String unit){this.unit = unit;};
	/**
	 * 获取：单位产值
	 */
	public Float getUnitOutput (){return this.unitOutput;};
	/**
	 * 设置：单位产值
	 */
	public void setUnitOutput (Float unitOutput){this.unitOutput = unitOutput;};
	/**
	 * 获取：创建时间
	 */
	public Date getStartDateTime (){return this.startDateTime;};
	/**
	 * 设置：创建时间
	 */
	public void setStartDateTime (Date startDateTime){this.startDateTime = startDateTime;};
	/**
	 * 获取：更新时间
	 */
	public Date getUpdateDateTime (){return this.updateDateTime;};
	/**
	 * 设置：更新时间
	 */
	public void setUpdateDateTime (Date updateDateTime){this.updateDateTime = updateDateTime;};
	/**
	 * 获取：排序号
	 */
	public Long getorderNum (){return this.orderNum;};
	/**
	 * 设置：排序号
	 */
	public void setorderNum (Long orderNum){this.orderNum = orderNum;};

	public List<Long> getProjectTypeIdList() {
		return ProjectTypeIdList;
	}

	public void setProjectTypeIdList(List<Long> projectTypeIdList) {
		ProjectTypeIdList = projectTypeIdList;
	}

	public List<ProjectTypeEntity> getProjectTypeList() {
		return ProjectTypeList;
	}

	public void setProjectTypeList(List<ProjectTypeEntity> projectTypeList) {
		ProjectTypeList = projectTypeList;
	}
}
