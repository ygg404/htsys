package io.renren.modules.finance.dao;

import io.renren.modules.finance.entity.ProjectAccountEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 财务操作表
 * 
 * @author ygg
 * @date 2019-10-31 11:18:50
 */
@Mapper
public interface ProjectAccountDao extends BaseMapper<ProjectAccountEntity> {

    List<String> getBusinessList();
}
