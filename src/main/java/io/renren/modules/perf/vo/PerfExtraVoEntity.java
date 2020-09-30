package io.renren.modules.perf.vo;

import io.renren.modules.perf.entity.PerfExtraScoringEntity;

import java.io.Serializable;
import java.util.List;

public class PerfExtraVoEntity implements Serializable {

    /**
     * 被考核人的ID
     */
    private Long checkUserId;
    /**
     * 被考核人的用户名
     */
    private String checkUserName;
    /**
     * 序号
     */
    private Long extraId;
    /**
     * 加减分项
     */
    private String extraItem;
    /**
     * 计分标准
     */
    private String standard;
    /**
     * 说明
     */
    private String remark;
    /**
     * 加/减类型(0-加分类型；1-减分类型)
     */
    private Long extraType;
    /**
     * 加减计分个数
     */
    private Long extraNum;
    /**
     * 年份
     */
    private Long year;
    /**
     * 月份
     */
    private Long month;
    /**
     * 考核对象列表
     */
    private List<PerfExtraScoringEntity> scoreList;

    /**
     * 获取：被考核人ID
     */
    public Long getCheckUserId (){return this.checkUserId;};
    /**
     * 设置：被考核人ID
     */
    public void setCheckUserId (Long checkUserId){this.checkUserId = checkUserId;};
    /**
     * 获取：被考核人姓名
     */
    public String getCheckUserName (){return this.checkUserName;};
    /**
     * 设置：被考核人姓名
     */
    public void setCheckUserName (String checkUserName){this.checkUserName = checkUserName;};
    /**
     * 获取：序号
     */
    public Long getExtraId (){return this.extraId;};
    /**
     * 设置：序号
     */
    public void setExtraId (Long extraId){this.extraId = extraId;};
    /**
     * 获取：加减分项
     */
    public String getExtraItem (){return this.extraItem;};
    /**
     * 设置：加减分项
     */
    public void setExtraItem (String extraItem){this.extraItem = extraItem;};
    /**
     * 获取：计分标准
     */
    public String getStandard (){return this.standard;};
    /**
     * 设置：计分标准
     */
    public void setStandard (String standard){this.standard = standard;};
    /**
     * 获取：说明
     */
    public String getRemark (){return this.remark;};
    /**
     * 设置：说明
     */
    public void setRemark (String remark){this.remark = remark;};
    /**
     * 获取：加/减类型(0-加分类型；1-减分类型)
     */
    public Long getExtraType (){return this.extraType;};
    /**
     * 设置：加/减类型(0-加分类型；1-减分类型)
     */
    public void setExtraType (Long extraType){this.extraType = extraType;};
    /**
     * 获取：加减计分个数
     */
    public Long getExtraNum (){return this.extraNum;};
    /**
     * 设置：加减计分个数
     */
    public void setExtraNum (Long extraNum){this.extraNum = extraNum;};
    /**
     * 获取：年份
     */
    public Long getYear (){return this.year;};
    /**
     * 设置：年份
     */
    public void setYear (Long year){this.year = year;};
    /**
     * 获取：月份
     */
    public Long getMonth (){return this.month;};
    /**
     * 设置：月份
     */
    public void setMonth (Long month){this.month = month;};
    /**
     * 获取： 考核对象列表
     */
    public List<PerfExtraScoringEntity> getScoreList(){
        return this.scoreList;
    }
    /**
     * 获取： 考核对象列表
     */
    public void setScoreList(List<PerfExtraScoringEntity> scoreList) {
        this.scoreList = scoreList;
    }
}
