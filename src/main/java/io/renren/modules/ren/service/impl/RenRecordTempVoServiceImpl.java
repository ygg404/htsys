package io.renren.modules.ren.service.impl;


import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;
import io.renren.modules.ren.dao.RenRecordTempVoDao;
import io.renren.modules.ren.dao.RenRecordVoDao;
import io.renren.modules.ren.service.RenRecordTempVoService;
import io.renren.modules.ren.service.RenRecordVoService;
import io.renren.modules.ren.vo.RenRecordTempVoEntity;
import io.renren.modules.ren.vo.RenRecordVoEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service("renRecordTempVoService")
public class RenRecordTempVoServiceImpl extends ServiceImpl<RenRecordTempVoDao, RenRecordTempVoEntity> implements RenRecordTempVoService {

    @Autowired
    private RenRecordVoService renRecordVoService;


    /**
     * 根据用户ID 获取实体类
     */
    @Override
    public RenRecordTempVoEntity selectById(Long userId){
        RenRecordTempVoEntity entity = baseMapper.getRenRecordTempVoById(userId);
        return entity;
    }
}
