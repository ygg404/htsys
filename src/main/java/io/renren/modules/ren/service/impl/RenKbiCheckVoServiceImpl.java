package io.renren.modules.ren.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.modules.ren.dao.RenKbiCheckVoDao;
import io.renren.modules.ren.dao.RenKbiPersonVoDao;
import io.renren.modules.ren.service.RenKbiCheckVoService;
import io.renren.modules.ren.service.RenKbiPersonVoService;
import io.renren.modules.ren.vo.RenKbiCheckVoEntity;
import io.renren.modules.ren.vo.RenKbiPersonVoEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service("renKbiCheckVoService")
public class RenKbiCheckVoServiceImpl extends ServiceImpl<RenKbiCheckVoDao, RenKbiCheckVoEntity> implements RenKbiCheckVoService {

    @Override
    public List<RenKbiCheckVoEntity> queryList(Map<String, Object> params){
        return this.baseMapper.getKbiCheckVoList(params);
    }
}