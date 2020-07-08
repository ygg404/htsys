package io.renren.modules.ren.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.modules.ren.dao.RenSalaryVoDao;
import io.renren.modules.ren.service.RenSalaryVoService;
import io.renren.modules.ren.vo.RenSalaryVoEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("renSalaryVoService")
public class RenSalaryVoServiceImpl extends ServiceImpl<RenSalaryVoDao, RenSalaryVoEntity> implements RenSalaryVoService {
    /**
     * 查询 工资
     * @return
     */
    @Override
    public List<RenSalaryVoEntity> getSalaryVoList(){
        return baseMapper.getRenSalaryVoList();
    }

    /**
     * 查询 工资设置
     * @return
     */
    @Override
    public List<RenSalaryVoEntity> getSalarySetVoList(){
        return baseMapper.getSalarySetVoList();
    }
}
