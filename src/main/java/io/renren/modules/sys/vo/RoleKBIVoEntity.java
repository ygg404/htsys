package io.renren.modules.sys.vo;


import io.renren.modules.sys.entity.SysRoleKbiEntity;

import java.io.Serializable;
import java.util.List;

public class RoleKBIVoEntity implements Serializable {

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
     * 用户名
     */
    private String username;
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
     * 岗位与考核项占比关系
     */
    private List<SysRoleKbiEntity> rkList;

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
        return userId;
    }
    /**
     * 设置：用户名
     */
    public void setUsername(String username) {
        this.username = username;
    }
    /**
     * 获取：用户名
     */
    public String getUsername() {
        return username;
    }
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
     * 获取： 岗位与考核项占比关系
     */
    public List<SysRoleKbiEntity> getRkList(){
        return this.rkList;
    }
    /**
     * 设置：岗位与考核项占比关系
     */
    public void setRkList(List<SysRoleKbiEntity> rkList){
        this.rkList = rkList;
    }
}
