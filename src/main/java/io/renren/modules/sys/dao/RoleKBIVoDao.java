package io.renren.modules.sys.dao;


import com.baomidou.mybatisplus.mapper.BaseMapper;
import io.renren.modules.sys.vo.RoleKBIVoEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RoleKBIVoDao extends BaseMapper<RoleKBIVoEntity> {

    /**
     * 岗位与考核项关系表
     * @return
     */
    List<RoleKBIVoEntity> getRoleKBIList();
}
