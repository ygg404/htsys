package io.renren.modules.ren.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.ren.dao.RenAccessUserDao;
import io.renren.modules.ren.entity.RenAccessUserEntity;
import io.renren.modules.ren.service.RenAccessUserService;
import org.springframework.transaction.annotation.Transactional;


@Service("renAccessUserService")
public class RenAccessUserServiceImpl extends ServiceImpl<RenAccessUserDao, RenAccessUserEntity> implements RenAccessUserService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String key = (String)params.get("key");

        Page<RenAccessUserEntity> page = this.selectPage(
                new Query<RenAccessUserEntity>(params).getPage(),
                new EntityWrapper<RenAccessUserEntity>().like(StringUtils.isNotBlank(key),"id", key)
        );

        return new PageUtils(page);
    }

    @Override
    public List<RenAccessUserEntity> queryList(Map<String, Object> params){
        String accessId = (String)params.get("accessId");
        List<RenAccessUserEntity> list = this.selectList(
                new EntityWrapper<RenAccessUserEntity>().eq(Strings.isNotBlank(accessId),"access_id",accessId)
        );
        return list;
    }



    @Override
    public List<RenAccessUserEntity> queryByUserId(Long userId){
            List<RenAccessUserEntity> list = this.selectList(new EntityWrapper<RenAccessUserEntity>().eq("user_id" , userId));
            return list;
    }

    @Override
    public void deleteByUserId(Long userId){
        HashMap<String,Object> parms = new HashMap<>();
        parms.put("user_id" , userId);
        this.deleteByMap(parms);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(RenAccessUserEntity entity) {
        this.insert(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(RenAccessUserEntity entity) {
        this.updateById(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteBatch(Long[] roleIds) {
        //删除
        this.deleteBatchIds(Arrays.asList(roleIds));
    }

}