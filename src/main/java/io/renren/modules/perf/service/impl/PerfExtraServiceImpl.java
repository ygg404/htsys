package io.renren.modules.perf.service.impl;

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

import io.renren.modules.perf.dao.PerfExtraDao;
import io.renren.modules.perf.entity.PerfExtraEntity;
import io.renren.modules.perf.service.PerfExtraService;


@Service("perfExtraService")
public class PerfExtraServiceImpl extends ServiceImpl<PerfExtraDao, PerfExtraEntity> implements PerfExtraService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String key = (String)params.get("key");

        Page<PerfExtraEntity> page = this.selectPage(
                new Query<PerfExtraEntity>(params).getPage(),
                new EntityWrapper<PerfExtraEntity>().like(StringUtils.isNotBlank(key),"id", key)
        );

        return new PageUtils(page);
    }

    @Override
    public List<PerfExtraEntity> queryList(Map<String, Object> params){
        List<PerfExtraEntity> list = this.selectList(
                new EntityWrapper<PerfExtraEntity>().orderBy("extra_Type",true).orderBy("id",true)
        );
        return list;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(PerfExtraEntity entity) {
        this.insert(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(PerfExtraEntity entity) {
        this.updateById(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteBatch(Long[] Ids) {
        //删除
        this.deleteBatchIds(Arrays.asList(Ids));
    }

}