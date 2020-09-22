package io.renren.modules.project.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.modules.project.dao.CheckOutputRemarkDao;
import io.renren.modules.project.entity.CheckOutputRemarkEntity;
import io.renren.modules.project.service.CheckOutputRemarkService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Arrays;
import java.util.Map;
import java.util.List;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;




@Service("checkOutputRemarkService")
public class CheckOutputRemarkServiceImpl extends ServiceImpl<CheckOutputRemarkDao, CheckOutputRemarkEntity> implements CheckOutputRemarkService {


    @Override
    public List<CheckOutputRemarkEntity> queryList(String projectNo){
        List<CheckOutputRemarkEntity> list = this.selectList(
                new EntityWrapper<CheckOutputRemarkEntity>().eq("project_no",projectNo)
        );
        return list;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(CheckOutputRemarkEntity entity) {
        this.insert(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(CheckOutputRemarkEntity entity) {
        this.updateById(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteByProjectNo(String projectNo) {
        //删除
        this.delete(new EntityWrapper<CheckOutputRemarkEntity>().eq("project_no",projectNo));
    }

}