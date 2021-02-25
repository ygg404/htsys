package io.renren.modules.webapi.vo;

import java.io.Serializable;

public class SmsVoEntity implements Serializable {
    /**
     * 手机号
     */
    private String phoneNum;
    /**
     * 用户账号
     */
    private String userAccount;
    /**
     * 机器码
     */
    private String machineCode;
    /**
     * 个人信息
     */
    private String userInfo;
    /**
     * 验证码
     */
    private String authCode;


    /**
     * 设置：手机号
     */
    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }
    /**
     * 获取：手机号
     */
    public String getPhoneNum() {
        return phoneNum;
    }
    /**
     * 设置：用户账号
     */
    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }
    /**
     * 获取：用户账号
     */
    public String getUserAccount() { return userAccount; }
    /**
     * 设置：机器码
     */
    public void setMachineCode(String machineCode) {
        this.machineCode = machineCode;
    }
    /**
     * 获取：机器码
     */
    public String getMachineCode() {
        return machineCode;
    }
    /**
     * 设置：个人信息
     */
    public void setUserInfo(String userInfo) {
        this.userInfo = userInfo;
    }
    /**
     * 获取：个人信息
     */
    public String getUserInfo() {
        return userInfo;
    }
    /**
     * 设置：验证码
     */
    public void setAuthCode(String authCode) {
        this.authCode = authCode;
    }
    /**
     * 获取：验证码
     */
    public String getAuthCode() {
        return authCode;
    }
}
