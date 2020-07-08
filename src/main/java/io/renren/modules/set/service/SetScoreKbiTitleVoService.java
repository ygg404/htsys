package io.renren.modules.set.service;

import com.baomidou.mybatisplus.service.IService;
import io.renren.modules.set.vo.ScoreKbiTitleVoEntity;


import java.util.List;
import java.util.Map;

/**
 * 效能分计算方法表
 *
 * @author ygg
 * @date 2020-06-11 15:40:33
 */
public interface SetScoreKbiTitleVoService extends IService<ScoreKbiTitleVoEntity> {

    List<ScoreKbiTitleVoEntity> queryList(Map<String, Object> params);

    ScoreKbiTitleVoEntity queryByDutyId(Long dutyId);

}

