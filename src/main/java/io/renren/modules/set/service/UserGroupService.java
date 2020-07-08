package io.renren.modules.set.service;


import com.baomidou.mybatisplus.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.set.entity.UserGroupEntity;


import java.util.List;
import java.util.Map;

/**
 * 用户ID项目ID表
 *
 * @author ygg
 * @date 2019-11-07 10:55:09
 */
public interface UserGroupService extends IService<UserGroupEntity> {

    PageUtils queryPage(Map<String, Object> params);

    void save(UserGroupEntity entity);

    void update(UserGroupEntity entity);

    void deleteBatch(Long[] roleIds);


    List<UserGroupEntity> ToSelectqueryList();

    UserGroupEntity selectByUID(Long userId);


  //先删后改
   void saveOrUpdate(Long userId,Long WorkgroupId);

   //通过user_id 集合 批量 删除 user_group
   void deleteBatchByUId(Long[] UserIdList);
}

