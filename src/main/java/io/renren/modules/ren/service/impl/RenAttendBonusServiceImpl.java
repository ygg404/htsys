package io.renren.modules.ren.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Arrays;


import io.renren.modules.ren.dao.RenAttendBonusDao;
import io.renren.modules.ren.entity.RenAttendBonusEntity;
import io.renren.modules.ren.service.RenAttendBonusService;


@Service("renAttendBonusService")
public class RenAttendBonusServiceImpl extends ServiceImpl<RenAttendBonusDao, RenAttendBonusEntity> implements RenAttendBonusService {


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(RenAttendBonusEntity entity) {
        this.insert(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(RenAttendBonusEntity entity) {
        this.updateById(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteBatch(Long[] Ids) {
        //删除
        this.deleteBatchIds(Arrays.asList(Ids));
    }

}