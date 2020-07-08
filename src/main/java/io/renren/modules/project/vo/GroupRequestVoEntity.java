package io.renren.modules.project.vo;

import java.io.Serializable;
import java.util.List;

public class GroupRequestVoEntity implements Serializable {

    /**
     *  队长id
     */
    private Long headId;
    /**
     * 项目编号
     */
    private String projectNo;
    /**
     * 各组产值明细列表
     */
    private List<ProjectGroupVoEntity> pgroupList;
    /**
     * 实际总产值
     */
    private float projectActuallyOutput;

    /**
     * 获取： 队长id
     */
    public Long getHeadId(){return this.headId;}
    /**
     * 设置： 队长id
     */
    public void setHeadId(Long headId){this.headId = headId;}
    /**
     * 获取：项目编号
     */
    public String getProjectNo(){return this.projectNo;}
    /**
     * 设置： 项目编号
     */
    public void setProjectNo(String projectNo){this.projectNo = projectNo;}
    /**
     * 获取：各组产值明细列表
     */
    public List<ProjectGroupVoEntity> getPgroupList() {return  this.pgroupList;}
    /**
     *设置：  各组产值明细列表
     */
    public void setPgroupList(List<ProjectGroupVoEntity> pgroupList) {this.pgroupList = pgroupList;}
    /**
     * 获取：实际总产值
     */
    public float getProjectActuallyOutput() {return this.projectActuallyOutput;}
    /**
     * 设置： 实际总产值
     */
    public void setProjectActuallyOutput(float projectActuallyOutput){this.projectActuallyOutput = projectActuallyOutput;}
}
