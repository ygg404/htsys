package io.renren.modules.ren.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.common.utils.MapUtils;
import io.renren.modules.ren.dao.RenRecordWorkTempDao;
import io.renren.modules.ren.entity.RenRecordWorkEntity;
import io.renren.modules.ren.entity.RenRecordWorkTempEntity;
import io.renren.modules.ren.service.RenRecordWorkTempService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import org.springframework.transaction.annotation.Transactional;


@Service("renRecordWorkTempService")
public class RenRecordWorkTempServiceImpl extends ServiceImpl<RenRecordWorkTempDao, RenRecordWorkTempEntity> implements RenRecordWorkTempService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String key = (String)params.get("key");

        Page<RenRecordWorkTempEntity> page = this.selectPage(
                new Query<RenRecordWorkTempEntity>(params).getPage(),
                new EntityWrapper<RenRecordWorkTempEntity>().like(StringUtils.isNotBlank(key),"id", key)
        );

        return new PageUtils(page);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<RenRecordWorkTempEntity> queryListByUserId(Long userId){
        return this.selectList(
                new EntityWrapper<RenRecordWorkTempEntity>().eq("user_id" , userId)
        );
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteByUserId(Long userId){
        this.deleteByMap(new MapUtils().put("user_id" , userId));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(RenRecordWorkTempEntity entity) {
        this.insert(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(RenRecordWorkTempEntity entity) {
        this.updateById(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteBatch(Long[] roleIds) {
        //删除
        this.deleteBatchIds(Arrays.asList(roleIds));
    }

}