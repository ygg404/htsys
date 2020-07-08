package io.renren.modules.ren.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Arrays;
import java.util.Map;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.ren.dao.RenSalarySetDao;
import io.renren.modules.ren.entity.RenSalarySetEntity;
import io.renren.modules.ren.service.RenSalarySetService;


@Service("renSalarySetService")
public class RenSalarySetServiceImpl extends ServiceImpl<RenSalarySetDao, RenSalarySetEntity> implements RenSalarySetService {


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(RenSalarySetEntity entity) {
        this.insert(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(RenSalarySetEntity entity) {
        this.updateById(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteBatch(Long[] Ids) {
        //删除
        this.deleteBatchIds(Arrays.asList(Ids));
    }

}