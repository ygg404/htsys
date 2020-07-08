package io.renren.modules.set.service;

import com.baomidou.mybatisplus.service.IService;
import io.renren.modules.set.vo.BranchVoEntity;

import java.util.List;

public interface BranchVoService extends IService<BranchVoEntity> {

    List<BranchVoEntity> getBranchList();

    Long saveBranchVo(BranchVoEntity branchVoEntity);

    BranchVoEntity getEntityById(Long id);
}
