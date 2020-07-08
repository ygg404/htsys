package io.renren.modules.set.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;
import io.renren.modules.set.dao.SetScoreKbiDao;
import io.renren.modules.set.dao.SetScoreKbiVoDao;
import io.renren.modules.set.entity.SetScoreKbiEntity;
import io.renren.modules.set.service.SetScoreKbiService;
import io.renren.modules.set.service.SetScoreKbiVoService;
import io.renren.modules.set.vo.ScoreKbiVoEntity;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Map;


@Service("setScoreKbiVoService")
public class SetScoreKbiVoServiceImpl extends ServiceImpl<SetScoreKbiVoDao, ScoreKbiVoEntity> implements SetScoreKbiVoService {

    @Override
    public List<ScoreKbiVoEntity> queryList(Map<String, Object> params){
        List<ScoreKbiVoEntity> list = this.baseMapper.getDutyKbiList(params);

        return list;
    }

    @Override
    public ScoreKbiVoEntity queryByDutyId(Long dutyId){
        ScoreKbiVoEntity entity = this.baseMapper.getOneByDutyId(dutyId);
        return entity;
    }
}