package io.renren.modules.set.vo;

import io.renren.modules.ren.vo.RenRecordVoEntity;
import io.renren.modules.sys.entity.SysUserEntity;
import io.renren.modules.sys.vo.UserVoEntity;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;

import java.io.Serializable;
import java.util.List;

public class BranchVoEntity implements Serializable {
    /**
     * 部门Id
     */
    private Long id;
    /**
     * 父部门Id
     */
    private Long parentId;
    /**
     * 部门名称
     */
    private String branchName;
    /**
     * 主负责人Id
     */
    private Long mdeputyId;
    /**
     * 主负责人名称
     */
    private String mdeputyName;
    /**
     * 副负责人Id
     */
    private Long sdeputyId;
    /**
     * 副负责人名称
     */
    private String sdeputyName;
    /**
     * 序号
     */
    private Long orderNum;
    /**
     * 成员列表
     */
    private List<UserVoEntity> userList;
    /**
     * 成员列表(带档案)
     */
    private List<RenRecordVoEntity> recordVoList;
    /**
     * 子类工作组
     */
    private List<BranchVoEntity> childList;

    /**
     * 获取：部门Id
     */
    public Long getid (){return this.id;};
    /**
     * 设置：部门Id
     */
    public void setid (Long id){this.id = id;};
    /**
     * 获取：父部门Id
     */
    public Long getparentId (){return this.parentId;};
    /**
     * 设置：父部门Id
     */
    public void setparentId (Long parentId){this.parentId = parentId;};
    /**
     * 获取：部门名称
     */
    public String getbranchName (){return this.branchName;};
    /**
     * 设置：部门名称
     */
    public void setbranchName (String branchName){this.branchName = branchName;};
    /**
     * 获取：主负责人Id
     */
    public Long getmdeputyId (){return this.mdeputyId;};
    /**
     * 设置：主负责人Id
     */
    public void setmdeputyId (Long mdeputyId){this.mdeputyId = mdeputyId;};
    /**
     * 获取：副负责人名称
     */
    public String getmdeputyName (){return this.mdeputyName;};
    /**
     * 设置：副负责人名称
     */
    public void setmdeputyName (String mdeputyName){this.mdeputyName = mdeputyName;};
    /**
     * 获取：副负责人Id
     */
    public Long getsdeputyId (){return this.sdeputyId;};
    /**
     * 设置：副负责人Id
     */
    public void setsdeputyId (Long sdeputyId){this.sdeputyId = sdeputyId;};
    /**
     * 获取：副负责人名称
     */
    public String getsdeputyName (){return this.sdeputyName;};
    /**
     * 设置：副负责人名称
     */
    public void setsdeputyName (String sdeputyName){this.sdeputyName = sdeputyName;};
    /**
     * 获取：序号
     */
    public Long getorderNum (){return this.orderNum;};
    /**
     * 设置：序号
     */
    public void setorderNum (Long orderNum){this.orderNum = orderNum;};
    /**
     *获取： 成员列表
     */
    public List<UserVoEntity> getUserList() { return this.userList;}
    /**
     *设置： 成员列表
     */
    public void setUserList(List<UserVoEntity> userList) { this.userList = userList;}
    /**
     *获取： 成员列表（带档案）
     */
    public List<RenRecordVoEntity> getRecordVoList() { return this.recordVoList;}
    /**
     *设置： 成员列表（带档案）
     */
    public void setRecordVoList(List<RenRecordVoEntity> recordVoList) { this.recordVoList = recordVoList;}
    /**
     * 获取： 子类工作组
     */
    public List<BranchVoEntity> getChildList() { return this.childList;}
    /**
     *设置： 子类工作组
     */
    public void setChildList(List<BranchVoEntity> list) {this.childList = list;}
}
