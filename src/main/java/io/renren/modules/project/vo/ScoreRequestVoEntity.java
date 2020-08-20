package io.renren.modules.project.vo;

import io.renren.modules.project.entity.QualityScoreEntity;

import java.io.Serializable;
import java.util.List;

/**
 * 质量评分视图类
 */
public class ScoreRequestVoEntity implements Serializable {

    /**
     * 项目编号
     */
    private String projectNo;
    /**
     * 评分明细
     */
    private List<QualityScoreEntity> scoreList;
    /**
     * 质检评分
     */
    private Float qualityScore;
    /**
     * 点位中误差
     */
    private Float errorPoint;
    /**
     * 间距中误差
     */
    private Float errorSpace;
    /**
     * 高程中误差
     */
    private Float errorHeigh;

    /**
     * 获取：项目编号
     */
    public String getprojectNo (){return this.projectNo;};
    /**
     * 设置：项目编号
     */
    public void setprojectNo (String projectNo){this.projectNo = projectNo;};
    /**
     * 获取：评分明细
     */
    public List<QualityScoreEntity> getScoreList (){return this.scoreList;};
    /**
     * 设置：评分明细
     */
    public void setScoreList (List<QualityScoreEntity> scoreList){this.scoreList = scoreList;};
    /**
     * 获取：质检评分
     */
    public Float getqualityScore (){return this.qualityScore;};
    /**
     * 设置：质检评分
     */
    public void setqualityScore (Float qualityScore){this.qualityScore = qualityScore;};
    /**
     * 获取：点位中误差
     */
    public Float getErrorPoint (){return this.errorPoint;};
    /**
     * 设置：点位中误差
     */
    public void setErrorPoint (Float errorPoint){this.errorPoint = errorPoint;};
    /**
     * 获取：间距中误差
     */
    public Float getErrorSpace (){return this.errorSpace;};
    /**
     * 设置：间距中误差
     */
    public void setErrorSpace (Float errorSpace){this.errorSpace = errorSpace;};
    /**
     * 获取：高程中误差
     */
    public Float getErrorHeigh (){return this.errorHeigh;};
    /**
     * 设置：高程中误差
     */
    public void setErrorHeigh (Float errorHeigh){this.errorHeigh = errorHeigh;};
}
