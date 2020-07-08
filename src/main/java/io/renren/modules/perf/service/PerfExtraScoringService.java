package io.renren.modules.perf.service;

import com.baomidou.mybatisplus.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.perf.entity.PerfExtraScoringEntity;
import io.renren.modules.perf.vo.PerfExtraVoEntity;

import java.util.Map;
import java.util.List;

/**
 * 加减分年度评分
 *
 * @author ygg
 * @date 2020-05-25 17:36:28
 */
public interface PerfExtraScoringService extends IService<PerfExtraScoringEntity> {

    PageUtils queryPage(Map<String, Object> params);

    void deleteByParms(PerfExtraVoEntity voEntity);

    List<PerfExtraScoringEntity> queryList(Map<String, Object> params);

    void save(PerfExtraScoringEntity entity);

    void update(PerfExtraScoringEntity entity);

    void deleteBatch(Long[] Ids);
}

