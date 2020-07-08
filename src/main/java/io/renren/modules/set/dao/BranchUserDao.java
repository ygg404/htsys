package io.renren.modules.set.dao;


import com.baomidou.mybatisplus.mapper.BaseMapper;
import io.renren.modules.set.entity.BranchUserEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 部门与成员关系表
 * 
 * @author ygg
 * @date 2020-02-24 09:58:46
 */
@Mapper
public interface BranchUserDao extends BaseMapper<BranchUserEntity> {
	
}
