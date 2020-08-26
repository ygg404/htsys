package io.renren.modules.ren.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import io.renren.modules.ren.vo.RenKbiCheckVoEntity;
import io.renren.modules.ren.vo.RenKbiPersonVoEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 效能分年度考核人员
 * 
 * @author ygg
 * @date 2020-07-04 10:33:57
 */
@Mapper
public interface RenKbiCheckVoDao extends BaseMapper<RenKbiCheckVoEntity> {

    List<RenKbiCheckVoEntity> getKbiCheckVoList(Map<String, Object> params);
}
