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

import io.renren.modules.set.dao.SetScoreKbiDao;
import io.renren.modules.set.entity.SetScoreKbiEntity;
import io.renren.modules.set.service.SetScoreKbiService;


@Service("setScoreKbiService")
public class SetScoreKbiServiceImpl extends ServiceImpl<SetScoreKbiDao, SetScoreKbiEntity> implements SetScoreKbiService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String key = (String)params.get("key");

        Page<SetScoreKbiEntity> page = this.selectPage(
                new Query<SetScoreKbiEntity>(params).getPage(),
                new EntityWrapper<SetScoreKbiEntity>().like(StringUtils.isNotBlank(key),"id", key)
        );

        return new PageUtils(page);
    }

    @Override
    public List<SetScoreKbiEntity> queryList(Map<String, Object> params){
        List<SetScoreKbiEntity> list = this.selectList(
                new EntityWrapper<SetScoreKbiEntity>()
        );
        return list;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(SetScoreKbiEntity entity) {
        this.insert(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(SetScoreKbiEntity entity) {
        this.updateById(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteBatch(Long[] Ids) {
        //删除
        this.deleteBatchIds(Arrays.asList(Ids));
    }

}