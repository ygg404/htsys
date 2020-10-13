package io.renren.modules.ren.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.common.utils.MapUtils;
import io.renren.modules.sys.entity.SysUserRoleEntity;
import io.renren.modules.sys.service.SysUserRoleService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Arrays;
import java.util.Map;
import java.util.List;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.ren.dao.RenKbiPersonDao;
import io.renren.modules.ren.entity.RenKbiPersonEntity;
import io.renren.modules.ren.service.RenKbiPersonService;


@Service("renKbiPersonService")
public class RenKbiPersonServiceImpl extends ServiceImpl<RenKbiPersonDao, RenKbiPersonEntity> implements RenKbiPersonService {

    @Autowired
    private SysUserRoleService sysUserRoleService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String key = (String)params.get("key");

        Page<RenKbiPersonEntity> page = this.selectPage(
                new Query<RenKbiPersonEntity>(params).getPage(),
                new EntityWrapper<RenKbiPersonEntity>().like(StringUtils.isNotBlank(key),"id", key)
        );

        return new PageUtils(page);
    }

    @Override
    public List<RenKbiPersonEntity> queryList(Map<String, Object> params){
        String year = (String) params.get("year");
        String month = (String) params.get("month");
        List<RenKbiPersonEntity> list = this.selectList(
                new EntityWrapper<RenKbiPersonEntity>().eq("year", year)
                .and().eq("month", month)
        );
        return list;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(RenKbiPersonEntity entity) {
        this.insert(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(RenKbiPersonEntity entity) {
        this.updateById(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteBatch(Long[] Ids) {
        //删除
        this.deleteBatchIds(Arrays.asList(Ids));
    }

}