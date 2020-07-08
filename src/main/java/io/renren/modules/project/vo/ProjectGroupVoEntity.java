package io.renren.modules.project.vo;


import com.fasterxml.jackson.annotation.JsonFormat;
import io.renren.modules.project.entity.CheckOutputEntity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 项目分组信息
 */
public class ProjectGroupVoEntity implements Serializable {
    /**
     * ID
     */
    private Integer id;
    /**
     * 是否被选中
     */
    private boolean checked;
    /**
     * 项目编号
     */
    private String projectNo;
    /**
     * 项目名称
     */
    private String projectName;
    /**
     * 组id
     */
    private Long groupId;
    /**
     * 组名
     */
    private String groupName;
    /**
     * 项目进度
     */
    private Long scheduleRate;
    /**
     * 队长名称
     */
    private String headMan;
    /**
     * 队长id
     */
    private Long headId;
    /**
     * 产值占比
     */
    private Float outputRate;
    /**
     * 最短工期
     */
    private Float shortDateTime;
    /**
     * 最迟工期
     */
    private Float lastDateTime;
    /**
     * 项目预算产值
     */
    private Float projectOutput;
    /**
     * 项目实际产值
     */
    private Float projectActuallyOutput;
    /**
     * 项目启动时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
    private Date projectStartDateTime;
    /**
     * 实际产值核算
     */
    private List<CheckOutputVoEntity> checkOutputVoList;

    /**
     * 获取：ID
     */
    public Integer getid (){return this.id;};
    /**
     * 设置：ID
     */
    public void setid (Integer id){this.id = id;};
    /**
     *获取：是否被选中
     */
    public boolean getchecked() {
        return checked;
    }
    /**
     *设置： 是否被选中
     */
    public void setchecked( boolean checked) {
        this.checked = checked;
    }
    /**
     * 获取：项目编号
     */
    public String getprojectNo (){return this.projectNo;};
    /**
     * 设置：项目编号
     */
    public void setprojectNo (String projectNo){this.projectNo = projectNo;};
    /**
     * 获取：项目名称
     */
    public String getprojectName (){return this.projectName;};
    /**
     * 设置：项目名称
     */
    public void setprojectName (String projectName){this.projectName = projectName;};
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
     * 获取：项目进度
     */
    public Long getscheduleRate() {return  this.scheduleRate;};
    /**
     * 设置：项目进度
     */
    public void setscheduleRate(Long scheduleRate) {this.scheduleRate = scheduleRate;};
    /**
     * 获取：项目启动时间
     */
    public Date getprojectStartDateTime() { return this.projectStartDateTime;}
    /**
     * 设置：项目启动时间
     */
    public void  setprojectStartDateTime( Date projectStartDateTime) { this.projectStartDateTime = projectStartDateTime;}

    /**
     * 获取：队长名称
     */
    public String getHeadMan() {
        return headMan;
    }
    /**
     * 设置：队长名称
     */
    public void setHeadMan(String headMan) {
        this.headMan = headMan;
    }
    /**
     * 获取：队长id
     */
    public Long getHeadId() {
        return headId;
    }
    /**
     * 设置：队长id
     */
    public void setHeadId(Long headId) {
        this.headId = headId;
    }
    /**
     * 获取：产值占比
     */
    public Float getoutputRate (){return this.outputRate;};
    /**
     * 设置：产值占比
     */
    public void setoutputRate (Float outputRate){this.outputRate = outputRate;};
    /**
     * 获取：最短工期
     */
    public Float getshortDateTime (){return this.shortDateTime;};
    /**
     * 设置：最短工期
     */
    public void setshortDateTime (Float shortDateTime){this.shortDateTime = shortDateTime;};
    /**
     * 获取：最迟工期
     */
    public Float getlastDateTime (){return this.lastDateTime;};
    /**
     * 设置：最迟工期
     */
    public void setlastDateTime (Float lastDateTime){this.lastDateTime = lastDateTime;};
    /**
     * 获取：项目预算产值
     */
    public Float getprojectOutput (){return this.projectOutput;};
    /**
     * 设置：项目预算产值
     */
    public void setprojectOutput (Float projectOutput){this.projectOutput = projectOutput;};
    /**
     * 获取：项目实际产值
     */
    public Float getprojectActuallyOutput (){return this.projectActuallyOutput;};
    /**
     * 设置：项目实际产值
     */
    public void setprojectActuallyOutput (Float projectActuallyOutput){this.projectActuallyOutput = projectActuallyOutput;};
    /**
     * 获取：实际产值核算
     */
    public List<CheckOutputVoEntity> getCheckOutputVoList() { return this.checkOutputVoList;}
    /**
     * 设置：实际产值核算
     */
    public void setcheckOutputList(List<CheckOutputVoEntity> checkOutputVoList) { this.checkOutputVoList = checkOutputVoList;}
}
