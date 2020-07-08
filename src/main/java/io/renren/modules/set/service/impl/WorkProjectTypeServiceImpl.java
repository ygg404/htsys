package io.renren.modules.set.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.modules.project.entity.ProjectContractEntity;
import io.renren.modules.set.entity.ShortTypeEntity;
import io.renren.modules.sys.entity.SysUserEntity;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;
import org.apache.commons.lang.StringUtils;
import io.renren.modules.set.dao.WorkProjectTypeDao;
import io.renren.modules.set.entity.WorkProjectTypeEntity;
import io.renren.modules.set.service.WorkProjectTypeService;
import org.springframework.transaction.annotation.Transactional;


@Service("workProjectTypeService")
public class WorkProjectTypeServiceImpl extends ServiceImpl<WorkProjectTypeDao, WorkProjectTypeEntity> implements WorkProjectTypeService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String key = (String)params.get("key");

        Page<WorkProjectTypeEntity> page = this.selectPage(
                new Query<WorkProjectTypeEntity>(params).getPage(),
                new EntityWrapper<WorkProjectTypeEntity>().like(StringUtils.isNotBlank(key),"id", key)
        );

        return new PageUtils(page);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(WorkProjectTypeEntity entity) {
        this.insert(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(WorkProjectTypeEntity entity) {
        this.updateById(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteBatch(Long[] roleIds) {
        //删除
        this.deleteBatchIds(Arrays.asList(roleIds));
    }


    @Override
    public List<WorkProjectTypeEntity> queryList(Map<String, Object> params){
        List<WorkProjectTypeEntity> list = this.selectByMap(params);
        return list;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteBatchByWIDs(Long[] wids) {
        //根据作业类型ID 删除

        Map<String, Object> parm = new HashMap<>();
        for (Long item : wids) {
            parm.put("wtypeId", item.toString());
        }
        this.deleteByMap(parm);
    }

    public List<WorkProjectTypeEntity> selectByWID(Long wid) {
        //work_project_type表 根据作业类型ID找出对应项目类型ID

        Map<String, Object> parm = new HashMap<>();
        parm.put("wtypeId", wid.toString());
        List<WorkProjectTypeEntity> list = this.selectByMap(parm);
        return list;
    }

    //批量删除数据  (传入值是Entity)
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void DeleteBatchByWPEnitty(Map<String, Object> params) {
        this.deleteByMap(params);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteBatchByWID(Long wid){
        Map<String,Object> params = new HashMap<>();
        params.put("wtypeId",wid);
        this.deleteByMap(params);
    }
}