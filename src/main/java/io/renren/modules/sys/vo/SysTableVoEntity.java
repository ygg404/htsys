package io.renren.modules.sys.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 系统表数据
 */
public class SysTableVoEntity implements Serializable {
    //表的名称
    private String tableName;
    //表的属性
    private String engine;
    //表的备注
    private String tableComment;
    //表的创建时间
    private Date createTime;

    /**
     * 设置：表的名称
     *
     * @param tableName
     */
    public void setTableName(String tableName) {
        this.tableName = tableName;
    }
    /**
     * 获取：表的名称
     *
     * @return String
     */
    public String getTableName() {
        return tableName;
    }
    /**
     * 设置：表的属性
     *
     * @param engine
     */
    public void setEngine(String engine) {
        this.engine = engine;
    }
    /**
     * 获取：表的属性
     *
     * @return String
     */
    public String getEngine() {
        return engine;
    }
    /**
     * 设置：表的备注
     *
     * @param tableComment
     */
    public void setTableComment(String tableComment) {
        this.tableComment = tableComment;
    }
    /**
     * 获取：表的备注
     *
     * @return String
     */
    public String getTableComment() {
        return tableComment;
    }
    /**
     * 设置：表的创建时间
     *
     * @param createTime
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    /**
     * 获取：表的创建时间
     *
     * @return String
     */
    public Date getCreateTime() {
        return createTime;
    }
}
