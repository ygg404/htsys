package io.renren.modules.set.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.modules.ren.service.RenRecordVoService;
import io.renren.modules.ren.vo.RenRecordVoEntity;
import io.renren.modules.set.dao.BranchUserDao;
import io.renren.modules.set.dao.BranchVoDao;
import io.renren.modules.set.entity.BranchUserEntity;
import io.renren.modules.set.service.BranchUserService;
import io.renren.modules.set.service.BranchVoService;
import io.renren.modules.set.vo.BranchVoEntity;
import io.renren.modules.sys.entity.SysUserEntity;
import io.renren.modules.sys.service.SysUserService;
import io.renren.modules.sys.vo.UserVoEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service("branchVoService")
public class BranchVoServiceImpl extends ServiceImpl<BranchVoDao, BranchVoEntity> implements BranchVoService {

    @Autowired
    public SysUserService sysUserService;

    @Autowired
    public BranchUserService branchUserService;

    @Autowired
    public RenRecordVoService renRecordVoService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<BranchVoEntity> getBranchList(){
        List<BranchVoEntity> list = baseMapper.getBranchVoList();
        for(BranchVoEntity voEntity: list){
            List<UserVoEntity> userEntityList = new ArrayList<>();
            // 获取该部门所有 成员信息
            List<BranchUserEntity> buList = branchUserService.getListByBranchId(voEntity.getid());
            List<RenRecordVoEntity> renvoList = new ArrayList<>();
            for(BranchUserEntity branchUserEntity : buList){
                RenRecordVoEntity renvo = renRecordVoService.selectById( branchUserEntity.getuserId() );
                renvoList.add(renvo);
            }
            voEntity.setRecordVoList(renvoList);
        }
        return list;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long saveBranchVo(BranchVoEntity branchVoEntity){
        return  this.baseMapper.insertBranchVo(branchVoEntity);
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public BranchVoEntity getEntityById(Long id){
        return this.baseMapper.getEntityById(id);
    }
}
