package io.renren.common.utils.cache;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class CacheManagerImpl implements CacheManager{

    private static volatile CacheManagerImpl INSTANCE;

    private static Map<String, CacheEntity> caches = new ConcurrentHashMap<>();

    private  CacheManagerImpl() {
    }

    public static CacheManagerImpl getInstance() {
        if (INSTANCE == null) {
            synchronized (CacheManagerImpl.class) {
                if (INSTANCE == null) {
                    INSTANCE = new CacheManagerImpl();
                }
            }
        }
        return INSTANCE;
    }

    @Override
    public void put(String key, CacheEntity cacheData) {
        caches.put(key, cacheData);
    }

    @Override
    public void put(String key, Object data, Long timeout) {
        timeout = timeout > 0? timeout : 0L;
        CacheEntity entity = new CacheEntity();
        entity.setData(data);
        entity.setTimeout(timeout);
        entity.setInitialTime(System.currentTimeMillis());
        put(key, entity);
    }

    @Override
    public CacheEntity getCacheByKey(String key) {
        if(this.isExist(key))
            return caches.get(key);
        return null;
    }

    @Override
    public Map<String, CacheEntity> getCacheAll() {
        return caches;
    }

    @Override
    public boolean isExist(String key) {
        return caches.containsKey(key);
    }

    @Override
    public void clearAll() {
        caches.clear();
    }

    @Override
    public void clearByKey(String key) {
        if (this.isExist(key)) {
            caches.remove(key);
        }
    }

    @Override
    public boolean isTimeOut(String key) {
        if (!this.isExist(key)) {
            return true;
        }

        CacheEntity cacheEntity = caches.get(key);
        Long timeOut = cacheEntity.getTimeout();
        Long initialTime = cacheEntity.getInitialTime();

        if (timeOut == 0 || System.currentTimeMillis() - initialTime < timeOut) {
            return false;
        }
        return true;

    }

    @Override
    public Set<String> getKeys() {
        return caches.keySet();
    }

}
