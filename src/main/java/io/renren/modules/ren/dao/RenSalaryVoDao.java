package io.renren.modules.ren.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import io.renren.modules.ren.vo.RenSalaryVoEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RenSalaryVoDao extends BaseMapper<RenSalaryVoEntity> {
    /**
     * 查询工资
     */
    List<RenSalaryVoEntity> getRenSalaryVoList();

    /**
     * 查询工资设置
     */
    List<RenSalaryVoEntity> getSalarySetVoList();
}
