package io.renren.modules.ren.service;

import com.baomidou.mybatisplus.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.ren.entity.RenSalarySetEntity;

import java.util.Map;

/**
 * 基本工资设置
 *
 * @author ygg
 * @date 2020-03-23 16:58:53
 */
public interface RenSalarySetService extends IService<RenSalarySetEntity> {


    void save(RenSalarySetEntity entity);

    void update(RenSalarySetEntity entity);

    void deleteBatch(Long[] Ids);
}

