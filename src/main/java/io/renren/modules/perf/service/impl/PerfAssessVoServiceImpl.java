package io.renren.modules.perf.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.modules.perf.vo.PerfAssessVoEntity;
import io.renren.modules.perf.dao.PerfAssessVoDao;
import io.renren.modules.perf.service.PerfAssessVoService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("perfAccessVoService")
public class PerfAssessVoServiceImpl extends ServiceImpl< PerfAssessVoDao,PerfAssessVoEntity> implements PerfAssessVoService {


    @Override
    public List<PerfAssessVoEntity> queryPerfAccessVoList(Map<String, Object> params){
        return this.baseMapper.getPerfAccessVoList(params);
    }

    @Override
    public Long queryCountPerfAccess(Map<String, Object> params){
        return this.baseMapper.countPerfAccess(params);
    }

    @Override
    public List<PerfAssessVoEntity> queryUserRoleList(Map<String, Object> params){
        return this.baseMapper.getUserRoleList(params);
    }

    @Override
    public List<PerfAssessVoEntity> queryUserHasAssess(Map<String, Object> params) {
        return this.baseMapper.getUserHasAssess(params);
    }
}
