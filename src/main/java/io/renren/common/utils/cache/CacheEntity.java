package io.renren.common.utils.cache;

public class CacheEntity {
    /**
     * 数据主体
     */
    private Object data;

    /**
     * 超时时长，0永不超时
     */
    private Long timeout;

    /**
     * 缓存设置初始时间
     */
    private Long initialTime;

    /**
     * 获取： 数据主体
     * @return
     */
    public Object getData() {return this.data;}
    /**
     * 设置： 数据主体
     * @return
     */
    public void setData(Object data) {this.data = data;}
    /**
     * 获取： 超时时长
     * @return
     */
    public Long getTimeout() {return this.timeout;}
    /**
     * 设置： 数据主体
     * @return
     */
    public void setTimeout(Long timeout) {this.timeout = timeout;}
    /**
     * 获取： 缓存设置初始时间
     * @return
     */
    public Long getInitialTime() {return this.initialTime;}
    /**
     * 设置： 缓存设置初始时间
     * @return
     */
    public void setInitialTime(Long initialTime) {this.initialTime = initialTime;}

}
