package io.renren.modules.oss.controller;

import io.renren.common.utils.R;
import org.apache.shiro.cache.CacheManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 微信接口
 *
 * @author YGG
 * @date 2017-03-25 12:13:26
 */
@RestController
@RequestMapping("sys/wx")
public class SysWxController {

    private CacheManager cacheManager;

    @GetMapping("/getWxToken")
    public R getWxToken(){
        String wxtoken = cacheManager.getCache("wxtoken").toString();

        return R.ok();
    }

}
