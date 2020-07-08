package io.renren.modules.project.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.modules.project.dao.BackWorkDao;
import io.renren.modules.project.entity.BackWorkEntity;
import io.renren.modules.project.service.BackWorkService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;
import org.springframework.transaction.annotation.Transactional;


@Service("backworkService")
public class BackWorkServiceImpl extends ServiceImpl<BackWorkDao, BackWorkEntity> implements BackWorkService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String key = (String)params.get("key");

        Page<BackWorkEntity> page = this.selectPage(
                new Query<BackWorkEntity>(params).getPage(),
                new EntityWrapper<BackWorkEntity>().like(StringUtils.isNotBlank(key),"id", key)
        );

        return new PageUtils(page);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<BackWorkEntity> queryByProjectNo(String projectNo){
        List<BackWorkEntity> list = this.selectList(
                new EntityWrapper<BackWorkEntity>().eq("project_no", projectNo)
        );
        return list;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(BackWorkEntity entity) {
        this.insert(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(BackWorkEntity entity) {
        this.updateById(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteBatch(Long[] roleIds) {
        //删除
        this.deleteBatchIds(Arrays.asList(roleIds));
    }

}