package io.renren.modules.ren.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Arrays;
import java.util.Map;
import java.util.List;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.ren.dao.RenKbiAuditDao;
import io.renren.modules.ren.entity.RenKbiAuditEntity;
import io.renren.modules.ren.service.RenKbiAuditService;


@Service("renKbiAuditService")
public class RenKbiAuditServiceImpl extends ServiceImpl<RenKbiAuditDao, RenKbiAuditEntity> implements RenKbiAuditService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String key = (String)params.get("key");

        Page<RenKbiAuditEntity> page = this.selectPage(
                new Query<RenKbiAuditEntity>(params).getPage(),
                new EntityWrapper<RenKbiAuditEntity>().like(StringUtils.isNotBlank(key),"id", key)
        );

        return new PageUtils(page);
    }

    @Override
    public List<RenKbiAuditEntity> queryList(Map<String, Object> params){
        String year = (String)params.get("year");
        String updown = (String)params.get("updown");
        List<RenKbiAuditEntity> list = this.selectList(
                new EntityWrapper<RenKbiAuditEntity>().eq("year", year).eq("updown",updown)
        );
        return list;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(RenKbiAuditEntity entity) {
        this.insert(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(RenKbiAuditEntity entity) {
        this.updateById(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteBatch(Long[] Ids) {
        //删除
        this.deleteBatchIds(Arrays.asList(Ids));
    }

}