package io.renren.modules.set.service;

import com.baomidou.mybatisplus.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.set.entity.SetScoreKbiEntity;
import io.renren.modules.set.vo.ScoreKbiVoEntity;

import java.util.List;
import java.util.Map;

/**
 * 效能分计算方法表
 *
 * @author ygg
 * @date 2020-06-11 15:40:33
 */
public interface SetScoreKbiVoService extends IService<ScoreKbiVoEntity> {

    List<ScoreKbiVoEntity> queryList(Map<String, Object> params);

    ScoreKbiVoEntity queryByDutyId(Long dutyId);

}

