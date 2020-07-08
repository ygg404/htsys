package io.renren.modules.ren.service;

import com.baomidou.mybatisplus.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.ren.vo.RenRecordVoEntity;

import java.util.List;
import java.util.Map;

/**
 * 人事档案分页查询
 */
public interface RenRecordVoService extends IService<RenRecordVoEntity> {
    /**
     * 分页查询
     * @param params
     * @return
     */
    PageUtils getRenRecordVoPage(Map<String, Object> params);
    /**
     * 根据用户ID 获取实体类
     */
    RenRecordVoEntity selectById(Long userId);

    /**
     * 查询档案及其关联分数列表
     * @param params
     * @return
     */
    List<RenRecordVoEntity> getRecordScoreVoList(Map<String, Object> params);
}
