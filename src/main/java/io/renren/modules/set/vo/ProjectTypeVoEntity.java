package io.renren.modules.set.vo;

import java.io.Serializable;

public class ProjectTypeVoEntity implements Serializable {
    /**
     * 项目类型ID
     */
    private Long ptypeId;
    /**
     * 项目类型名称
     */
    private String projectTypeName;
    /**
     * 工作类型ID
     */
    private Long wtypeId;
    /**
     * 工作类型名称
     */
    private String workTypeName;
    /**
     * 单位
     */
    private String unit;
    /**
     * 单位产值
     */
    private Float unitOutput;

    /**
     * 获取：项目类型id
     */
    public Long getPtypeId (){return this.ptypeId;};
    /**
     * 设置：项目类型ID
     */
    public void setPtypeId (Long ptypeId){this.ptypeId = ptypeId;};
    /**
     * 获取：项目类型名称
     */
    public String getProjectTypeName (){return this.projectTypeName;};
    /**
     * 设置：项目类型名称
     */
    public void setProjectTypeName (String projectTypeName){this.projectTypeName = projectTypeName;};
    /**
     * 获取：工作类型Id
     */
    public Long getwtypeId (){return this.wtypeId;};
    /**
     * 设置：工作类Id
     */
    public void setwtypeId (Long wtypeId){this.wtypeId = wtypeId;};
    /**
     * 获取：工作类型名称
     */
    public String getworkTypeName (){return this.workTypeName;};
    /**
     * 设置：工作类型名称
     */
    public void setworkTypeName (String typeName){this.workTypeName = typeName;};
    /**
     * 获取：单位
     */
    public String getUnit (){return this.unit;};
    /**
     * 设置：单位
     */
    public void setUnit (String unit){this.unit = unit;};
    /**
     * 获取：单位产值
     */
    public Float getUnitOutput (){return this.unitOutput;};
    /**
     * 设置：单位产值
     */
    public void setUnitOutput (Float unitOutput){this.unitOutput = unitOutput;};
}
