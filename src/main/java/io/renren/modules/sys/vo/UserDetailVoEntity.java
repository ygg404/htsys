package io.renren.modules.sys.vo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 用户明细信息
 */
public class UserDetailVoEntity implements Serializable {
    /**
     * 用户id
     */
    private Long userId;
    /**
     * 用户账号
     */
    private String useraccount;

    /**
     * 用户名
     */
    private String username;
    /**
     * 生日
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
    private Date birthday;
    /**
     * 学历: 0-无；1-小学；2-初中;3-高中;4-中专;5-职高;6-大专;7-本科;8-硕士;9-博士;10-博士后
     */
    private Long education;
    /**
     * 毕业院校
     */
    private String graduateSchool;
    /**
     * 职称( 0-无；1-技术员；2-助理工程师；3-中级工程师；4-高级工程师；5-正高级工程师)
     */
    private Long professionalTitle;
    /**
     * 头像路径
     */
    private String headUrl;
    /**
     * 联系电话
     */
    private String mobile;
    /**
     * 角色列表
     */
    private List<String> roleList;
    /**
     * 工作组
     */
    private List<String> groupList;



    /**
     * 获取：用户id
     */
    public Long getuserId (){return this.userId;};
    /**
     * 设置：用户id
     */
    public void setuserId (Long userId){this.userId = userId;};
    /**
     * 设置：账号
     *
     * @param useraccount 账号
     */
    public void setUseraccount(String useraccount) {
        this.useraccount = useraccount;
    }

    /**
     * 获取：用户名
     *
     * @return String
     */
    public String getUseraccount() {
        return useraccount;
    }

    /**
     * 设置：用户名
     *
     * @param username 用户名
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * 获取：用户名
     *
     * @return String
     */
    public String getUsername() {
        return username;
    }
    /**
     * 获取：生日
     */
    public Date getbirthday (){return this.birthday;};
    /**
     * 设置：生日
     */
    public void setbirthday (Date birthday){this.birthday = birthday;};
    /**
     * 获取：学历: 0-无；1-小学；2-初中;3-高中;4-中专;5-职高;6-大专;7-本科;8-硕士;9-博士;10-博士后
     */
    public Long geteducation (){return this.education;};
    /**
     * 设置：学历: 0-无；1-小学；2-初中;3-高中;4-中专;5-职高;6-大专;7-本科;8-硕士;9-博士;10-博士后
     */
    public void seteducation (Long education){this.education = education;};
    /**
     * 获取：毕业院校
     */
    public String getgraduateSchool (){return this.graduateSchool;};
    /**
     * 设置：毕业院校
     */
    public void setgraduateSchool (String graduateSchool){this.graduateSchool = graduateSchool;};
    /**
     * 获取：职称( 0-无；1-技术员；2-助理工程师；3-中级工程师；4-高级工程师；5-正高级工程师)
     */
    public Long getprofessionalTitle (){return this.professionalTitle;};
    /**
     * 设置：职称( 0-无；1-技术员；2-助理工程师；3-中级工程师；4-高级工程师；5-正高级工程师)
     */
    public void setprofessionalTitle (Long professionalTitle){this.professionalTitle = professionalTitle;};
    /**
     * 获取：头像路径
     */
    public String getheadUrl (){return this.headUrl;};
    /**
     * 设置：头像路径
     */
    public void setheadUrl (String headUrl){this.headUrl = headUrl;};
    /**
     * 获取：联系电话
     */
    public String getmobile (){return this.mobile;};
    /**
     * 设置：联系电话
     */
    public void setmobile (String mobile){this.mobile = mobile;};
    /**
     * 获取：角色列表
     */
    public List<String> getRoleList (){return this.roleList;};
    /**
     * 设置：联系电话
     */
    public void setRoleList (List<String> roleList){this.roleList = roleList;};
    /**
     * 获取：工作组
     */
    public List<String> getGroupList (){return this.groupList;};
    /**
     * 设置：联系电话
     */
    public void setGroupList (List<String> groupList){this.groupList = groupList;};
}
