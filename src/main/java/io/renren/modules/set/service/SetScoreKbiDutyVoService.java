package io.renren.modules.set.service;

import com.baomidou.mybatisplus.service.IService;
import io.renren.modules.set.vo.ScoreKbiDutyVoEntity;
import io.renren.modules.set.vo.ScoreKbiVoEntity;

import java.util.List;
import java.util.Map;

/**
 * 效能分计算方法表
 *
 * @author ygg
 * @date 2020-06-11 15:40:33
 */
public interface SetScoreKbiDutyVoService extends IService<ScoreKbiDutyVoEntity> {

    List<ScoreKbiDutyVoEntity> queryList(Map<String, Object> params);

    ScoreKbiDutyVoEntity queryByDutyId(Long dutyId);

}

