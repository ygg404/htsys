package io.renren.modules.set.vo;

import java.io.Serializable;

public class ScoreKbiTitleVoEntity implements Serializable {

    /**
     * 职称ID
     */
    private Long titleId;
    /**
     * 职务
     */
    private String titleName;
    /**
     * 基准分
     */
    private Long standardScore;
    /**
     * 未考核时基准分
     */
    private Long assessnoScore;
    /**
     * 试用期基准分
     */
    private Long trialScore;

    /**
     * 获取：职称ID
     */
    public Long getTitleId (){return this.titleId;};
    /**
     * 设置：职称ID
     */
    public void setTitleId (Long titleId){this.titleId = titleId;};
    /**
     * 获取：职称
     */
    public String getTitleName (){return this.titleName;};
    /**
     * 设置：职称
     */
    public void setTitleName (String titleName){this.titleName = titleName;};
    /**
     * 获取：基准分
     */
    public Long getStandardScore (){return this.standardScore;};
    /**
     * 设置：基准分
     */
    public void setStandardScore (Long standardScore){this.standardScore = standardScore;};
    /**
     * 获取：未考核时基准分
     */
    public Long getAssessnoScore (){return this.assessnoScore;};
    /**
     * 设置：未考核时基准分
     */
    public void setAssessnoScore (Long assessnoScore){this.assessnoScore = assessnoScore;};
    /**
     * 获取：试用期基准分
     */
    public Long getTrialScore (){return this.trialScore;};
    /**
     * 设置：试用期基准分
     */
    public void setTrialScore (Long trialScore){this.trialScore = trialScore;};
}
