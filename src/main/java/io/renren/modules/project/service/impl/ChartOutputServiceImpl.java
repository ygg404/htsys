package io.renren.modules.project.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.modules.project.dao.ChartOutputDao;
import io.renren.modules.project.service.ChartOutputService;
import io.renren.modules.project.vo.ChartOutputVoEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("ChartOutputService")
public class ChartOutputServiceImpl extends ServiceImpl<ChartOutputDao, ChartOutputVoEntity> implements ChartOutputService {

    @Override
    public List<ChartOutputVoEntity> queryList(Map<String, Object> params){
        List<ChartOutputVoEntity> list = baseMapper.getChartOutputVoList( params );
        return list;
    }
}
