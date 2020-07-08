package io.renren.modules.ren.service;

import com.baomidou.mybatisplus.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.ren.vo.RenRecordTempVoEntity;
import io.renren.modules.ren.vo.RenRecordVoEntity;

import java.util.Map;

/**
 * 人事档案查询 （临时）
 */
public interface RenRecordTempVoService extends IService<RenRecordTempVoEntity> {

    /**
     * 根据用户ID 获取实体类
     */
    RenRecordTempVoEntity selectById(Long userId);
}
