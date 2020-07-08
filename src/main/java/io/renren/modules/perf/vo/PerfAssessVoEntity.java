package io.renren.modules.perf.vo;

import io.renren.modules.perf.entity.PerfAssessEntity;

import java.io.Serializable;
import java.util.List;

public class PerfAssessVoEntity implements Serializable {
    /**
     * 岗位ID
     */
    private Long roleId;
    /**
     * 岗位名称
     */
    private String roleName;
    /**
     * 用户ID
     */
    private Long userId;
    /**
     * 用户名称
     */
    private String userName;
    /**
     * 职务ID
     */
    private Long dutyId;
    /**
     * 职务
     */
    private String  dutyName;
    /**
     * 效能标准分
     */
    private Long standardScore;
    /**
     * 被考核人ID
     */
    private Long checkUserId;
    /**
     * 被考核人姓名
     */
    private String checkUserName;
    /**
     * 考核项ID
     */
    private Long kbiId;
    /**
     * 考核项名称
     */
    private String kbiName;
    /**
     * 考核占比
     */
    private Long kbiRatio;
    /**
     * 考核评分
     */
    private Long kbiScore;
    /**
     * 年度
     */
    private Long year;
    /**
     * 上下半年(0-上半年，1-下半年 )
     */
    private Long updown;
    /**
     * 是否已经提交
     */
    private boolean isAssess;
    /**
     * 年度考核人与被考核人对应关系评分列表
     */
    private List<PerfAssessEntity> accessList;


    /**
     * 获取：岗位ID
     */
    public Long getRoleId (){return this.roleId;};
    /**
     * 设置：岗位ID
     */
    public void setRoleId (Long roleId){this.roleId = roleId;};
    /**
     * 获取：岗位名称
     */
    public String getRoleName (){return this.roleName;};
    /**
     * 设置：岗位名称
     */
    public void setRoleName (String roleName){this.roleName = roleName;};
    /**
     * 设置：用户ID
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }
    /**
     * 获取：用户ID
     */
    public Long getUserId() {
        return this.userId;
    }
    /**
     * 设置：用户名
     */
    public void setUserName(String username) {
        this.userName = username;
    }
    /**
     * 获取：用户名
     */
    public String getUserName() {
        return this.userName;
    }
    /**
     * 设置：职务ID
     */
    public void setDutyId(Long dutyId) {
        this.dutyId = dutyId;
    }
    /**
     * 获取：职务Id
     */
    public Long getDutyId() {return this.dutyId;}
    /**
     * 设置：职务名称
     */
    public void setDutyName(String dutyName) {
        this.dutyName = dutyName;
    }
    /**
     * 获取：职务名称
     */
    public String getDutyName() {return this.dutyName;}
    /**
     * 设置：效能标准分
     */
    public void setStandardScore(Long standardScore) {this.standardScore = standardScore;};
    /**
     * 获取：效能标准分
     */
    public Long getStandardScore() {return this.standardScore;};
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
     * 获取：考核项ID
     */
    public Long getKbiId (){return this.kbiId;};
    /**
     * 设置：考核项ID
     */
    public void setKbiId (Long kbiId){this.kbiId = kbiId;};
    /**
     * 获取：考核项名称
     */
    public String getKbiName (){return this.kbiName;};
    /**
     * 设置：考核项名称
     */
    public void setKbiName (String kbiName){this.kbiName = kbiName;};
    /**
     * 获取：考核占比
     */
    public Long getKbiRatio (){return this.kbiRatio;};
    /**
     * 设置：考核占比
     */
    public void setKbiRatio (Long kbiRatio){this.kbiRatio = kbiRatio;};
    /**
     * 获取：考核评分
     */
    public Long getKbiScore (){return this.kbiScore;};
    /**
     * 设置：考核评分
     */
    public void setKbiScore (Long kbiScore){this.kbiScore = kbiScore;};
    /**
     * 获取：年度
     */
    public Long getYear (){return this.year;};
    /**
     * 设置：年度
     */
    public void setYear (Long year){this.year = year;};
    /**
     * 获取：上下半年(0-上半年，1-下半年 )
     */
    public Long getUpdown (){return this.updown;};
    /**
     * 设置：上下半年(0-上半年，1-下半年 )
     */
    public void setUpdown (Long updown){this.updown = updown;};
    /**
     * 获取： 是否已经提交
     */
    public boolean getIsAssess(){ return this.isAssess; }
    /**
     * 设置： 是否已经提交
     */
    public void setIsAssess(boolean isAssess){ this.isAssess = isAssess; }
    /**
     * 获取：年度考核人与被考核人对应关系评分列表
     */
    public List<PerfAssessEntity> getAccessList(){
        return this.accessList;
    }
    /**
     * 设置：年度考核人与被考核人对应关系评分列表
     */
    public void setAccessList(List<PerfAssessEntity> accessList){
        this.accessList = accessList;
    }


}
