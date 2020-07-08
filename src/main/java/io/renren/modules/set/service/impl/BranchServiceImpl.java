package io.renren.modules.set.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.modules.set.dao.BranchDao;
import io.renren.modules.set.entity.BranchEntity;
import io.renren.modules.set.service.BranchService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;
import org.springframework.transaction.annotation.Transactional;


@Service("branchService")
public class BranchServiceImpl extends ServiceImpl<BranchDao, BranchEntity> implements BranchService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String key = (String)params.get("key");

        Page<BranchEntity> page = this.selectPage(
                new Query<BranchEntity>(params).getPage(),
                new EntityWrapper<BranchEntity>().like(StringUtils.isNotBlank(key),"id", key)
        );

        return new PageUtils(page);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<BranchEntity> queryByParentId(Long parentId){
        return this.selectList(
                new EntityWrapper<BranchEntity>().eq("parent_id" , parentId).orderBy("order_num",true)
        );
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(BranchEntity entity) {
        this.insert(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(BranchEntity entity) {
        this.updateById(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteBatch(Long[] roleIds) {
        //删除
        this.deleteBatchIds(Arrays.asList(roleIds));
    }

}