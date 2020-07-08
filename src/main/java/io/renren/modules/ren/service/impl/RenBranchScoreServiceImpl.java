package io.renren.modules.ren.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.ren.dao.RenBranchScoreDao;
import io.renren.modules.ren.entity.RenBranchScoreEntity;
import io.renren.modules.ren.service.RenBranchScoreService;
import org.springframework.transaction.annotation.Transactional;


@Service("renBranchScoreService")
public class RenBranchScoreServiceImpl extends ServiceImpl<RenBranchScoreDao, RenBranchScoreEntity> implements RenBranchScoreService {

    @Override
    public List<RenBranchScoreEntity> queryList(Map<String, Object> params) {
        String key = (String)params.get("key");
        String branchId = (String)params.get("branchId");

        List<RenBranchScoreEntity> list = this.selectList(
                new EntityWrapper<RenBranchScoreEntity>().eq(Strings.isNotBlank(branchId), "branch_id" , branchId )
        );

        return list;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(RenBranchScoreEntity entity) {
        this.insert(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(RenBranchScoreEntity entity) {
        this.updateById(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteBatch(Long[] roleIds) {
        //删除
        this.deleteBatchIds(Arrays.asList(roleIds));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteByBranchId(Long branchId){
        this.delete(new EntityWrapper<RenBranchScoreEntity>().eq("branch_id", branchId));
    }

}