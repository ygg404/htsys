package io.renren.modules.dop.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 地图标注表
 * 
 * @author ygg
 * @date 2020-09-24 15:08:45
 */
@TableName("dop_bmap")
public class DopBmapEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 自增Id
	 */
	@TableId
	private Long id;
	/**
	 * 标题
	 */
	private String label;
	/**
	 * 标题经度
	 */
	private Float labelLng;
	/**
	 * 标题纬度
	 */
	private Float labelLat;
	/**
	 * 经度（中心坐标）
	 */
	private Float lng;
	/**
	 * 纬度（中心坐标）
	 */
	private Float lat;
	/**
	 * 经纬度（格式： xx,xx；xx,xx ）
	 */
	private String coordinate;
	/**
	 * 面积
	 */
	private Float area;
	/**
	 * 类型（1-点；2-线；3-面）
	 */
	private String type;
	/**
	 * 备注
	 */
	private String remark;
	/**
	 * 创建用户ID
	 */
	private Long createUserId;
	/**
	 * 创建用户名
	 */
	private String createUserName;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 修改时间
	 */
	private Date modifyTime;

	/**
	* 获取：自增Id
	*/
	public Long getId (){return this.id;};
	/**
	 * 设置：自增Id
	 */
	public void setId (Long id){this.id = id;};
	/**
	* 获取：标题
	*/
	public String getLabel (){return this.label;};
	/**
	 * 设置：标题
	 */
	public void setLabel (String label){this.label = label;};
	/**
	* 获取：标题经度
	*/
	public Float getLabelLng (){return this.labelLng;};
	/**
	 * 设置：标题经度
	 */
	public void setLabelLng (Float labelLng){this.labelLng = labelLng;};
	/**
	* 获取：标题纬度
	*/
	public Float getLabelLat (){return this.labelLat;};
	/**
	 * 设置：标题纬度
	 */
	public void setLabelLat (Float labelLat){this.labelLat = labelLat;};
	/**
	* 获取：经度（中心坐标）
	*/
	public Float getLng (){return this.lng;};
	/**
	 * 设置：经度（中心坐标）
	 */
	public void setLng (Float lng){this.lng = lng;};
	/**
	* 获取：纬度（中心坐标）
	*/
	public Float getLat (){return this.lat;};
	/**
	 * 设置：纬度（中心坐标）
	 */
	public void setLat (Float lat){this.lat = lat;};
	/**
	* 获取：经纬度（格式： xx,xx；xx,xx ）
	*/
	public String getCoordinate (){return this.coordinate;};
	/**
	 * 设置：经纬度（格式： xx,xx；xx,xx ）
	 */
	public void setCoordinate (String coordinate){this.coordinate = coordinate;};
	/**
	* 获取：面积
	*/
	public Float getArea (){return this.area;};
	/**
	 * 设置：面积
	 */
	public void setArea (Float area){this.area = area;};
	/**
	* 获取：类型（1-点；2-线；3-面）
	*/
	public String getType (){return this.type;};
	/**
	 * 设置：类型（1-点；2-线；3-面）
	 */
	public void setType (String type){this.type = type;};
	/**
	* 获取：备注
	*/
	public String getRemark (){return this.remark;};
	/**
	 * 设置：备注
	 */
	public void setRemark (String remark){this.remark = remark;};
	/**
	* 获取：创建用户ID
	*/
	public Long getCreateUserId (){return this.createUserId;};
	/**
	 * 设置：创建用户ID
	 */
	public void setCreateUserId (Long createUserId){this.createUserId = createUserId;};
	/**
	* 获取：创建用户名
	*/
	public String getCreateUserName (){return this.createUserName;};
	/**
	 * 设置：创建用户名
	 */
	public void setCreateUserName (String createUserName){this.createUserName = createUserName;};
	/**
	* 获取：创建时间
	*/
	public Date getCreateTime (){return this.createTime;};
	/**
	 * 设置：创建时间
	 */
	public void setCreateTime (Date createTime){this.createTime = createTime;};
	/**
	* 获取：修改时间
	*/
	public Date getModifyTime (){return this.modifyTime;};
	/**
	 * 设置：修改时间
	 */
	public void setModifyTime (Date modifyTime){this.modifyTime = modifyTime;};
}
