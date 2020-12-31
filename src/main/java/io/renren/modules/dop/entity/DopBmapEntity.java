package io.renren.modules.dop.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 地图标注表
 *
 * @author ygg
 * @date 2020-12-28 11:46:22
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
	private Double labelLng;
	/**
	 * 标题纬度
	 */
	private Double labelLat;
	/**
	 * 经度（中心坐标）
	 */
	private Double lng;
	/**
	 * 纬度（中心坐标）
	 */
	private Double lat;
	/**
	 * 经纬度（格式： xx,xx；xx,xx ）
	 */
	private String coordinate;
	/**
	 * 面积
	 */
	private Double area;
	/**
	 * 类型（0-项目;1-点；2-线；3-面）
	 */
	private Long type;
	/**
	 * 来源
	 */
	private String origin;
	/**
	 * 测绘人员
	 */
	private String surveyor;
	/**
	 * 测绘时间
	 */
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
	private Date surveyTime;
	/**
	 * 定位方法
	 */
	private String posMethod;
	/**
	 * 线路类型
	 */
	private String lineType;
	/**
	 * 线路长度
	 */
	private Double lineLength;
	/**
	 * 高程基准
	 */
	private String heightDatum;
	/**
	 * 高程等级
	 */
	private String heightLevel;
	/**
	 * 测绘要求
	 */
	private String surveyRequire;
	/**
	 * 参数类型（1-四参表；2-七参表）
	 */
	private Long sfRadio;
	/**
	 * 四参表-平移北(m)
	 */
	private Float fpNorth;
	/**
	 * 四参表-平移东(m)
	 */
	private Float fpEast;
	/**
	 * 四参表-旋转
	 */
	private String fpRotate;
	/**
	 * 四参表-尺寸
	 */
	private Float fpSize;
	/**
	 * 七参数
	 */
	private Double spDxm;
	/**
	 * 七参数
	 */
	private Double spDym;
	/**
	 * 七参数
	 */
	private Double spDzm;
	/**
	 * 七参数
	 */
	private Double spRx;
	/**
	 * 七参数
	 */
	private Double spRy;
	/**
	 * 七参数
	 */
	private Double spRz;
	/**
	 * 七参数
	 */
	private Double spKppm;
	/**
	 * 坐标系统
	 */
	private String corSystem;
	/**
	 * 精确坐标X
	 */
	private Double corX;
	/**
	 * 精确坐标Y
	 */
	private Double corY;
	/**
	 * 精确坐标H
	 */
	private Double corH;
	/**
	 * 控制点类级
	 */
	private String posCate;
	/**
	 * 所在图幅
	 */
	private String sheet;
	/**
	 * 所在地
	 */
	private String location;
	/**
	 * 概略位置
	 */
	private String outline;
	/**
	 * 地类
	 */
	private String cake;
	/**
	 * 土质
	 */
	private String soil;
	/**
	 * 本点交通概况
	 */
	private String transport;
	/**
	 * 交通路线图
	 */
	private String transImg;
	/**
	 * 现场近照
	 */
	private String photoScene;
	/**
	 * 现场远照
	 */
	private String photoFar;
	/**
	 * 单位
	 */
	private String unit;
	/**
	 * 选点员
	 */
	private String pointPerson;
	/**
	 * 埋石时间
	 */
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
	private Date stoneTime;
	/**
	 * 是否联测Ⅳ等水准（1-是; 0-否）
	 */
	private Long unitMeasure;
	/**
	 * 编制人员
	 */
	private String authMan;
	/**
	 * 校核人员
	 */
	private String checkMan;
	/**
	 * 备注
	 */
	private String remark;
	/**
	 * 上传的word文件（点之记）
	 */
	private String wordFile;
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
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	private Date createTime;
	/**
	 * 修改时间
	 */
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	private Date modifyTime;
	/**
	 * 父类Id
	 */
	private Long parentId;

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
	public Double getLabelLng (){return this.labelLng;};
	/**
	 * 设置：标题经度
	 */
	public void setLabelLng (Double labelLng){this.labelLng = labelLng;};
	/**
	 * 获取：标题纬度
	 */
	public Double getLabelLat (){return this.labelLat;};
	/**
	 * 设置：标题纬度
	 */
	public void setLabelLat (Double labelLat){this.labelLat = labelLat;};
	/**
	 * 获取：经度（中心坐标）
	 */
	public Double getLng (){return this.lng;};
	/**
	 * 设置：经度（中心坐标）
	 */
	public void setLng (Double lng){this.lng = lng;};
	/**
	 * 获取：纬度（中心坐标）
	 */
	public Double getLat (){return this.lat;};
	/**
	 * 设置：纬度（中心坐标）
	 */
	public void setLat (Double lat){this.lat = lat;};
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
	public Double getArea (){return this.area;};
	/**
	 * 设置：面积
	 */
	public void setArea (Double area){this.area = area;};
	/**
	 * 获取：类型（0-项目;1-点；2-线；3-面）
	 */
	public Long getType (){return this.type;};
	/**
	 * 设置：类型（0-项目;1-点；2-线；3-面）
	 */
	public void setType (Long type){this.type = type;};
	/**
	 * 获取：来源
	 */
	public String getOrigin (){return this.origin;};
	/**
	 * 设置：来源
	 */
	public void setOrigin (String origin){this.origin = origin;};
	/**
	 * 获取：测绘人员
	 */
	public String getSurveyor (){return this.surveyor;};
	/**
	 * 设置：测绘人员
	 */
	public void setSurveyor (String surveyor){this.surveyor = surveyor;};
	/**
	 * 获取：测绘时间
	 */
	public Date getSurveyTime (){return this.surveyTime;};
	/**
	 * 设置：测绘时间
	 */
	public void setSurveyTime (Date surveyTime){this.surveyTime = surveyTime;};
	/**
	 * 获取：定位方法
	 */
	public String getPosMethod (){return this.posMethod;};
	/**
	 * 设置：定位方法
	 */
	public void setPosMethod (String posMethod){this.posMethod = posMethod;};
	/**
	 * 获取：线路类型
	 */
	public String getLineType (){return this.lineType;};
	/**
	 * 设置：线路类型
	 */
	public void setLineType (String lineType){this.lineType = lineType;};
	/**
	 * 获取：线路长度
	 */
	public Double getLineLength (){return this.lineLength;};
	/**
	 * 设置：线路长度
	 */
	public void setLineLength (Double lineLength){this.lineLength = lineLength;};
	/**
	 * 获取：高程基准
	 */
	public String getHeightDatum (){return this.heightDatum;};
	/**
	 * 设置：高程基准
	 */
	public void setHeightDatum (String heightDatum){this.heightDatum = heightDatum;};
	/**
	 * 获取：高程等级
	 */
	public String getHeightLevel (){return this.heightLevel;};
	/**
	 * 设置：高程等级
	 */
	public void setHeightLevel (String heightLevel){this.heightLevel = heightLevel;};
	/**
	 * 获取：测绘要求
	 */
	public String getSurveyRequire (){return this.surveyRequire;};
	/**
	 * 设置：测绘要求
	 */
	public void setSurveyRequire (String surveyRequire){this.surveyRequire = surveyRequire;};
	/**
	 * 获取：参数类型（1-四参表；2-七参表）
	 */
	public Long getSfRadio (){return this.sfRadio;};
	/**
	 * 设置：参数类型（1-四参表；2-七参表）
	 */
	public void setSfRadio (Long sfRadio){this.sfRadio = sfRadio;};
	/**
	 * 获取：四参表-平移北(m)
	 */
	public Float getFpNorth (){return this.fpNorth;};
	/**
	 * 设置：四参表-平移北(m)
	 */
	public void setFpNorth (Float fpNorth){this.fpNorth = fpNorth;};
	/**
	 * 获取：四参表-平移东(m)
	 */
	public Float getFpEast (){return this.fpEast;};
	/**
	 * 设置：四参表-平移东(m)
	 */
	public void setFpEast (Float fpEast){this.fpEast = fpEast;};
	/**
	 * 获取：四参表-旋转
	 */
	public String getFpRotate (){return this.fpRotate;};
	/**
	 * 设置：四参表-旋转
	 */
	public void setFpRotate (String fpRotate){this.fpRotate = fpRotate;};
	/**
	 * 获取：四参表-尺寸
	 */
	public Float getFpSize (){return this.fpSize;};
	/**
	 * 设置：四参表-尺寸
	 */
	public void setFpSize (Float fpSize){this.fpSize = fpSize;};
	/**
	 * 获取：七参数Dx
	 */
	public Double getSpDxm (){return this.spDxm;};
	/**
	 * 设置：七参数Dx
	 */
	public void setSpDxm (Double spDxm){this.spDxm = spDxm;};
	/**
	 * 获取：七参数Dy
	 */
	public Double getSpDym (){return this.spDym;};
	/**
	 * 设置：七参数Dy
	 */
	public void setSpDym (Double spDym){this.spDym = spDym;};
	/**
	 * 获取：七参数Dz
	 */
	public Double getSpDzm (){return this.spDzm;};
	/**
	 * 设置：七参数Dz
	 */
	public void setSpDzm (Double spDzm){this.spDzm = spDzm;};
	/**
	 * 获取：七参数Rx
	 */
	public Double getSpRx (){return this.spRx;};
	/**
	 * 设置：七参数Rx
	 */
	public void setSpRx (Double spRx){this.spRx = spRx;};
	/**
	 * 获取：七参数Ry
	 */
	public Double getSpRy (){return this.spRy;};
	/**
	 * 设置：七参数Ry
	 */
	public void setSpRy (Double spRy){this.spRy = spRy;};
	/**
	 * 获取：七参数Rz
	 */
	public Double getSpRz (){return this.spRz;};
	/**
	 * 设置：七参数Rz
	 */
	public void setSpRz (Double spRz){this.spRz = spRz;};
	/**
	 * 获取：七参数Kppm
	 */
	public Double getSpKppm (){return this.spKppm;};
	/**
	 * 设置：七参数Kppm
	 */
	public void setSpKppm (Double spKppm){this.spKppm = spKppm;};
	/**
	 * 获取：坐标系统
	 */
	public String getCorSystem (){return this.corSystem;};
	/**
	 * 设置：坐标系统
	 */
	public void setCorSystem (String corSystem){this.corSystem = corSystem;};
	/**
	 * 获取：精确坐标X
	 */
	public Double getCorX (){return this.corX;};
	/**
	 * 设置：精确坐标X
	 */
	public void setCorX (Double corX){this.corX = corX;};
	/**
	 * 获取：精确坐标Y
	 */
	public Double getCorY (){return this.corY;};
	/**
	 * 设置：精确坐标Y
	 */
	public void setCorY (Double corY){this.corY = corY;};
	/**
	 * 获取：精确坐标H
	 */
	public Double getCorH (){return this.corH;};
	/**
	 * 设置：精确坐标H
	 */
	public void setCorH (Double corH){this.corH = corH;};
	/**
	 * 获取：控制点类级
	 */
	public String getPosCate (){return this.posCate;};
	/**
	 * 设置：控制点类级
	 */
	public void setPosCate (String posCate){this.posCate = posCate;};
	/**
	 * 获取：所在图幅
	 */
	public String getSheet (){return this.sheet;};
	/**
	 * 设置：所在图幅
	 */
	public void setSheet (String sheet){this.sheet = sheet;};
	/**
	 * 获取：所在地
	 */
	public String getLocation (){return this.location;};
	/**
	 * 设置：所在地
	 */
	public void setLocation (String location){this.location = location;};
	/**
	 * 获取：概略位置
	 */
	public String getOutline (){return this.outline;};
	/**
	 * 设置：概略位置
	 */
	public void setOutline (String outline){this.outline = outline;};
	/**
	 * 获取：地类
	 */
	public String getCake (){return this.cake;};
	/**
	 * 设置：地类
	 */
	public void setCake (String cake){this.cake = cake;};
	/**
	 * 获取：土质
	 */
	public String getSoil (){return this.soil;};
	/**
	 * 设置：土质
	 */
	public void setSoil (String soil){this.soil = soil;};
	/**
	 * 获取：本点交通概况
	 */
	public String getTransport (){return this.transport;};
	/**
	 * 设置：本点交通概况
	 */
	public void setTransport (String transport){this.transport = transport;};
	/**
	 * 获取：交通路线图
	 */
	public String getTransImg (){return this.transImg;};
	/**
	 * 设置：交通路线图
	 */
	public void setTransImg (String transImg){this.transImg = transImg;};
	/**
	 * 获取：现场近照
	 */
	public String getPhotoScene (){return this.photoScene;};
	/**
	 * 设置：现场近照
	 */
	public void setPhotoScene (String photoScene){this.photoScene = photoScene;};
	/**
	 * 获取：现场远照
	 */
	public String getPhotoFar (){return this.photoFar;};
	/**
	 * 设置：现场远照
	 */
	public void setPhotoFar (String photoFar){this.photoFar = photoFar;};
	/**
	 * 获取：单位
	 */
	public String getUnit (){return this.unit;};
	/**
	 * 设置：单位
	 */
	public void setUnit (String unit){this.unit = unit;};
	/**
	 * 获取：选点员
	 */
	public String getPointPerson (){return this.pointPerson;};
	/**
	 * 设置：选点员
	 */
	public void setPointPerson (String pointPerson){this.pointPerson = pointPerson;};
	/**
	 * 获取：埋石时间
	 */
	public Date getStoneTime (){return this.stoneTime;};
	/**
	 * 设置：埋石时间
	 */
	public void setStoneTime (Date stoneTime){this.stoneTime = stoneTime;};
	/**
	 * 获取：是否联测Ⅳ等水准（1-是; 0-否）
	 */
	public Long getUnitMeasure (){return this.unitMeasure;};
	/**
	 * 设置：是否联测Ⅳ等水准（1-是; 0-否）
	 */
	public void setUnitMeasure (Long unitMeasure){this.unitMeasure = unitMeasure;};
	/**
	 * 获取：编制人员
	 */
	public String getAuthMan (){return this.authMan;};
	/**
	 * 设置：编制人员
	 */
	public void setAuthMan (String authMan){this.authMan = authMan;};
	/**
	 * 获取：校核人员
	 */
	public String getCheckMan (){return this.checkMan;};
	/**
	 * 设置：校核人员
	 */
	public void setCheckMan (String checkMan){this.checkMan = checkMan;};
	/**
	 * 获取：备注
	 */
	public String getRemark (){return this.remark;};
	/**
	 * 设置：备注
	 */
	public void setRemark (String remark){this.remark = remark;};
	/**
	 * 获取：上传的word文件（点之记）
	 */
	public String getWordFile (){return this.wordFile;};
	/**
	 * 设置：上传的word文件（点之记）
	 */
	public void setWordFile (String wordFile){this.wordFile = wordFile;};
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
	/**
	 * 获取：父类Id
	 */
	public Long getParentId (){return this.parentId;};
	/**
	 * 设置：父类Id
	 */
	public void setParentId (Long parentId){this.parentId = parentId;};
}
