package io.renren.modules.set.vo;

import java.io.Serializable;

public class ScoreKbiVoEntity implements Serializable {

    /**
     * 职务ID
     */
    private Long dutyId;
    /**
     * 职务
     */
    private String dutyName;
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
     * 获取：职务ID
     */
    public Long getDutyId (){return this.dutyId;};
    /**
     * 设置：职务ID
     */
    public void setDutyId (Long dutyId){this.dutyId = dutyId;};
    /**
     * 获取：职务
     */
    public String getDutyName (){return this.dutyName;};
    /**
     * 设置：职务
     */
    public void setDutyName (String dutyName){this.dutyName = dutyName;};
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
