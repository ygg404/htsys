package io.renren.modules.dop.controller;

import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;
import io.renren.modules.dop.service.DopArchivesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 项目档案表
 *
 * @author ygg
 * @date 2020-09-07 10:28:20
 */
@RestController
@RequestMapping("dop/archives")
public class DopArchivesController {

    @Autowired
    private DopArchivesService dopArchivesService;
    /**
     * 列表
     */
    @RequestMapping("/page")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = dopArchivesService.queryArchivesPage(params);

        return R.ok().put("page", page);
    }
}
