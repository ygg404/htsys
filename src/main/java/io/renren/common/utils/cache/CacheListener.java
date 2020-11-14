package io.renren.common.utils.cache;

import java.util.Set;

public class CacheListener {
    private CacheManager cacheManager;

    public CacheListener(CacheManager cacheManager) {
        this.cacheManager = cacheManager;
    }

    public void startListen() {
        new Thread(new Runnable() {

            @Override
            public void run() {
                while(true) {
                    Set<String> keys = cacheManager.getKeys();

                    for (String key : keys) {
                        if(cacheManager.isTimeOut(key)) {
                            cacheManager.clearByKey(key);
                            System.out.println(key + " 被清除了");
                        }
                    }

                    try {
                        //休息30s再检查
                        Thread.sleep(30 * 1000);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
}
