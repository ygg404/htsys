package io.renren.modules.ren.service;

import com.baomidou.mybatisplus.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.ren.entity.RenAttendDaysEntity;

import java.util.Date;
import java.util.Map;

/**
 * 应出勤天数表
 *
 * @author ygg
 * @date 2020-03-10 15:32:12
 */
public interface RenAttendDaysService extends IService<RenAttendDaysEntity> {

    PageUtils queryPage(Map<String, Object> params);

    RenAttendDaysEntity getByDate(String date);

    void save(RenAttendDaysEntity entity);

    void update(RenAttendDaysEntity entity);

    void deleteByDate(Date date);
}

