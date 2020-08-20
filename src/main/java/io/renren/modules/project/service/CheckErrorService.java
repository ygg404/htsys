package io.renren.modules.project.service;

import com.baomidou.mybatisplus.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.project.entity.CheckErrorEntity;


import java.util.Map;
import java.util.List;

/**
 * 质检报告误差
 *
 * @author ygg
 * @date 2020-08-19 17:13:37
 */
public interface CheckErrorService extends IService<CheckErrorEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<CheckErrorEntity> queryList(Map<String, Object> params);

    void save(CheckErrorEntity entity);

    void update(CheckErrorEntity entity);

    void deleteBatch(Long[] Ids);
}

