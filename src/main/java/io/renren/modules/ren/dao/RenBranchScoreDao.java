package io.renren.modules.ren.dao;

import io.renren.modules.ren.entity.RenBranchScoreEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 部门与产值的考核分数
 * 
 * @author ygg
 * @date 2020-03-05 10:58:38
 */
@Mapper
public interface RenBranchScoreDao extends BaseMapper<RenBranchScoreEntity> {
	
}
