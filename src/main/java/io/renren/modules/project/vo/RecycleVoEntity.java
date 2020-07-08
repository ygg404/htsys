package io.renren.modules.project.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * 回收站实体类
 */
public class RecycleVoEntity implements Serializable {
    /**
     * 项目ID
     */
    private Integer id;
    /**
     * 项目编号
     */
    private String projectNo;
    /**
     * 合同编号
     */
    private String contractNo;
    /**
     * 项目名称
     */
    private String projectName;
    /**
     * 委托单位
     */
    private String projectAuthorize;
    /**
     * 业务负责人
     */
    private String projectBusiness;
    /**
     * 生产负责人
     */
    private String projectProduce;
    /**
     * 项目负责人
     */
    private String projectCharge;
    /**
     * 项目阶段
     */
    private Integer projectStage;
    /**
     * 项目阶段名称
     */
    private String projectStageName;

    /**
     * 项目开始时间
     */
    private Date projectStartDateTime;

    /**
     * 获取：
     */
    public Integer getid (){return this.id;};
    /**
     * 设置：
     */
    public void setid (Integer id){this.id = id;};
    /**
     * 获取：项目编号
     */
    public String getprojectNo (){return this.projectNo;};
    /**
     * 设置：项目编号
     */
    public void setprojectNo (String projectNo){this.projectNo = projectNo;};
    /**
     * 获取：合同编号
     */
    public String getcontractNo (){return this.contractNo;};
    /**
     * 设置：合同编号
     */
    public void setcontractNo (String contractNo){this.contractNo = contractNo;};
    /**
     * 获取：项目名称
     */
    public String getprojectName (){return this.projectName;};
    /**
     * 设置：项目名称
     */
    public void setprojectName (String projectName){this.projectName = projectName;};
    /**
     * 获取：项目委托单位
     */
    public String getprojectAuthorize (){return this.projectAuthorize;};
    /**
     * 设置：项目委托单位
     */
    public void setprojectAuthorize (String projectAuthorize){this.projectAuthorize = projectAuthorize;};
    /**
     * 获取：业务负责人
     */
    public String getprojectBusiness (){return this.projectBusiness;};
    /**
     * 设置：业务负责人
     */
    public void setprojectBusiness (String projectName){this.projectBusiness = projectBusiness;};
    /**
     * 获取：生产负责人
     */
    public String getprojectProduce (){return this.projectProduce;};
    /**
     * 设置：生产负责人
     */
    public void setprojectProduce (String projectProduce){this.projectProduce = projectProduce;};
    /**
     * 获取：项目负责人
     */
    public String getprojectCharge() { return  this.projectCharge;};
    /**
     * 设置：项目负责人
     */
    public void setprojectCharge(String projectCharge) { this.projectCharge = projectCharge;};

    /**
     * 获取：项目阶段
     */
    public Integer getprojectStage (){return this.projectStage;}
    /**
     * 设置：项目阶段
     */
    public void setprojectStage (Integer projectStage){this.projectStage = projectStage;};
    /**
     * 获取：项目阶段名称
     */
    public String getprojectStageName (){return this.projectStageName;}
    /**
     * 设置：项目阶段名称
     */
    public void setprojectStageName(String projectStageName) {this.projectStageName = projectStageName;}
    /**
     * 获取：项目开始时间
     */
    public Date getprojectStartDateTime (){return  this.projectStartDateTime;};
    /**
     * 设置：项目开始时间
     */
    public void setprojectStartDateTime (Date projectStartDateTime){this.projectStartDateTime = projectStartDateTime;};


}
