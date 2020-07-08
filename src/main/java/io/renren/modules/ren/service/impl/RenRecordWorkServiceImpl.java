package io.renren.modules.ren.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.common.utils.MapUtils;
import io.renren.modules.ren.dao.RenRecordWorkDao;
import io.renren.modules.ren.entity.RenRecordEducationEntity;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;


import io.renren.modules.ren.entity.RenRecordWorkEntity;
import io.renren.modules.ren.service.RenRecordWorkService;
import org.springframework.transaction.annotation.Transactional;


@Service("renRecordWorkService")
public class RenRecordWorkServiceImpl extends ServiceImpl<RenRecordWorkDao, RenRecordWorkEntity> implements RenRecordWorkService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String key = (String)params.get("key");

        Page<RenRecordWorkEntity> page = this.selectPage(
                new Query<RenRecordWorkEntity>(params).getPage(),
                new EntityWrapper<RenRecordWorkEntity>().like(StringUtils.isNotBlank(key),"id", key)
        );

        return new PageUtils(page);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<RenRecordWorkEntity> queryListByUserId(Long userId){
        return this.selectList(
                new EntityWrapper<RenRecordWorkEntity>().eq("user_id" , userId)
        );
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteByUserId(Long userId){
        this.deleteByMap(new MapUtils().put("user_id" , userId));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(RenRecordWorkEntity entity) {
        this.insert(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(RenRecordWorkEntity entity) {
        this.updateById(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteBatch(Long[] roleIds) {
        //删除
        this.deleteBatchIds(Arrays.asList(roleIds));
    }

}