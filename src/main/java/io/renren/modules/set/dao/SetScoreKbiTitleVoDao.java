package io.renren.modules.set.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import io.renren.modules.set.vo.ScoreKbiDutyVoEntity;
import io.renren.modules.set.vo.ScoreKbiTitleVoEntity;
import io.renren.modules.set.vo.ScoreKbiVoEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 效能分计算方法表
 *
 * @author ygg
 * @date 2020-06-11 15:40:33
 */
@Mapper
public interface SetScoreKbiTitleVoDao extends BaseMapper<ScoreKbiTitleVoEntity> {

    List<ScoreKbiTitleVoEntity> getTitleKbiList(Map<String, Object> params);

    ScoreKbiTitleVoEntity getOneByDutyId(Long dutyId);
}
