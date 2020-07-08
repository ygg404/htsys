package io.renren.modules.set.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.modules.set.dao.SetScoreKbiDutyVoDao;
import io.renren.modules.set.dao.SetScoreKbiTitleVoDao;
import io.renren.modules.set.service.SetScoreKbiDutyVoService;
import io.renren.modules.set.service.SetScoreKbiTitleVoService;
import io.renren.modules.set.vo.ScoreKbiDutyVoEntity;
import io.renren.modules.set.vo.ScoreKbiTitleVoEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service("setScoreKbiDutyVoService")
public class SetScoreKbiDutyVoServiceImpl extends ServiceImpl<SetScoreKbiDutyVoDao, ScoreKbiDutyVoEntity> implements SetScoreKbiDutyVoService {

    @Override
    public List<ScoreKbiDutyVoEntity> queryList(Map<String, Object> params){
        List<ScoreKbiDutyVoEntity> list = this.baseMapper.getDutyKbiList(params);

        return list;
    }

    @Override
    public ScoreKbiDutyVoEntity queryByDutyId(Long dutyId){
        ScoreKbiDutyVoEntity entity = this.baseMapper.getOneByDutyId(dutyId);
        return entity;
    }
}