package io.renren.modules.sys.service;

import com.baomidou.mybatisplus.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.sys.entity.SysUserDetailEntity;
import io.renren.modules.sys.vo.UserDetailVoEntity;

import java.util.Map;

/**
 * 用户明细表
 *
 * @author ygg
 * @date 2019-11-30 11:04:55
 */
public interface SysUserDetailService extends IService<SysUserDetailEntity> {

    PageUtils queryPage(Map<String, Object> params);

    /**
     * 获取当前用户详信息
     * @param userId
     * @return
     */
    UserDetailVoEntity queryUserDetail(Long userId);

    void save(SysUserDetailEntity entity);

    void update(SysUserDetailEntity entity);

    void deleteBatch(Long[] roleIds);
}

