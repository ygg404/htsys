package io.renren.modules.set.entity;



import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;


/**
 * 类型名称
 * 
 * @author ygg
 * @email sunlightcs@gmail.com
 * @date 2019-10-23 09:28:19
 */
@TableName("short_type")
public class ShortTypeEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Long id;
	/**
	 * 类型名称
	 */
	private String typeName;

	/**
	 * 设置：
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * 获取：
	 */
	public Long getId() {
		return id;
	}

	/**
	 * 设置：类型名称
	 */
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	/**
	 * 获取：类型名称
	 */
	public String getTypeName() {
		return typeName;
	}
}
