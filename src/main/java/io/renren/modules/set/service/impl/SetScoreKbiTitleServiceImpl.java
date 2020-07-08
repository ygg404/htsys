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

import io.renren.modules.set.dao.SetScoreKbiTitleDao;
import io.renren.modules.set.entity.SetScoreKbiTitleEntity;
import io.renren.modules.set.service.SetScoreKbiTitleService;


@Service("setScoreKbiTitleService")
public class SetScoreKbiTitleServiceImpl extends ServiceImpl<SetScoreKbiTitleDao, SetScoreKbiTitleEntity> implements SetScoreKbiTitleService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String key = (String)params.get("key");

        Page<SetScoreKbiTitleEntity> page = this.selectPage(
                new Query<SetScoreKbiTitleEntity>(params).getPage(),
                new EntityWrapper<SetScoreKbiTitleEntity>().like(StringUtils.isNotBlank(key),"id", key)
        );

        return new PageUtils(page);
    }

    @Override
    public List<SetScoreKbiTitleEntity> queryList(Map<String, Object> params){
        List<SetScoreKbiTitleEntity> list = this.selectList(
                new EntityWrapper<SetScoreKbiTitleEntity>()
        );
        return list;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(SetScoreKbiTitleEntity entity) {
        this.insert(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(SetScoreKbiTitleEntity entity) {
        this.updateById(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteBatch(Long[] Ids) {
        //删除
        this.deleteBatchIds(Arrays.asList(Ids));
    }

}