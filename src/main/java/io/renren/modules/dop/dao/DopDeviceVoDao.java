package io.renren.modules.dop.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import io.renren.modules.dop.vo.DeviceVoEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 仪器实体类
 */
@Mapper
public interface DopDeviceVoDao extends BaseMapper<DeviceVoEntity> {

    List<DeviceVoEntity> getDeviceLendList(Long deviceId);
}
