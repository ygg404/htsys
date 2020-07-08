package io.renren.modules.ren.service.impl;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;
import io.renren.modules.project.vo.ProjectVoEntity;
import io.renren.modules.ren.dao.RenRecordVoDao;
import io.renren.modules.ren.service.RenRecordVoService;
import io.renren.modules.ren.vo.RenRecordVoEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("renRecordVoService")
public class RenRecordVoServiceImpl extends ServiceImpl<RenRecordVoDao, RenRecordVoEntity> implements RenRecordVoService {

    @Autowired
    private RenRecordVoService renRecordVoService;

    /**
     * 获取人事档案分页列表
     * @param params
     * @return
     */
    @Override
    public PageUtils getRenRecordVoPage(Map<String, Object> params){
        Page<RenRecordVoEntity> pagnation = new Query<RenRecordVoEntity>(params).getPage();
        pagnation = pagnation.setRecords( baseMapper.getRenRecordVoPage(pagnation , params ) );
        return new PageUtils(pagnation);
    }

    /**
     * 根据用户ID 获取实体类
     */
    @Override
    public RenRecordVoEntity selectById(Long userId){
        RenRecordVoEntity entity = baseMapper.getRenRecordVoById(userId);
        return entity;
    }

    @Override
    public List<RenRecordVoEntity> getRecordScoreVoList(Map<String, Object> params){
        List<RenRecordVoEntity> list = baseMapper.getRecordScoreVoList(params);
        return list;
    }
}
