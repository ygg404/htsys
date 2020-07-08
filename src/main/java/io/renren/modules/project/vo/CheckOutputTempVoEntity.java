package io.renren.modules.project.vo;

import java.io.Serializable;
import java.util.List;

public class CheckOutputTempVoEntity implements Serializable {
    /**
     * 项目编号
     */
    private String projectNo;
    /**
     * 预算产值明细
     */
    private List<CheckOutputVoEntity> tempOutputlist;

    /**
     * 获取：项目编号
     */
    public String getprojectNo (){return this.projectNo;};
    /**
     * 设置：项目编号
     */
    public void setprojectNo (String projectNo){this.projectNo = projectNo;};

    /**
     * 获取：预算产值明细
     */
    public List<CheckOutputVoEntity>  gettempOutputlist (){return this.tempOutputlist;};
    /**
     * 设置：预算产值明细
     */
    public void settempOutputlist (List<CheckOutputVoEntity> tempOutputlist){this.tempOutputlist = tempOutputlist;};
}
