package io.renren.modules.sys.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户明细表
 * 
 * @author ygg
 * @date 2019-11-30 11:04:55
 */
@TableName("sys_user_detail")
public class SysUserDetailEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 用户id
	 */
	@TableId
	private Long userId;
	/**
	 * 生日
	 */
	private Date birthday;
	/**
	 * 学历: 0-无；1-小学；2-初中;3-高中;4-中专;5-职高;6-大专;7-本科;8-硕士;9-博士;10-博士后
	 */
	private Long education;
	/**
	 * 毕业院校
	 */
	private String graduateSchool;
	/**
	 * 职称( 0-无；1-技术员；2-助理工程师；3-中级工程师；4-高级工程师；5-正高级工程师)
	 */
	private Long professionalTitle;
	/**
	 * 头像路径
	 */
	private String headUrl;
	/**
	 * 联系电话
	 */
	private String mobile;

	/**
	* 获取：用户id
	*/
	public Long getuserId (){return this.userId;};
	/**
	 * 设置：用户id
	 */
	public void setuserId (Long userId){this.userId = userId;};
	/**
	* 获取：生日
	*/
	public Date getbirthday (){return this.birthday;};
	/**
	 * 设置：生日
	 */
	public void setbirthday (Date birthday){this.birthday = birthday;};
	/**
	* 获取：学历: 0-无；1-小学；2-初中;3-高中;4-中专;5-职高;6-大专;7-本科;8-硕士;9-博士;10-博士后
	*/
	public Long geteducation (){return this.education;};
	/**
	 * 设置：学历: 0-无；1-小学；2-初中;3-高中;4-中专;5-职高;6-大专;7-本科;8-硕士;9-博士;10-博士后
	 */
	public void seteducation (Long education){this.education = education;};
	/**
	* 获取：毕业院校
	*/
	public String getgraduateSchool (){return this.graduateSchool;};
	/**
	 * 设置：毕业院校
	 */
	public void setgraduateSchool (String graduateSchool){this.graduateSchool = graduateSchool;};
	/**
	* 获取：职称( 0-无；1-技术员；2-助理工程师；3-中级工程师；4-高级工程师；5-正高级工程师)
	*/
	public Long getprofessionalTitle (){return this.professionalTitle;};
	/**
	 * 设置：职称( 0-无；1-技术员；2-助理工程师；3-中级工程师；4-高级工程师；5-正高级工程师)
	 */
	public void setprofessionalTitle (Long professionalTitle){this.professionalTitle = professionalTitle;};
	/**
	* 获取：头像路径
	*/
	public String getheadUrl (){return this.headUrl;};
	/**
	 * 设置：头像路径
	 */
	public void setheadUrl (String headUrl){this.headUrl = headUrl;};
	/**
	* 获取：联系电话
	*/
	public String getmobile (){return this.mobile;};
	/**
	 * 设置：联系电话
	 */
	public void setmobile (String mobile){this.mobile = mobile;};
}
