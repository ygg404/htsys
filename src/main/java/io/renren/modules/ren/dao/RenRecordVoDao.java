package io.renren.modules.ren.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import io.renren.modules.project.vo.ProjectVoEntity;
import io.renren.modules.ren.entity.RenRecordEntity;
import io.renren.modules.ren.vo.RenRecordVoEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 职工档案复合表（正式）
 *
 * @author ygg
 * @date 2020-02-11 11:50:15
 */
@Mapper
public interface RenRecordVoDao extends BaseMapper<RenRecordVoEntity> {
    /**
     * 分页查询
     */
    List<RenRecordVoEntity> getRenRecordVoPage(Page<RenRecordVoEntity> pagination, Map<String, Object> params);
    /**
     * 根据用户ID 获取实体类
     */
    RenRecordVoEntity getRenRecordVoById(Long userId);
    /**
     * 查询分数统计表
     */
    List<RenRecordVoEntity> getRecordScoreVoList(Map<String, Object> params);
}
