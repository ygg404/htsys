package io.renren.modules.set.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;


/**
 * 作业类型ID项目类型ID关联表
 * 
 * @author ygg
 * @date 2019-10-31 16:56:32
 */
@TableName("work_project_type")
public class WorkProjectTypeEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Long id;
	/**
	 * 工作类型Id
	 */
	private Long wtypeid;
	/**
	 * 项目类型Id
	 */
	private Long ptypeid;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getWtypeid() {
		return wtypeid;
	}

	public void setWtypeid(Long wtypeid) {
		this.wtypeid = wtypeid;
	}

	public Long getPtypeid() {
		return ptypeid;
	}

	public void setPtypeid(Long ptypeid) {
		this.ptypeid = ptypeid;
	}
}
