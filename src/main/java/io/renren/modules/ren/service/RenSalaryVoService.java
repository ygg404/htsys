package io.renren.modules.ren.service;


import com.baomidou.mybatisplus.service.IService;
import io.renren.modules.ren.vo.RenAttendVoEntity;
import io.renren.modules.ren.vo.RenSalaryVoEntity;

import java.util.List;
import java.util.Map;

/**
 * 出勤表复合类查询
 */
public interface RenSalaryVoService extends IService<RenSalaryVoEntity> {
    /**
     * 查询 工资
     * @return
     */
    List<RenSalaryVoEntity> getSalaryVoList();

    /**
     * 查询 工资设置
     * @return
     */
    List<RenSalaryVoEntity> getSalarySetVoList();
}
