package io.renren.modules.set.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 学历分对照表
 * 
 * @author ygg
 * @date 2020-06-11 09:29:42
 */
@TableName("set_score_ed")
public class SetScoreEdEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 自增id
	 */
	@TableId
	private Long id;
	/**
	 * 对应项名称
	 */
	private String scoreName;
	/**
	 * 类别（1-学制；2-专业；3-学历）
	 */
	private Long cateid;
	/**
	 * 分数
	 */
	private Float score;
	/**
	 * 顺序号
	 */
	private Long orderNum;

	/**
	* 获取：自增id
	*/
	public Long getId (){return this.id;};
	/**
	 * 设置：自增id
	 */
	public void setId (Long id){this.id = id;};
	/**
	* 获取：对应项名称
	*/
	public String getScoreName (){return this.scoreName;};
	/**
	 * 设置：对应项名称
	 */
	public void setScoreName (String scoreName){this.scoreName = scoreName;};
	/**
	* 获取：类别（1-学制；2-专业；3-学历）
	*/
	public Long getCateid (){return this.cateid;};
	/**
	 * 设置：类别（1-学制；2-专业；3-学历）
	 */
	public void setCateid (Long cateid){this.cateid = cateid;};
	/**
	* 获取：分数
	*/
	public Float getScore (){return this.score;};
	/**
	 * 设置：分数
	 */
	public void setScore (Float score){this.score = score;};
	/**
	* 获取：顺序号
	*/
	public Long getOrderNum (){return this.orderNum;};
	/**
	 * 设置：顺序号
	 */
	public void setOrderNum (Long orderNum){this.orderNum = orderNum;};
}
