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

import io.renren.modules.dop.dao.DopDeviceDao;
import io.renren.modules.dop.entity.DopDeviceEntity;
import io.renren.modules.dop.service.DopDeviceService;


@Service("dopDeviceService")
public class DopDeviceServiceImpl extends ServiceImpl<DopDeviceDao, DopDeviceEntity> implements DopDeviceService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String key = (String)params.get("key");

        Page<DopDeviceEntity> page = this.selectPage(
                new Query<DopDeviceEntity>(params).getPage(),
                new EntityWrapper<DopDeviceEntity>().like(StringUtils.isNotBlank(key),"device_name", key)
                        .or().like(StringUtils.isNotBlank(key),"factory_num", key)
                        .or().like(StringUtils.isNotBlank(key),"factory_name", key)
        );

        return new PageUtils(page);
    }

    @Override
    public DopDeviceEntity selectByNum(Map<String, Object> params){
        String factoryNum = (String)params.get("factoryNum");

        DopDeviceEntity entity = this.selectOne(new EntityWrapper<DopDeviceEntity>().eq("factory_num" , factoryNum));
        return entity;
    }

    @Override
    public List<DopDeviceEntity> queryList(Map<String, Object> params){
        List<DopDeviceEntity> list = this.selectList(
                new EntityWrapper<DopDeviceEntity>()
        );
        return list;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(DopDeviceEntity entity) {
        this.insert(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(DopDeviceEntity entity) {
        this.updateById(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteBatch(Long[] Ids) {
        //删除
        this.deleteBatchIds(Arrays.asList(Ids));
    }

}