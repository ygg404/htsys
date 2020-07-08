package io.renren.modules.ren.vo;

import java.io.Serializable;
import java.util.Date;

public class RenSalaryVoEntity implements Serializable {
    /**
     * 自增ID
     */
    private Long id;
    /**
     * 用户ID
     */
    private Long userId;
    /**
     * 组队ID
     */
    private Long branchId;
    /**
     * 组队名字
     */
    private String branchName;
    /**
     * 用户名
     */
    private String username;
    /**
     * 基本工资
     */
    private Float baseSalary;
    /**
     * 职务工资
     */
    private Float workSalary;
    /**
     * 职称工资
     */
    private Float titleSalary;
    /**
     * 住房补贴
     */
    private Float housingSalary;
    /**
     * 电脑补贴
     */
    private Float pcSalary;
    /**
     * 餐补贴
     */
    private Float mealSalary;
    /**
     * 社保
     */
    private Float socialSalary;
    /**
     * 修改时间
     */
    private Date createDate;

    /**
     * 获取：自增ID
     */
    public Long getid (){return this.id;};
    /**
     * 设置：自增ID
     */
    public void setid (Long id){this.id = id;};
    /**
     * 获取：用户ID
     */
    public Long getuserId (){return this.userId;};
    /**
     * 设置：用户ID
     */
    public void setuserId (Long userId){this.userId = userId;};
    /**
     * 获取：部门ID
     */
    public Long getBranchId (){return this.branchId;};
    /**
     * 设置：部门ID
     */
    public void setBranchId (Long branchId){this.branchId = branchId;};
    /**
     * 获取：部门名称
     */
    public String getBranchName (){return this.branchName;};
    /**
     * 设置：部门名称
     */
    public void setBranchName (String branchName){this.branchName = branchName;};
    /**
     * 获取：用户名
     */
    public String getusername (){return this.username;};
    /**
     * 设置：用户名
     */
    public void setusername (String username){this.username = username;};
    /**
     * 获取：基本工资
     */
    public Float getbaseSalary (){return this.baseSalary;};
    /**
     * 设置：基本工资
     */
    public void setbaseSalary (Float baseSalary){this.baseSalary = baseSalary;};
    /**
     * 获取：职务工资
     */
    public Float getworkSalary (){return this.workSalary;};
    /**
     * 设置：职务工资
     */
    public void setworkSalary (Float workSalary){this.workSalary = workSalary;};
    /**
     * 获取：职称工资
     */
    public Float gettitleSalary (){return this.titleSalary;};
    /**
     * 设置：职称工资
     */
    public void settitleSalary (Float titleSalary){this.titleSalary = titleSalary;};
    /**
     * 获取：住房补贴
     */
    public Float gethousingSalary (){return this.housingSalary;};
    /**
     * 设置：住房补贴
     */
    public void sethousingSalary (Float housingSalary){this.housingSalary = housingSalary;};
    /**
     * 获取：电脑补贴
     */
    public Float getpcSalary (){return this.pcSalary;};
    /**
     * 设置：电脑补贴
     */
    public void setpcSalary (Float pcSalary){this.pcSalary = pcSalary;};
    /**
     * 获取：餐补贴
     */
    public Float getmealSalary (){return this.mealSalary;};
    /**
     * 设置：餐补贴
     */
    public void setmealSalary (Float mealSalary){this.mealSalary = mealSalary;};
    /**
     * 获取：社保
     */
    public Float getsocialSalary (){return this.socialSalary;};
    /**
     * 设置：社保
     */
    public void setsocialSalary (Float socialSalary){this.socialSalary = socialSalary;};
    /**
     * 获取：修改时间
     */
    public Date getcreateDate (){return this.createDate;};
    /**
     * 设置：修改时间
     */
    public void setcreateDate (Date createDate){this.createDate = createDate;};
}
