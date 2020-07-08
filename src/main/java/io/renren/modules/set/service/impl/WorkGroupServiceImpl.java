package io.renren.modules.set.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.modules.set.entity.BranchEntity;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.List;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.set.dao.WorkGroupDao;
import io.renren.modules.set.entity.WorkGroupEntity;
import io.renren.modules.set.service.WorkGroupService;


@Service("workGroupService")
public class WorkGroupServiceImpl extends ServiceImpl<WorkGroupDao, WorkGroupEntity> implements WorkGroupService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String key = (String)params.get("key");

        Page<WorkGroupEntity> page = this.selectPage(
                new Query<WorkGroupEntity>(params).getPage(),
                new EntityWrapper<WorkGroupEntity>().like(StringUtils.isNotBlank(key),"id", key)
        );

        return new PageUtils(page);
    }

    @Override
    public List<WorkGroupEntity> queryList(Map<String, Object> params){
        String pId = (String)params.get("pid");

        List<WorkGroupEntity> list = this.selectList(
                new EntityWrapper<WorkGroupEntity>().eq(StringUtils.isNotBlank(pId), "p_id",pId)
                        .orderBy("p_id",true).orderBy("order_num" , true)
        );
        return list;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(WorkGroupEntity entity) {
        this.insert(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(WorkGroupEntity entity) {
        this.updateById(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void setSort(WorkGroupEntity entity){
        // 获取同一个上级部门的所有子部门(列表根据 orderNum 排序)
        List<WorkGroupEntity> parentlist = this.selectList(
                new EntityWrapper<WorkGroupEntity>().eq("p_id", entity.getPId())
                        .orderBy("order_num",true)
        );
        int orgIndex = 0; //被替换的元素下标
        for(int i=0; i<parentlist.size();i++) {
            if (parentlist.get(i).getId() == entity.getId()) {
                orgIndex = i;
            }
        }
        if(orgIndex != 0){
            WorkGroupEntity pre = parentlist.get(orgIndex-1);
            WorkGroupEntity cur = parentlist.get(orgIndex);
            Long temp = pre.getOrderNum();
            if(temp == cur.getOrderNum()) temp-=1;
            pre.setOrderNum( cur.getOrderNum());
            cur.setOrderNum(temp);
            this.update(pre);
            this.update(cur);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteBatch(Long[] Ids) {
        List<WorkGroupEntity> list = this.selectList( new EntityWrapper<>());
        //删除
        this.deleteBatchIds(Arrays.asList(Ids));
        // 删除该部门关联的成员部门表
        for(Long id : Ids){
            // 递归方式 获取所有子部门所有Id
            List<Long> childList = getAllChildId(list , id);
            if(childList.size() > 0){
                Long[] childIds = childList.stream().toArray(Long[]::new);
                this.deleteBatchIds(Arrays.asList(childIds));
            }
        }

    }


    public List<Long> getAllChildId(List<WorkGroupEntity> list, Long parentId){
        List<Long> curIds = new ArrayList<Long>();
        for(WorkGroupEntity entity : list){
            if( entity.getPId() == parentId){
                curIds.add( entity.getId() );
                curIds.addAll( getAllChildId( list , entity.getId()) );
            }
        }
        return curIds;
    }

}