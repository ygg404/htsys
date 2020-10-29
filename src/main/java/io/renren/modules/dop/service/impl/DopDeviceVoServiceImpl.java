package io.renren.modules.dop.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.modules.dop.dao.DopDeviceVoDao;
import io.renren.modules.dop.service.DopDeviceVoService;
import io.renren.modules.dop.vo.DeviceVoEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("dopDeviceVoService")
public class DopDeviceVoServiceImpl extends ServiceImpl<DopDeviceVoDao, DeviceVoEntity> implements DopDeviceVoService {

    @Override
    public List<DeviceVoEntity> getDeviceLendList(Long deviceId){
        return this.baseMapper.getDeviceLendList(deviceId);
    }
}
