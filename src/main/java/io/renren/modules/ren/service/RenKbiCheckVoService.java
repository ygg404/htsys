package io.renren.modules.ren.service;

import com.baomidou.mybatisplus.service.IService;
import io.renren.modules.ren.vo.RenKbiCheckVoEntity;

import java.util.List;
import java.util.Map;

/**
 * 效能分年度考核人员
 *
 * @author ygg
 * @date 2020-07-04 10:33:57
 */
public interface RenKbiCheckVoService extends IService<RenKbiCheckVoEntity> {

    List<RenKbiCheckVoEntity> queryList(Map<String, Object> params);
}

