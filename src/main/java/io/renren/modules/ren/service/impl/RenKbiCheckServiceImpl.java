package io.renren.modules.ren.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.modules.ren.dao.RenKbiCheckDao;
import io.renren.modules.ren.entity.RenKbiCheckEntity;
import io.renren.modules.ren.service.RenKbiCheckService;
import io.renren.modules.sys.entity.SysUserEntity;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Arrays;
import java.util.Map;
import java.util.List;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;




@Service("renKbiCheckService")
public class RenKbiCheckServiceImpl extends ServiceImpl<RenKbiCheckDao, RenKbiCheckEntity> implements RenKbiCheckService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String key = (String)params.get("key");

        Page<RenKbiCheckEntity> page = this.selectPage(
                new Query<RenKbiCheckEntity>(params).getPage(),
                new EntityWrapper<RenKbiCheckEntity>().like(StringUtils.isNotBlank(key),"id", key)
        );

        return new PageUtils(page);
    }

    @Override
    public List<RenKbiCheckEntity> queryList(Map<String, Object> params){
        String year = (String)params.get("year");
        String month = (String)params.get("month");

        List<RenKbiCheckEntity> list = this.selectList(
                new EntityWrapper<RenKbiCheckEntity>().eq("year",year).eq("month",month)
        );
        return list;
    }

    @Override
    public RenKbiCheckEntity queryByParams(Map<String, Object> params){
        String year = (String)params.get("year");
        String month = (String)params.get("month");
        // 获取当前用户Id
        Long curUserId =  ((SysUserEntity) SecurityUtils.getSubject().getPrincipal()).getUserId();

        RenKbiCheckEntity entity = this.selectOne(new EntityWrapper<RenKbiCheckEntity>()
                .eq("year",year).eq("month",month).eq("user_id",curUserId));
        return entity;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(RenKbiCheckEntity entity) {
        this.insert(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(RenKbiCheckEntity entity) {
        this.updateById(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteBatch(Long[] Ids) {
        //删除
        this.deleteBatchIds(Arrays.asList(Ids));
    }

}