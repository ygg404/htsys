package io.renren.modules.ren.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.ren.dao.RenAttendDao;
import io.renren.modules.ren.entity.RenAttendEntity;
import io.renren.modules.ren.service.RenAttendService;


@Service("renAttendService")
public class RenAttendServiceImpl extends ServiceImpl<RenAttendDao, RenAttendEntity> implements RenAttendService {

    @Override
    public List<RenAttendEntity> queryList(Map<String, Object> params) {
        String attendTime = (String)params.get("attendTime");
        List<RenAttendEntity> list = this.selectList(
                new EntityWrapper<RenAttendEntity>().and(StringUtils.isNotBlank(attendTime) , "LEFT( CAST(attend_time AS date) , 7)  = {0}",attendTime)
        );
        return list;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteByDate(Date attendTime) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");//设置日期格式
        if (attendTime != null) {
            this.delete(new EntityWrapper<RenAttendEntity>().and("LEFT( CAST(attend_time AS date) , 7)  = {0}",
                    sdf.format(attendTime)) );
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(RenAttendEntity entity) {
        this.insert(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(RenAttendEntity entity) {
        this.updateById(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteBatch(Long[] Ids) {
        //删除
        this.deleteBatchIds(Arrays.asList(Ids));
    }

}