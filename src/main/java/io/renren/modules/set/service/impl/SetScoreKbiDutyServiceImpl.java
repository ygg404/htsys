package io.renren.modules.set.service.impl;

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

import io.renren.modules.set.dao.SetScoreKbiDutyDao;
import io.renren.modules.set.entity.SetScoreKbiDutyEntity;
import io.renren.modules.set.service.SetScoreKbiDutyService;


@Service("setScoreKbiDutyService")
public class SetScoreKbiDutyServiceImpl extends ServiceImpl<SetScoreKbiDutyDao, SetScoreKbiDutyEntity> implements SetScoreKbiDutyService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String key = (String)params.get("key");

        Page<SetScoreKbiDutyEntity> page = this.selectPage(
                new Query<SetScoreKbiDutyEntity>(params).getPage(),
                new EntityWrapper<SetScoreKbiDutyEntity>().like(StringUtils.isNotBlank(key),"id", key)
        );

        return new PageUtils(page);
    }

    @Override
    public List<SetScoreKbiDutyEntity> queryList(Map<String, Object> params){
        List<SetScoreKbiDutyEntity> list = this.selectList(
                new EntityWrapper<SetScoreKbiDutyEntity>()
        );
        return list;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(SetScoreKbiDutyEntity entity) {
        this.insert(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(SetScoreKbiDutyEntity entity) {
        this.updateById(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteBatch(Long[] Ids) {
        //删除
        this.deleteBatchIds(Arrays.asList(Ids));
    }

}