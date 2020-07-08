package io.renren.modules.ren.service;

import com.baomidou.mybatisplus.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.ren.entity.RenAttendBonusEntity;

import java.util.Map;

/**
 * 年度全勤奖天数
 *
 * @author ygg
 * @date 2020-03-21 14:24:14
 */
public interface RenAttendBonusService extends IService<RenAttendBonusEntity> {

    void save(RenAttendBonusEntity entity);

    void update(RenAttendBonusEntity entity);

    void deleteBatch(Long[] Ids);
}

