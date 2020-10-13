package io.renren.modules.perf.vo;

import io.renren.modules.perf.entity.PerfExtraScoringEntity;

import java.io.Serializable;
import java.util.List;

public class PerfVoEntity implements Serializable {

    /**
     * 被考核人ID
     */
    private Long checkUserId;
    /**
     * 被考核人姓名
     */
    private String checkUserName;
    /**
     * 被考核人部门ID
     */
    private Long cbranchId;
    /**
     * 被考核人部门名称
     */
    private String cbranchName;
    /**
     * 考核人Id
     */
    private Long userId;
    /**
     * 考核人姓名
     */
    private String userName;
    /**
     * 考核人部门ID
     */
    private Long ubranchId;
    /**
     * 考核人部门名称
     */
    private String ubranchName;
    /**
     * 是否为同一个部门
     */
    private Long isSameBranch;
    /**
     * 是否为其领导
     */
    private Long isGuider;
    /**
     * 考核项ID
     */
    private Long kbiId;
    /**
     * 考核项
     */
    private String kbiName;
    /**
     * 考核项占比
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
     * 月份
     */
    private Long month;
    /**
     *  角色ID
     */
    private Long roleId;
    /**
     * 角色名称
     */
    private String roleName;
    /**
     * 职务ID
     */
    private Long dutyId;
    /**
     * 职务
     */
    private String dutyName;
    /**
     * 职务基准分
     */
    private Long dutyStandScore;
    /**
     * 职称等级
     */
    private Long titleLever;
    /**
     * 职称名称
     */
    private String titleName;
    /**
     * 职称基准分
     */
    private Long titleStandScore;
    /**
     * 效能基准分
     */
    private Long kbiStandScore;
    /**
     * 考核人对应的评分列表
     */
    private List<PerfVoEntity> perfList;
    /**
     * 加减分
     */
    private List<PerfExtraScoringEntity> extraList;

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
     * 获取：被考核人部门ID
     */
    public Long getCbranchId (){return this.cbranchId;};
    /**
     * 设置：被考核人部门ID
     */
    public void setCbranchId (Long cbranchId){this.cbranchId = cbranchId;};
    /**
     * 获取：被考核人部门名称
     */
    public String getCbranchName (){return this.cbranchName;};
    /**
     * 设置：被考核人部门名称
     */
    public void setCbranchName (String cbranchName){this.cbranchName = cbranchName;};
    /**
     * 获取：考核人Id
     */
    public Long getUserId (){return this.userId;};
    /**
     * 设置：考核人Id
     */
    public void setUserId (Long userId){this.userId = userId;};
    /**
     * 获取：考核人姓名
     */
    public String getUserName (){return this.userName;};
    /**
     * 设置：考核人姓名
     */
    public void setUserName (String userName){this.userName = userName;};
    /**
     * 获取：考核人部门ID
     */
    public Long getUbranchId (){return this.ubranchId;};
    /**
     * 设置：考核人部门ID
     */
    public void setUbranchId (Long ubranchId){this.ubranchId = ubranchId;};
    /**
     * 获取：部门名称
     */
    public String getUbranchName (){return this.ubranchName;};
    /**
     * 设置：部门名称
     */
    public void setUbranchName (String ubranchName){this.ubranchName = ubranchName;};
    /**
     * 获取：是否同一部门
     */
    public Long getIsSameBranch (){return this.isSameBranch;};
    /**
     * 设置：是否同一部门
     */
    public void setIsSameBranch (Long isSameBranch){this.isSameBranch = isSameBranch;};
    /**
     * 获取：是否其领导
     */
    public Long getIsGuider (){return this.isGuider;};
    /**
     * 设置：是否其领导
     */
    public void setIsGuider (Long isGuider){this.isGuider = isGuider;};
    /**
     * 获取：考核项ID
     */
    public Long getKbiId (){return this.kbiId;};
    /**
     * 设置：考核项ID
     */
    public void setKbiId (Long kbiId){this.kbiId = kbiId;};
    /**
     * 获取：考核项
     */
    public String getKbiName (){return this.kbiName;};
    /**
     * 设置：考核项
     */
    public void setKbiName (String kbiName){this.kbiName = kbiName;};
    /**
     * 获取：考核项占比
     */
    public Long getKbiRatio (){return this.kbiRatio;};
    /**
     * 设置：考核项占比
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
     * 获取：月份
     */
    public Long getMonth (){return this.month;};
    /**
     * 设置：月份
     */
    public void setMonth (Long month){this.month = month;};
    /**
     * 获取：角色ID
     */
    public Long getRoleId (){return this.roleId;};
    /**
     * 设置：角色ID
     */
    public void setRoleId (Long roleId){this.roleId = roleId;};
    /**
     * 获取：角色名称
     */
    public String getRoleName (){return this.roleName;};
    /**
     * 设置：角色名称
     */
    public void setRoleName (String roleName){this.roleName = roleName;};
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
     * 获取：职务基准分
     */
    public Long getDutyStandScore (){return this.dutyStandScore;};
    /**
     * 设置：职务基准分
     */
    public void setDutyStandScore (Long dutyStandScore){this.dutyStandScore = dutyStandScore;};
    /**
     * 获取：职称等级
     */
    public Long getTitleLever (){return this.titleLever;};
    /**
     * 设置：职称等级
     */
    public void setTitleLever (Long titleLever){this.titleLever = titleLever;};
    /**
     * 获取：职称名称
     */
    public String getTitleName (){return this.titleName;};
    /**
     * 设置：职称名称
     */
    public void setTitleName (String titleName){this.titleName = titleName;};
    /**
     * 获取：职称效能分
     */
    public Long getTitleStandScore (){return this.titleStandScore;};
    /**
     * 设置：职称效能分
     */
    public void setTitleStandScore (Long titleStandScore){this.titleStandScore = titleStandScore;};
    /**
     * 获取：效能基准分
     */
    public Long getKbiStandScore (){return this.kbiStandScore;};
    /**
     * 设置：效能基准分
     */
    public void setKbiStandScore (Long kbiStandScore){this.kbiStandScore = kbiStandScore;};
    /**
     * 获取：考核人对应的评分列表
     */
    public List<PerfVoEntity> getPerfList() {return this.perfList;}
    /**
     * 设置：考核人对应的评分列表
     */
    public void setPerfList(List<PerfVoEntity> perfList) {this.perfList = perfList;}
    /**
     * 获取：加减分
     */
    public List<PerfExtraScoringEntity> getExtraList() {return this.extraList;}
    /**
     * 设置：加减分
     */
    public void setExtraList(List<PerfExtraScoringEntity> extraList) {this.extraList = extraList;}

}
