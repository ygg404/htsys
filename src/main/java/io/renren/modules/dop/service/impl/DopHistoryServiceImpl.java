package io.renren.modules.dop.service.impl;

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

import io.renren.modules.dop.dao.DopHistoryDao;
import io.renren.modules.dop.entity.DopHistoryEntity;
import io.renren.modules.dop.service.DopHistoryService;


@Service("dopHistoryService")
public class DopHistoryServiceImpl extends ServiceImpl<DopHistoryDao, DopHistoryEntity> implements DopHistoryService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String deviceId = (String)params.get("deviceId");
        String key = (String)params.get("key");

        Page<DopHistoryEntity> page = this.selectPage(
                new Query<DopHistoryEntity>(params).getPage(),
                new EntityWrapper<DopHistoryEntity>().eq("device_id", deviceId)
                        .like(StringUtils.isNotBlank(key),"borrower_name", key)
                        .like(StringUtils.isNotBlank(key),"lender_name", key)
                        .like(StringUtils.isNotBlank(key),"device_name", key)
        );

        return new PageUtils(page);
    }

    @Override
    public List<DopHistoryEntity> queryList(Map<String, Object> params){
        List<DopHistoryEntity> list = this.selectList(
                new EntityWrapper<DopHistoryEntity>()
        );
        return list;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(DopHistoryEntity entity) {
        this.insert(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(DopHistoryEntity entity) {
        this.updateById(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteBatch(Long[] Ids) {
        //删除
        this.deleteBatchIds(Arrays.asList(Ids));
    }

}