package io.renren.modules.ren.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.ren.dao.RenAccessDao;
import io.renren.modules.ren.entity.RenAccessEntity;
import io.renren.modules.ren.service.RenAccessService;
import org.springframework.transaction.annotation.Transactional;


@Service("renAccessService")
public class RenAccessServiceImpl extends ServiceImpl<RenAccessDao, RenAccessEntity> implements RenAccessService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String key = (String)params.get("key");

        Page<RenAccessEntity> page = this.selectPage(
                new Query<RenAccessEntity>(params).getPage(),
                new EntityWrapper<RenAccessEntity>().like(StringUtils.isNotBlank(key),"id", key)
        );

        return new PageUtils(page);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<RenAccessEntity> queryList(Map<String, Object> params){
        String key = (String)params.get("key");
        String parentId = (String)params.get("parentId");
        List<RenAccessEntity> list = this.selectList(
                new EntityWrapper<RenAccessEntity>()
                        .like(StringUtils.isNotBlank(key),"access_name",key)
                        .eq(StringUtils.isNotBlank(parentId),"parent_id" ,parentId )
        );
        return list;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(RenAccessEntity entity) {
        this.insert(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(RenAccessEntity entity) {
        this.updateById(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteBatch(Long[] Ids) {
        //删除
        this.deleteBatchIds(Arrays.asList(Ids));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteByParentId(Long parentId){
        HashMap<String,Object> parm = new HashMap<>();
        parm.put("parent_id" , parentId);
        this.deleteByMap( parm );
    }
}