package io.renren.modules.sys.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.modules.set.service.UserGroupService;

import io.renren.modules.sys.service.SysRoleService;
import io.renren.modules.sys.vo.UserDetailVoEntity;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.sys.dao.SysUserDetailDao;
import io.renren.modules.sys.entity.SysUserDetailEntity;
import io.renren.modules.sys.service.SysUserDetailService;
import org.springframework.transaction.annotation.Transactional;


@Service("sysUserDetailService")
public class SysUserDetailServiceImpl extends ServiceImpl<SysUserDetailDao, SysUserDetailEntity> implements SysUserDetailService {


    @Autowired
    private UserGroupService userGroupService;

    @Autowired
    private SysRoleService sysRoleService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String key = (String)params.get("key");

        Page<SysUserDetailEntity> page = this.selectPage(
                new Query<SysUserDetailEntity>(params).getPage(),
                new EntityWrapper<SysUserDetailEntity>().like(StringUtils.isNotBlank(key),"id", key)
        );

        return new PageUtils(page);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public UserDetailVoEntity queryUserDetail(Long userId){
        UserDetailVoEntity uservo = this.baseMapper.getUserDetailVo(userId);
        // 获取用户对应的工作组
        List<String> groupList = this.baseMapper.getWorkGroupListByUserId(userId);
        // 获取用户对应的角色组
        List<String> roleList = this.baseMapper.getRoleNameListByUserId(userId);
        uservo.setGroupList(groupList);
        uservo.setRoleList(roleList);

        return uservo;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(SysUserDetailEntity entity) {
        this.insert(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(SysUserDetailEntity entity) {
        this.updateById(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteBatch(Long[] roleIds) {
        //删除
        this.deleteBatchIds(Arrays.asList(roleIds));
    }

}