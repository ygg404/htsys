package io.renren.modules.project.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.common.utils.StringUtil;
import io.renren.modules.project.dao.ProjectBillDao;
import io.renren.modules.project.entity.ProjectBillEntity;
import io.renren.modules.project.service.ProjectBillService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Arrays;
import java.util.Map;
import java.util.List;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;



@Service("projectBillService")
public class ProjectBillServiceImpl extends ServiceImpl<ProjectBillDao, ProjectBillEntity> implements ProjectBillService {

    @Override
    public List<ProjectBillEntity> queryList(Map<String, Object> params){
        String projectNo = (String)params.get("projectNo");

        List<ProjectBillEntity> list = this.selectList(
                new EntityWrapper<ProjectBillEntity>().eq("project_no", projectNo).orderBy("id", true)
        );
        return list;
    }

    @Override
    public void deleteByProNo(String projectNo){
        if(StringUtil.isEmpty(projectNo))return;
        this.delete(new EntityWrapper<ProjectBillEntity>().eq("project_no",projectNo));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(ProjectBillEntity entity) {
        this.insert(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(ProjectBillEntity entity) {
        this.updateById(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteBatch(Long[] Ids) {
        //删除
        this.deleteBatchIds(Arrays.asList(Ids));
    }

}