package io.renren.common.utils.cache;

import java.util.Map;
import java.util.Set;

public interface CacheManager {

    /**
     * 存入缓存
     * @param key
     * @param cacheData
     */
    void put(String key, CacheEntity cacheData);

    /**
     * 存入缓存
     * @param key
     * @param data
     * @param timeout
     */
    void put(String key, Object data, Long timeout);

    /**
     * 获取缓存
     * @param key
     * @return
     */
    CacheEntity getCacheByKey(String key);

    /**
     * 获取所有缓存
     * @return
     */
    Map<String, CacheEntity> getCacheAll();

    /**
     * 判断缓存是否存在
     * @param key
     * @return
     */
    boolean isExist(String key);

    /**
     * 清除所有缓存
     */
    void clearAll();

    /**
     * 清除对应缓存
     * @param key
     */
    void clearByKey(String key);

    /**
     * 缓存是否失效
     * @param key
     * @return
     */
    boolean isTimeOut(String key);

    /**
     * 获取所有key
     * @return
     */
    Set<String> getKeys();
}
