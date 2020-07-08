package io.renren.modules.project.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.project.dao.CheckOutputTempDao;
import io.renren.modules.project.entity.CheckOutputTempEntity;
import io.renren.modules.project.service.CheckOutputTempService;
import org.springframework.transaction.annotation.Transactional;


@Service("checkOutputTempService")
public class CheckOutputTempServiceImpl extends ServiceImpl<CheckOutputTempDao, CheckOutputTempEntity> implements CheckOutputTempService {

    @Override
    public List<CheckOutputTempEntity> queryList(Map<String, Object> params) {
        String projectNo = (String)params.get("projectNo");

        List<CheckOutputTempEntity> list = this.selectList(
                new EntityWrapper<CheckOutputTempEntity>().like(StringUtils.isNotBlank(projectNo),"project_no", projectNo)
        );

        return list;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(CheckOutputTempEntity entity) {
        this.insert(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(CheckOutputTempEntity entity) {
        this.updateById(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteBatch(Long[] roleIds) {
        //删除
        this.deleteBatchIds(Arrays.asList(roleIds));
    }

}