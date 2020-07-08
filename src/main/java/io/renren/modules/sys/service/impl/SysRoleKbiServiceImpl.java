package io.renren.modules.sys.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.modules.sys.dao.RoleKBIVoDao;
import io.renren.modules.sys.vo.RoleKBIVoEntity;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.sys.dao.SysRoleKbiDao;
import io.renren.modules.sys.entity.SysRoleKbiEntity;
import io.renren.modules.sys.service.SysRoleKbiService;


@Service("sysRoleKbiService")
public class SysRoleKbiServiceImpl extends ServiceImpl<SysRoleKbiDao, SysRoleKbiEntity> implements SysRoleKbiService {

    @Autowired
    public RoleKBIVoDao roleKBIVoDao;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String key = (String)params.get("key");

        Page<SysRoleKbiEntity> page = this.selectPage(
                new Query<SysRoleKbiEntity>(params).getPage(),
                new EntityWrapper<SysRoleKbiEntity>().like(StringUtils.isNotBlank(key),"id", key)
        );

        return new PageUtils(page);
    }

    @Override
    public List<SysRoleKbiEntity> queryList(){
        List<SysRoleKbiEntity> list = this.selectList(
                new EntityWrapper<SysRoleKbiEntity>().orderBy("role_id" , true)
        );
        return list;
    }

    @Override
    public List<RoleKBIVoEntity> queryRoleKBIList(){
        List<RoleKBIVoEntity> list = roleKBIVoDao.getRoleKBIList();
        return list;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(SysRoleKbiEntity entity) {
        this.insert(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(SysRoleKbiEntity entity) {
        this.updateById(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteBatch(Long[] Ids) {
        //删除
        this.deleteBatchIds(Arrays.asList(Ids));
    }

}