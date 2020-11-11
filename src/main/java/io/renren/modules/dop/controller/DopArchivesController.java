package io.renren.modules.dop.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;
import io.renren.modules.dop.service.DopArchivesService;
import io.renren.modules.project.entity.ProjectArchivesEntity;
import io.renren.modules.project.service.ProjectArchivesService;
import io.renren.modules.project.vo.ProjectArchivesVoEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
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

    @Autowired
    private ProjectArchivesService projectArchivesService;

    /**
     * 列表
     */
    @RequestMapping("/page")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = dopArchivesService.queryArchivesPage(params);

        return R.ok().put("page", page);
    }

    /**
     * 列表
     */
    @RequestMapping("/info")
    public R info(@RequestParam Map<String, Object> params){
        ProjectArchivesVoEntity archives = dopArchivesService.queryArchivesVo(params);

        return R.ok().put("archives", archives);
    }

    @RequestMapping("/update")
    public R update(@RequestBody ProjectArchivesEntity archive){
        dopArchivesService.update(archive);
        return R.ok();
    }
}
