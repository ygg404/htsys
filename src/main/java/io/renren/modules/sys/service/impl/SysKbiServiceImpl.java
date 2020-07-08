package io.renren.modules.sys.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.sys.dao.SysKbiDao;
import io.renren.modules.sys.entity.SysKbiEntity;
import io.renren.modules.sys.service.SysKbiService;


@Service("sysKbiService")
public class SysKbiServiceImpl extends ServiceImpl<SysKbiDao, SysKbiEntity> implements SysKbiService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String key = (String)params.get("key");

        Page<SysKbiEntity> page = this.selectPage(
                new Query<SysKbiEntity>(params).getPage(),
                new EntityWrapper<SysKbiEntity>().like(StringUtils.isNotBlank(key),"id", key)
        );

        return new PageUtils(page);
    }

    @Override
    public List<SysKbiEntity> queryList(Map<String, Object> params){
        List<SysKbiEntity> list = this.selectList(
                new EntityWrapper<SysKbiEntity>().orderBy("order_num",true)
        );
        return list;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(SysKbiEntity entity) {
        // 获取最大的排序号
        Long max = 0L;
        List<SysKbiEntity> list = this.queryList(null);
        for (SysKbiEntity sysKbiEntity : list){
            if(sysKbiEntity.getOrderNum() > max){
                max = sysKbiEntity.getOrderNum();
            }
        }
        entity.setOrderNum(max + 1);

        this.insert(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(SysKbiEntity entity) {
        this.updateById(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteBatch(Long[] Ids) {
        //删除
        this.deleteBatchIds(Arrays.asList(Ids));
    }

}