package io.renren.modules.perf.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 效能考核评分
 * 
 * @author ygg
 * @date 2020-05-16 15:45:46
 */
@TableName("perf_assess")
public class PerfAssessEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 自增ID
	 */
	@TableId
	private Long id;
	/**
	 * 考核人Id
	 */
	private Long userId;
	/**
	 * 考核人姓名
	 */
	private String userName;
	/**
	 * 被考核人ID
	 */
	private Long checkUserId;
	/**
	 * 被考核人姓名
	 */
	private String checkUserName;
	/**
	 * 考核项ID
	 */
	private Long kbiId;
	/**
	 * 考核评分
	 */
	private Long kbiScore;
	/**
	 * 考核项
	 */
	private String kbiName;
	/**
	 * 考核占比
	 */
	private Long kbiRatio;
	/**
	 * 年度
	 */
	private Long year;
	/**
	 * 月份
	 */
	private Long month;

	/**
	* 获取：自增ID
	*/
	public Long getId (){return this.id;};
	/**
	 * 设置：自增ID
	 */
	public void setId (Long id){this.id = id;};
	/**
	* 获取：考核人Id
	*/
	public Long getUserId (){return this.userId;};
	/**
	 * 设置：考核人Id
	 */
	public void setUserId (Long userId){this.userId = userId;};
	/**
	* 获取：考核人姓名
	*/
	public String getUserName (){return this.userName;};
	/**
	 * 设置：考核人姓名
	 */
	public void setUserName (String userName){this.userName = userName;};
	/**
	* 获取：被考核人ID
	*/
	public Long getCheckUserId (){return this.checkUserId;};
	/**
	 * 设置：被考核人ID
	 */
	public void setCheckUserId (Long checkUserId){this.checkUserId = checkUserId;};
	/**
	* 获取：被考核人姓名
	*/
	public String getCheckUserName (){return this.checkUserName;};
	/**
	 * 设置：被考核人姓名
	 */
	public void setCheckUserName (String checkUserName){this.checkUserName = checkUserName;};
	/**
	* 获取：考核项ID
	*/
	public Long getKbiId (){return this.kbiId;};
	/**
	 * 设置：考核项ID
	 */
	public void setKbiId (Long kbiId){this.kbiId = kbiId;};
	/**
	* 获取：考核评分
	*/
	public Long getKbiScore (){return this.kbiScore;};
	/**
	 * 设置：考核评分
	 */
	public void setKbiScore (Long kbiScore){this.kbiScore = kbiScore;};
	/**
	* 获取：考核项
	*/
	public String getKbiName (){return this.kbiName;};
	/**
	 * 设置：考核项
	 */
	public void setKbiName (String kbiName){this.kbiName = kbiName;};
	/**
	 * 获取：考核占比
	 */
	public Long getKbiRatio (){return this.kbiRatio;};
	/**
	 * 设置：考核占比
	 */
	public void setKbiRatio (Long kbiRatio){this.kbiRatio = kbiRatio;};
	/**
	* 获取：年度
	*/
	public Long getYear (){return this.year;};
	/**
	 * 设置：年度
	 */
	public void setYear (Long year){this.year = year;};
	/**
	* 获取：月份
	*/
	public Long getMonth (){return this.month;};
	/**
	 * 设置：月份
	 */
	public void setMonth (Long month){this.month = month;};
}
