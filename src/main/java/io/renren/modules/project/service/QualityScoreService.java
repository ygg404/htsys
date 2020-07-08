package io.renren.modules.project.service;

import com.baomidou.mybatisplus.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.project.entity.QualityScoreEntity;

import java.util.List;
import java.util.Map;

/**
 * 质量评分详情
 *
 * @author ygg
 * @date 2019-11-16 11:21:53
 */
public interface QualityScoreService extends IService<QualityScoreEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<QualityScoreEntity> queryList(Map<String, Object> params);

    void save(QualityScoreEntity entity);

    void update(QualityScoreEntity entity);

    void deleteBatch(Long[] roleIds);
}

