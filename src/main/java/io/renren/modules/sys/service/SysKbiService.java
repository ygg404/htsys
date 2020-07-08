package io.renren.modules.sys.service;

import com.baomidou.mybatisplus.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.sys.entity.SysKbiEntity;

import java.util.List;
import java.util.Map;

/**
 * 效能考核项
 *
 * @author ygg
 * @date 2020-05-15 10:38:15
 */
public interface SysKbiService extends IService<SysKbiEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<SysKbiEntity> queryList(Map<String, Object> params);

    void save(SysKbiEntity entity);

    void update(SysKbiEntity entity);

    void deleteBatch(Long[] Ids);
}

