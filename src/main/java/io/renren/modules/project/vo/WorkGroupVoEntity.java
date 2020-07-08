package io.renren.modules.project.vo;

import java.io.Serializable;
import java.util.List;

/**
 * 各组的产值和项目进度
 */
public class WorkGroupVoEntity implements Serializable {

    /**
     * id
     */
    private Long groupId;
    /**
     * 组名
     */
    private String groupName;
    /**
     * 已安排产值
     */
    private Float isSetOutput;
    /**
     * 未安排产值
     */
    private Float isNotOutput;
    /**
     * 未完成项目个数
     */
    private Integer undoneNum;
    /**
     * 项目数
     */
    private Integer projectNum;
    /**
     * 各组总产值
     */
    private Float output;
    /**
     * 项目列表
     */
    private List<ProjectGroupVoEntity> projectList;


    /**
     * 获取：组id
     */
    public Long getgroupId (){return this.groupId;};
    /**
     * 设置：组id
     */
    public void setgroupId (Long groupId){this.groupId = groupId;};
    /**
     * 获取：组名
     */
    public String getgroupName (){return this.groupName;};
    /**
     * 设置：组名
     */
    public void setgroupName (String groupName){this.groupName = groupName;};

    /**
     * 获取：已安排产值
     */
    public Float getisSetOutput (){return this.isSetOutput;};
    /**
     * 设置：已安排产值
     */
    public void setisSetOutput (Float isSetOutput){this.isSetOutput = isSetOutput;};
    /**
     * 获取：未完成产值
     */
    public Float getIsNotOutput (){return this.isNotOutput;};
    /**
     * 设置：未完成产值
     */
    public void setIsNotOutput (Float isNotOutput){this.isNotOutput = isNotOutput;};
    /**
     * 获取：未完成产值
     */
    public Float getOutput (){return this.output;};
    /**
     * 设置：未完成产值
     */
    public void setOutput (Float output){this.output = output;};
    /**
     * 获取：未完成项目个数
     */
    public Integer getundoneNum (){return this.undoneNum;};
    /**
     * 设置：未完成项目个数
     */
    public void setundoneNum (Integer undoneNum){this.undoneNum = undoneNum;};
    /**
     * 获取：项目个数
     */
    public Integer getprojectNum (){return this.projectNum;};
    /**
     * 设置：项目个数
     */
    public void setprojectNum (Integer projectNum){this.projectNum = projectNum;};
    /**
     *获取： 项目列表
     */
    public List<ProjectGroupVoEntity> getProjectList() {return this.projectList;}
    /**
     * 设置： 项目列表
     */
    public void setProjectList(List<ProjectGroupVoEntity> projectList) {this.projectList = projectList;}
}
