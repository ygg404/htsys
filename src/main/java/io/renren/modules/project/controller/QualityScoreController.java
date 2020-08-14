package io.renren.modules.project.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import io.renren.common.annotation.SysLog;
import io.renren.common.utils.MapUtils;
import io.renren.modules.project.entity.CheckQualityEntity;
import io.renren.modules.project.service.CheckQualityService;
import io.renren.modules.project.vo.ScoreRequestVoEntity;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.renren.modules.project.entity.QualityScoreEntity;
import io.renren.modules.project.service.QualityScoreService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * 质量评分详情
 *
 * @author ygg
 * @date 2019-11-16 11:21:53
 */
@RestController
@RequestMapping("project/qualityscore")
public class QualityScoreController {
    @Autowired
    private QualityScoreService qualityScoreService;
    @Autowired
    private CheckQualityService checkQualityService;
    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        List<QualityScoreEntity> list = qualityScoreService.queryList(params);

        return R.ok().put("list", list);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("project:qualityscore:info")
    public R info(@PathVariable("id") Long id){
		QualityScoreEntity qualityScore = qualityScoreService.selectById(id);

        return R.ok().put("qualityScore", qualityScore);
    }

    /**
     * 保存质量评分 明细
     */
    @SysLog("修改质量评分明细")
    @RequestMapping("/saveList")
//    @RequiresPermissions("project:qualityscore:save")
    public R saveList(@RequestBody ScoreRequestVoEntity scoreVo){
        //保存前 先根据项目编号删除评分明细
        if(scoreVo.getprojectNo() == null || scoreVo.getprojectNo().length() <= 0)
        {
            return R.error("项目编号异常！请刷新重试。");
        }
        MapUtils params = new MapUtils().put("project_no",scoreVo.getprojectNo());
        qualityScoreService.deleteByMap(params);
        //根据项目编号获取质检检查
        CheckQualityEntity cqEntity = checkQualityService.selectOne(
                new EntityWrapper<CheckQualityEntity>().eq("project_no", scoreVo.getprojectNo())
        );
        if(cqEntity == null) cqEntity =new CheckQualityEntity();
        cqEntity.setqualityScore(scoreVo.getqualityScore());
        cqEntity.setprojectNo(scoreVo.getprojectNo());
        checkQualityService.insertOrUpdate(cqEntity);
        //批量插入评分明细
		if(scoreVo.getScoreList().size() > 0)qualityScoreService.insertBatch(scoreVo.getScoreList());

        return R.ok();
    }


    /**
     * 修改
     */
    @SysLog("修改质量评分")
    @RequestMapping("/update")
    @RequiresPermissions("project:qualityscore:update")
    public R update(@RequestBody QualityScoreEntity qualityScore){
		qualityScoreService.updateById(qualityScore);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("project:qualityscore:delete")
    public R delete(@RequestBody Long[] ids){
		qualityScoreService.deleteBatch(ids);

        return R.ok();
    }

}
