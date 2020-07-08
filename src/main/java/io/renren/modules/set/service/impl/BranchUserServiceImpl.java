package io.renren.modules.set.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.modules.set.dao.BranchUserDao;
import io.renren.modules.set.entity.BranchUserEntity;
import io.renren.modules.set.service.BranchUserService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;
import org.springframework.transaction.annotation.Transactional;


@Service("sysBranchUserService")
public class BranchUserServiceImpl extends ServiceImpl<BranchUserDao, BranchUserEntity> implements BranchUserService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String key = (String)params.get("key");

        Page<BranchUserEntity> page = this.selectPage(
                new Query<BranchUserEntity>(params).getPage(),
                new EntityWrapper<BranchUserEntity>().like(StringUtils.isNotBlank(key),"id", key)
        );

        return new PageUtils(page);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<BranchUserEntity> getListByBranchId(Long branchId){
        List<BranchUserEntity> list = this.selectList(
                new EntityWrapper<BranchUserEntity>().eq("branch_id" , branchId)
        );
        return list;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteByBranchId(Long branchId){
        if(branchId != null){
            this.delete( new EntityWrapper<BranchUserEntity>().eq("branch_id" , branchId));
        }
    }

    // 通过用户Id 删除 部门与用户的关系列表
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteByUserId(Long userId){
        if(userId != null){
            this.delete( new EntityWrapper<BranchUserEntity>().eq("user_id" , userId));
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(BranchUserEntity entity) {
        this.insert(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(BranchUserEntity entity) {
        this.updateById(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteBatch(Long[] roleIds) {
        //删除
        this.deleteBatchIds(Arrays.asList(roleIds));
    }

}