package io.renren.modules.set.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.modules.set.dao.SetScoreKbiTitleVoDao;
import io.renren.modules.set.dao.SetScoreKbiVoDao;
import io.renren.modules.set.service.SetScoreKbiTitleVoService;
import io.renren.modules.set.service.SetScoreKbiVoService;
import io.renren.modules.set.vo.ScoreKbiTitleVoEntity;
import io.renren.modules.set.vo.ScoreKbiVoEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service("setScoreKbiTitleVoService")
public class SetScoreKbiTitleVoServiceImpl extends ServiceImpl<SetScoreKbiTitleVoDao, ScoreKbiTitleVoEntity> implements SetScoreKbiTitleVoService {

    @Override
    public List<ScoreKbiTitleVoEntity> queryList(Map<String, Object> params){
        List<ScoreKbiTitleVoEntity> list = this.baseMapper.getTitleKbiList(params);

        return list;
    }

    @Override
    public ScoreKbiTitleVoEntity queryByDutyId(Long dutyId){
        ScoreKbiTitleVoEntity entity = this.baseMapper.getOneByDutyId(dutyId);
        return entity;
    }
}