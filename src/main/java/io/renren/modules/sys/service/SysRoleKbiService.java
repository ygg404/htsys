package io.renren.modules.sys.service;

import com.baomidou.mybatisplus.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.sys.entity.SysRoleKbiEntity;
import io.renren.modules.sys.vo.RoleKBIVoEntity;

import java.util.List;
import java.util.Map;

/**
 * 效能考核项设置
 *
 * @author ygg
 * @date 2020-05-15 10:26:46
 */
public interface SysRoleKbiService extends IService<SysRoleKbiEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<SysRoleKbiEntity> queryList();

    List<RoleKBIVoEntity> queryRoleKBIList();

    void save(SysRoleKbiEntity entity);

    void update(SysRoleKbiEntity entity);

    void deleteBatch(Long[] Ids);
}

