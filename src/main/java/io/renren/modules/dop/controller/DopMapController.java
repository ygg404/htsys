package io.renren.modules.dop.controller;

import io.renren.common.utils.R;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 * 地图接口
 *
 * @author ygg
 * @date 2020-09-07 10:28:20
 */
@RestController
@RequestMapping("dop/map")
public class DopMapController {

    /**
     * 根据IP 获取地理位置
     */
    @RequestMapping("/getAddress")
    public R list(@RequestParam String ip){


        return R.ok();
    }
}
