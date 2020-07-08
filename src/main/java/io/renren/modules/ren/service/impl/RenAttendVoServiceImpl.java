package io.renren.modules.ren.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.modules.ren.dao.RenAttendVoDao;
import io.renren.modules.ren.service.RenAttendVoService;
import io.renren.modules.ren.vo.RenAttendVoEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("renAttendVoService")
public class RenAttendVoServiceImpl extends ServiceImpl<RenAttendVoDao, RenAttendVoEntity> implements RenAttendVoService {

    @Override
    public List<RenAttendVoEntity> queryRenAttendVoList(Map<String, Object> params){
        return this.baseMapper.getRenAttendVoList(params);
    }

    @Override
    public List<RenAttendVoEntity> queryBranchUserList(){
        return this.baseMapper.getBranchUserList();
    }
}
