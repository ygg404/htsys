package io.renren.modules.project.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.SqlHelper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;
import io.renren.modules.project.dao.ProjectRecycleDao;
import io.renren.modules.project.entity.ProjectContractEntity;
import io.renren.modules.project.service.RecycleService;
import io.renren.modules.project.vo.RecycleVoEntity;
import javafx.scene.control.Pagination;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("RecycleService")
public class RecycleServiceImpl extends ServiceImpl<ProjectRecycleDao, RecycleVoEntity> implements RecycleService {

    /**
     * 获取回收站分页列表
     * @param params
     * @return
     */
    @Override
    public PageUtils getRecyclePage(Map<String, Object> params){
        Page<RecycleVoEntity> pagnation = new Query<RecycleVoEntity>(params).getPage();
        pagnation = pagnation.setRecords( baseMapper.getRecyclePage(pagnation , params ) );
        return new PageUtils(pagnation);
    }
}
