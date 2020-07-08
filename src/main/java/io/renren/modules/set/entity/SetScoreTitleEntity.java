package io.renren.modules.set.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 职称基准分表
 *
 * @author ygg
 * @date 2020-06-28 17:29:27
 */
@TableName("set_score_title")
public class SetScoreTitleEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 自增ID
	 */
	@TableId
	private Long id;
	/**
	 * 职称名称
	 */
	private String jobTitle;
	/**
	 * 分数
	 */
	private Float score;
	/**
	 * 类别（1-职称等级； 2-专业职称系数）
	 */
	private Long cateid;
	/**
	 * 顺序号
	 */
	private Long orderNum;

	/**
	 * 获取：自增ID
	 */
	public Long getId (){return this.id;};
	/**
	 * 设置：自增ID
	 */
	public void setId (Long id){this.id = id;};
	/**
	 * 获取：职称名称
	 */
	public String getJobTitle (){return this.jobTitle;};
	/**
	 * 设置：职称名称
	 */
	public void setJobTitle (String jobTitle){this.jobTitle = jobTitle;};
	/**
	 * 获取：分数
	 */
	public Float getScore (){return this.score;};
	/**
	 * 设置：分数
	 */
	public void setScore (Float score){this.score = score;};
	/**
	 * 获取：类别（1-职称等级； 2-专业职称系数）
	 */
	public Long getCateid (){return this.cateid;};
	/**
	 * 设置：类别（1-职称等级； 2-专业职称系数）
	 */
	public void setCateid (Long cateid){this.cateid = cateid;};
	/**
	 * 获取：顺序号
	 */
	public Long getOrderNum (){return this.orderNum;};
	/**
	 * 设置：顺序号
	 */
	public void setOrderNum (Long orderNum){this.orderNum = orderNum;};
}
