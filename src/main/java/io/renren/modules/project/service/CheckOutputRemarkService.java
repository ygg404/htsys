package io.renren.modules.project.service;

import com.baomidou.mybatisplus.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.project.entity.CheckOutputRemarkEntity;


import java.util.Map;
import java.util.List;

/**
 * 产值核算备注
 *
 * @author ygg
 * @date 2020-09-22 10:48:54
 */
public interface CheckOutputRemarkService extends IService<CheckOutputRemarkEntity> {


    List<CheckOutputRemarkEntity> queryList(String projectNo);

    void save(CheckOutputRemarkEntity entity);

    void update(CheckOutputRemarkEntity entity);

    void deleteByProjectNo(String projectNo);
}

