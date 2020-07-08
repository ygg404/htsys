package io.renren.modules.ren.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.ren.dao.RenAttendDaysDao;
import io.renren.modules.ren.entity.RenAttendDaysEntity;
import io.renren.modules.ren.service.RenAttendDaysService;


@Service("renAttendDaysService")
public class RenAttendDaysServiceImpl extends ServiceImpl<RenAttendDaysDao, RenAttendDaysEntity> implements RenAttendDaysService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String key = (String)params.get("key");

        Page<RenAttendDaysEntity> page = this.selectPage(
                new Query<RenAttendDaysEntity>(params).getPage(),
                new EntityWrapper<RenAttendDaysEntity>().like(StringUtils.isNotBlank(key),"id", key)
        );

        return new PageUtils(page);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public RenAttendDaysEntity getByDate(String date){
        RenAttendDaysEntity entity = this.selectOne(
                new EntityWrapper<RenAttendDaysEntity>().and("LEFT( CAST(date AS date) , 7)  = {0}",date)
        );
        return entity;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(RenAttendDaysEntity entity) {
        this.insert(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(RenAttendDaysEntity entity) {
        this.updateById(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteByDate(Date date) {
        //删除
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");//设置日期格式
        this.delete( new EntityWrapper<RenAttendDaysEntity>().and( "LEFT( CAST(date AS date) , 7)  = {0}",sdf.format(date) ));
    }

}