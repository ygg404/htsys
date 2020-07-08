package io.renren.modules.ren.service;

import com.baomidou.mybatisplus.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.ren.entity.RenAttendEntity;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 出勤表
 *
 * @author ygg
 * @date 2020-03-10 10:10:43
 */
public interface RenAttendService extends IService<RenAttendEntity> {

    List<RenAttendEntity> queryList(Map<String, Object> params);

    void deleteByDate(Date date);

    void save(RenAttendEntity entity);

    void update(RenAttendEntity entity);

    void deleteBatch(Long[] Ids);
}

