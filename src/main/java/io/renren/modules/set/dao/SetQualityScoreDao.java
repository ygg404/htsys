package io.renren.modules.set.dao;

import io.renren.modules.set.entity.SetQualityScoreEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 质量评分表设置
 * 
 * @author ygg
 * @date 2020-08-14 10:17:40
 */
@Mapper
public interface SetQualityScoreDao extends BaseMapper<SetQualityScoreEntity> {

    List<String> getFileList();

    List<SetQualityScoreEntity> getFileNoList();
}
