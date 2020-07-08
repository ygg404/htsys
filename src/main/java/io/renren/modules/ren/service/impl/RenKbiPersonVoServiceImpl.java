package io.renren.modules.ren.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.modules.ren.dao.RenKbiPersonVoDao;
import io.renren.modules.ren.service.RenKbiPersonVoService;
import io.renren.modules.ren.vo.RenKbiPersonVoEntity;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Map;


@Service("renKbiPersonVoService")
public class RenKbiPersonVoServiceImpl extends ServiceImpl<RenKbiPersonVoDao, RenKbiPersonVoEntity> implements RenKbiPersonVoService {

    @Override
    public List<RenKbiPersonVoEntity> queryList(Map<String, Object> params){
        return this.baseMapper.getKbiPersonVoList(params);
    }
}