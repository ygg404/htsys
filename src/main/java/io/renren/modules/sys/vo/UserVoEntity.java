package io.renren.modules.sys.vo;

import java.io.Serializable;

public class UserVoEntity implements Serializable {

    /**
     * 用户ID
     */
    private Long userId;
    /**
     * 用户名
     */
    private String username;
    /**
     * 用户账号
     */
    private String useraccount;

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
     * 设置：用户账号
     */
    public void setUseraccount(String useraccount) {
        this.useraccount = useraccount;
    }
    /**
     * 获取：用户账号
     */
    public String getUseraccount() {
        return useraccount;
    }
}