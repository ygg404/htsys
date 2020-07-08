package io.renren.modules.set.dao;


import com.baomidou.mybatisplus.mapper.BaseMapper;
import io.renren.modules.set.entity.BranchEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 部门图表
 * 
 * @author ygg
 * @date 2020-02-21 15:03:54
 */
@Mapper
public interface BranchDao extends BaseMapper<BranchEntity> {
	
}
