package io.renren.modules.ren.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.ren.dao.RenBranchBaseDao;
import io.renren.modules.ren.entity.RenBranchBaseEntity;
import io.renren.modules.ren.service.RenBranchBaseService;
import org.springframework.transaction.annotation.Transactional;


@Service("renBranchBaseService")
public class RenBranchBaseServiceImpl extends ServiceImpl<RenBranchBaseDao, RenBranchBaseEntity> implements RenBranchBaseService {

    @Override
    public List<RenBranchBaseEntity> queryList(Map<String, Object> params) {
        String key = (String)params.get("key");

        List<RenBranchBaseEntity> list = this.selectList(
                new EntityWrapper<RenBranchBaseEntity>().like(StringUtils.isNotBlank(key),"id", key)
        );

        return list;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(RenBranchBaseEntity entity) {
        this.insert(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(RenBranchBaseEntity entity) {
        this.updateById(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteBatch(Long[] roleIds) {
        //删除
        this.deleteBatchIds(Arrays.asList(roleIds));
    }

}