package io.renren.modules.project.vo;

import com.baomidou.mybatisplus.annotations.TableId;

import java.io.Serializable;
import java.util.List;

public class CheckOutputVoEntity implements Serializable {
    /**
     * ID
     */
    private Long id;
    /**
     * 是否勾选
     */
    private Boolean checked;
    /**
     * 工作类型ID
     */
    private Long typeId;
    /**
     * 类型名称
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
     * 项目编号
     */
    private String projectNo;
    /**
     * 项目占比
     */
    private Float projectRatio;
    /**
     * 工作量
     */
    private Float workLoad;
    /**
     * 工作组ID
     */
    private Long groupId;
    /**
     * 作业类型列表ID
     */
    private List<Long> projectTypeIdList;

    /**
     * 获取：ID
     */
    public Long getId (){return this.id;};
    /**
     * 设置：ID
     */
    public void setId (Long id){this.id = id;};
    /**
     * 获取：
     */
    public Boolean getChecked (){return this.checked;};
    /**
     * 设置：ID
     */
    public void setChecked (Boolean checked){this.checked = checked;};
    /**
     * 获取：工作类型ID
     */
    public Long gettypeId (){return this.typeId;};
    /**
     * 设置：工作类型ID
     */
    public void settypeId (Long typeId){this.typeId = typeId;};
    /**
     * 获取：项目编号
     */
    public String getprojectNo (){return this.projectNo;};
    /**
     * 设置：项目编号
     */
    public void setprojectNo (String projectNo){this.projectNo = projectNo;};
    /**
     * 获取：项目占比
     */
    public Float getprojectRatio (){return this.projectRatio;};
    /**
     * 设置：项目占比
     */
    public void setprojectRatio (Float projectRatio){this.projectRatio = projectRatio;};
    /**
     * 获取：工作量
     */
    public Float getworkLoad (){return this.workLoad;};
    /**
     * 设置：工作量
     */
    public void setworkLoad (Float workLoad){this.workLoad = workLoad;};
    /**
     * 获取：工作组ID
     */
    public Long getgroupId (){return this.groupId;};
    /**
     * 设置：工作组ID
     */
    public void setgroupId (Long groupId){this.groupId = groupId;};

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Float getUnitOutput() {
        return unitOutput;
    }

    public void setUnitOutput(Float unitOutput) {
        this.unitOutput = unitOutput;
    }

    /**
     * 获取：作业类型列表ID
     */
    public List<Long> getProjectTypeIdList() {return  this.projectTypeIdList;}
    /**
     * 设置：作业类型列表ID
     */
    public void setProjectTypeIdList(List<Long> projectTypeIdList) {this.projectTypeIdList = projectTypeIdList;}

}
