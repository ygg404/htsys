package io.renren.modules.project.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.modules.project.dao.CheckErrorDao;
import io.renren.modules.project.entity.CheckErrorEntity;
import io.renren.modules.project.service.CheckErrorService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Arrays;
import java.util.Map;
import java.util.List;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;




@Service("checkErrorService")
public class CheckErrorServiceImpl extends ServiceImpl<CheckErrorDao, CheckErrorEntity> implements CheckErrorService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String key = (String)params.get("key");

        Page<CheckErrorEntity> page = this.selectPage(
                new Query<CheckErrorEntity>(params).getPage(),
                new EntityWrapper<CheckErrorEntity>().like(StringUtils.isNotBlank(key),"id", key)
        );

        return new PageUtils(page);
    }

    @Override
    public List<CheckErrorEntity> queryList(Map<String, Object> params){
        List<CheckErrorEntity> list = this.selectList(
                new EntityWrapper<CheckErrorEntity>()
        );
        return list;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(CheckErrorEntity entity) {
        this.insert(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(CheckErrorEntity entity) {
        this.updateById(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteBatch(Long[] Ids) {
        //删除
        this.deleteBatchIds(Arrays.asList(Ids));
    }

}