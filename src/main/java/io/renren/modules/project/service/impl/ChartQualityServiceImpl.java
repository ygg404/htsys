package io.renren.modules.project.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.modules.project.dao.ChartQualityDao;
import io.renren.modules.project.service.ChartQualityService;
import io.renren.modules.project.vo.ChartQualityVoEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("ChartQualityService")
public class ChartQualityServiceImpl extends ServiceImpl<ChartQualityDao, ChartQualityVoEntity> implements ChartQualityService {

    @Override
    public List<ChartQualityVoEntity> queryList(Map<String, Object> params){
        List<ChartQualityVoEntity> list = baseMapper.getChartQualityVoList( params );
        return list;
    }
}
