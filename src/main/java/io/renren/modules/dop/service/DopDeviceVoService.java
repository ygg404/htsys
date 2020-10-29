package io.renren.modules.dop.service;

import com.baomidou.mybatisplus.service.IService;
import io.renren.modules.dop.vo.DeviceVoEntity;

import java.util.List;

public interface DopDeviceVoService extends IService<DeviceVoEntity> {

    List<DeviceVoEntity> getDeviceLendList(Long deviceId);
}
