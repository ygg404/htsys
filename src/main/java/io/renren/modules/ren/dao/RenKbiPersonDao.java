package io.renren.modules.ren.dao;

import io.renren.modules.ren.entity.RenKbiPersonEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 效能分年度参评人员
 * 
 * @author ygg
 * @date 2020-07-04 10:33:57
 */
@Mapper
public interface RenKbiPersonDao extends BaseMapper<RenKbiPersonEntity> {

}
