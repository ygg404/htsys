package io.renren.modules.perf.service;

import com.baomidou.mybatisplus.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.perf.entity.PerfAssessEntity;
import io.renren.modules.perf.vo.PerfAssessVoEntity;

import java.util.Map;
import java.util.List;

/**
 * 效能考核评分
 *
 * @author ygg
 * @date 2020-05-16 15:45:46
 */
public interface PerfAssessService extends IService<PerfAssessEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<PerfAssessEntity> queryList(Map<String, Object> params);

    void deleteByMap(PerfAssessVoEntity voEntity);

    void save(PerfAssessEntity entity);

    void update(PerfAssessEntity entity);

    void deleteBatch(Long[] Ids);
}

