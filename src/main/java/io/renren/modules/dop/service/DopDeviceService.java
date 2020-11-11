package io.renren.modules.dop.service;

import com.baomidou.mybatisplus.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.dop.entity.DopDeviceEntity;

import java.util.Map;
import java.util.List;

/**
 * 仪器表
 *
 * @author ygg
 * @date 2020-09-07 10:28:20
 */
public interface DopDeviceService extends IService<DopDeviceEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<DopDeviceEntity> queryList(Map<String, Object> params);

    DopDeviceEntity selectByNum(Map<String, Object> params);

    void save(DopDeviceEntity entity);

    void update(DopDeviceEntity entity);

    void deleteBatch(Long[] Ids);
}

