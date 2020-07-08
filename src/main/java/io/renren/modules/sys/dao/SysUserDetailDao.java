package io.renren.modules.sys.dao;

import io.renren.modules.sys.entity.SysUserDetailEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import io.renren.modules.sys.vo.UserDetailVoEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 用户明细表
 * 
 * @author ygg
 * @date 2019-11-30 11:04:55
 */
@Mapper
public interface SysUserDetailDao extends BaseMapper<SysUserDetailEntity> {

    UserDetailVoEntity getUserDetailVo(Long userId);

    List<String> getWorkGroupListByUserId(Long userId);

    List<String> getRoleNameListByUserId(Long userId);
}
