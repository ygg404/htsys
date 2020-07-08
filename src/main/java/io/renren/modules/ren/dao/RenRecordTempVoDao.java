package io.renren.modules.ren.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import io.renren.modules.ren.vo.RenRecordTempVoEntity;
import io.renren.modules.ren.vo.RenRecordVoEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 职工档案复合表（临时）
 *
 * @author ygg
 * @date 2020-02-11 11:50:15
 */
@Mapper
public interface RenRecordTempVoDao extends BaseMapper<RenRecordTempVoEntity> {
    /**
     * 根据用户ID 获取实体类
     */
    RenRecordTempVoEntity getRenRecordTempVoById(Long userId);
}
