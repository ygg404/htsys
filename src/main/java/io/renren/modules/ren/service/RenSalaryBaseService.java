package io.renren.modules.ren.service;

import com.baomidou.mybatisplus.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.ren.entity.RenSalaryBaseEntity;
import io.renren.modules.ren.vo.RenRecordVoEntity;
import io.renren.modules.ren.vo.RenSalaryVoEntity;

import java.util.List;
import java.util.Map;

/**
 * 基本工资表
 *
 * @author ygg
 * @date 2020-03-07 11:22:51
 */
public interface RenSalaryBaseService extends IService<RenSalaryBaseEntity> {

    List<RenSalaryBaseEntity> queryList(Map<String, Object> params);

    List<RenSalaryBaseEntity> setSalaryByRecord(List<RenRecordVoEntity> recordVo, List<RenSalaryBaseEntity> salaryVo,Map<String, Object> params);

    void save(RenSalaryBaseEntity entity);

    void update(RenSalaryBaseEntity entity);

    void deleteBatch(Long[] Ids);
}

