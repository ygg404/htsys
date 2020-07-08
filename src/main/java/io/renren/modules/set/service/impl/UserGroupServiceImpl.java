package io.renren.modules.set.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.common.utils.R;
import io.renren.modules.sys.entity.SysRoleEntity;
import io.renren.modules.sys.entity.SysUserEntity;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import io.renren.common.utils.MapUtils;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.set.dao.UserGroupDao;
import io.renren.modules.set.entity.UserGroupEntity;
import io.renren.modules.set.service.UserGroupService;
import org.springframework.transaction.annotation.Transactional;


@Service("userGroupService")
public class UserGroupServiceImpl extends ServiceImpl<UserGroupDao, UserGroupEntity> implements UserGroupService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String key = (String)params.get("key");

        Page<UserGroupEntity> page = this.selectPage(
                new Query<UserGroupEntity>(params).getPage(),
                new EntityWrapper<UserGroupEntity>().like(StringUtils.isNotBlank(key),"id", key)
        );

        return new PageUtils(page);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(UserGroupEntity entity) {
        this.insert(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(UserGroupEntity entity) {
        this.updateById(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteBatch(Long[] roleIds) {
        //删除
        this.deleteBatchIds(Arrays.asList(roleIds));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<UserGroupEntity> ToSelectqueryList(){
        List<UserGroupEntity> list = this.selectList( new EntityWrapper<UserGroupEntity>());
        return list;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public UserGroupEntity selectByUID(Long userid){
        Map<String,Object> params = new HashMap<>();
        params.put("user_id",userid);
        //this.deleteByMap(params);
       List<UserGroupEntity> UGEntity = this.selectByMap(params);
       if(UGEntity.size() != 0) {
           return UGEntity.get(0);
       }
       return null;
    }

    //先删后改
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveOrUpdate(Long userId,Long WorkgroupId){
        //试图查询到数据
         UserGroupEntity SelectEntity = this.selectByUID(userId);
         if(SelectEntity != null){
             //有 删除数据
             this.deleteById(SelectEntity.getId());
         }
         if(WorkgroupId == null){
             return;
         }
         UserGroupEntity OperEntity = new UserGroupEntity();
         OperEntity.setUserId(userId);
         OperEntity.setGroupId(WorkgroupId);
         this.insert(OperEntity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteBatchByUId(Long[] UserIdList){

        for(Long item : UserIdList) {
            this.deleteByMap(new MapUtils().put("user_id", item));
        }
    }


}