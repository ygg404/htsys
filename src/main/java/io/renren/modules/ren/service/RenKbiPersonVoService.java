package io.renren.modules.ren.service;

import com.baomidou.mybatisplus.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.ren.entity.RenKbiPersonEntity;
import io.renren.modules.ren.vo.RenKbiPersonVoEntity;

import java.util.List;
import java.util.Map;

/**
 * 效能分年度参评人员
 *
 * @author ygg
 * @date 2020-07-04 10:33:57
 */
public interface RenKbiPersonVoService extends IService<RenKbiPersonVoEntity> {

    List<RenKbiPersonVoEntity> queryList(Map<String, Object> params);
}

