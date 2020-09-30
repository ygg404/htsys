package io.renren.modules.dop.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 仪器表
 * 
 * @author ygg
 * @date 2020-09-23 10:22:28
 */
@TableName("dop_device")
public class DopDeviceEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 序号ID
	 */
	@TableId
	private Long id;
	/**
	 * 仪器名称
	 */
	private String deviceName;
	/**
	 * 出厂编号
	 */
	private String factoryNum;
	/**
	 * 生产厂家
	 */
	private String factoryName;
	/**
	 * 型号规格
	 */
	private String specNum;
	/**
	 * 标称精度
	 */
	private String accuracy;
	/**
	 * 购置金额
	 */
	private Float price;
	/**
	 * 购置时间
	 */
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM")
	private Date buyTime;
	/**
	 * 设备状况（0-闲置中；1-出借中；2-维修中）
	 */
	private Long devStation;
	/**
	 * 出借情况
	 */
	private String lendStation;
	/**
	 * 上次检修时间
	 */
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM")
	private Date lastCheckTime;
	/**
	 * 有效期
	 */
	private Long indate;
	/**
	 * 仪器证书
	 */
	private String fileName;
	/**
	 * 备注
	 */
	private String remark;
	/**
	 * 创建时间
	 */
	private Date createTime;

	/**
	* 获取：序号ID
	*/
	public Long getId (){return this.id;};
	/**
	 * 设置：序号ID
	 */
	public void setId (Long id){this.id = id;};
	/**
	* 获取：仪器名称
	*/
	public String getDeviceName (){return this.deviceName;};
	/**
	 * 设置：仪器名称
	 */
	public void setDeviceName (String deviceName){this.deviceName = deviceName;};
	/**
	* 获取：出厂编号
	*/
	public String getFactoryNum (){return this.factoryNum;};
	/**
	 * 设置：出厂编号
	 */
	public void setFactoryNum (String factoryNum){this.factoryNum = factoryNum;};
	/**
	* 获取：生产厂家
	*/
	public String getFactoryName (){return this.factoryName;};
	/**
	 * 设置：生产厂家
	 */
	public void setFactoryName (String factoryName){this.factoryName = factoryName;};
	/**
	* 获取：型号规格
	*/
	public String getSpecNum (){return this.specNum;};
	/**
	 * 设置：型号规格
	 */
	public void setSpecNum (String specNum){this.specNum = specNum;};
	/**
	* 获取：标称精度
	*/
	public String getAccuracy (){return this.accuracy;};
	/**
	 * 设置：标称精度
	 */
	public void setAccuracy (String accuracy){this.accuracy = accuracy;};
	/**
	* 获取：购置金额
	*/
	public Float getPrice (){return this.price;};
	/**
	 * 设置：购置金额
	 */
	public void setPrice (Float price){this.price = price;};
	/**
	* 获取：购置时间
	*/
	public Date getBuyTime (){return this.buyTime;};
	/**
	 * 设置：购置时间
	 */
	public void setBuyTime (Date buyTime){this.buyTime = buyTime;};
	/**
	* 获取：设备状况（0-闲置中；1-出借中；2-维修中）
	*/
	public Long getDevStation (){return this.devStation;};
	/**
	 * 设置：设备状况（0-闲置中；1-出借中；2-维修中）
	 */
	public void setDevStation (Long devStation){this.devStation = devStation;};
	/**
	* 获取：出借情况
	*/
	public String getLendStation (){return this.lendStation;};
	/**
	 * 设置：出借情况
	 */
	public void setLendStation (String lendStation){this.lendStation = lendStation;};
	/**
	* 获取：上次检修时间
	*/
	public Date getLastCheckTime (){return this.lastCheckTime;};
	/**
	 * 设置：上次检修时间
	 */
	public void setLastCheckTime (Date lastCheckTime){this.lastCheckTime = lastCheckTime;};
	/**
	* 获取：有效期
	*/
	public Long getIndate (){return this.indate;};
	/**
	 * 设置：有效期
	 */
	public void setIndate (Long indate){this.indate = indate;};
	/**
	* 获取：仪器证书
	*/
	public String getFileName (){return this.fileName;};
	/**
	 * 设置：仪器证书
	 */
	public void setFileName (String fileName){this.fileName = fileName;};
	/**
	* 获取：备注
	*/
	public String getRemark (){return this.remark;};
	/**
	 * 设置：备注
	 */
	public void setRemark (String remark){this.remark = remark;};
	/**
	* 获取：创建时间
	*/
	public Date getCreateTime (){return this.createTime;};
	/**
	 * 设置：创建时间
	 */
	public void setCreateTime (Date createTime){this.createTime = createTime;};
}
