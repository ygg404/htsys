package io.renren.modules.set.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.modules.project.entity.ProjectContractEntity;
import io.renren.modules.project.service.ProjectService;
import io.renren.modules.set.entity.ProjectTypeEntity;
import io.renren.modules.set.entity.WorkProjectTypeEntity;
import io.renren.modules.set.service.ProjectTypeService;
import io.renren.modules.set.service.WorkProjectTypeService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.set.dao.WorkTypeDao;
import io.renren.modules.set.entity.WorkTypeEntity;
import io.renren.modules.set.service.WorkTypeService;
import org.springframework.transaction.annotation.Transactional;


@Service("workTypeService")
public class WorkTypeServiceImpl extends ServiceImpl<WorkTypeDao, WorkTypeEntity> implements WorkTypeService {

    @Autowired
    public WorkProjectTypeService workprojecttypeservice;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String key = (String) params.get("key");
        String pTypeId = (String) params.get("projectTypeId");

        Page<WorkTypeEntity> pagnation = new Query<WorkTypeEntity>(params).getPage();
        pagnation = pagnation.setRecords( baseMapper.getWorkTypePage(pagnation , params ) );

//        Page<WorkTypeEntity> page = this.selectPage(
//                new Query<WorkTypeEntity>(params).getPage(),
//                new EntityWrapper<WorkTypeEntity>().like(StringUtils.isNotBlank(key), "id", key)
//                .or().like(StringUtils.isNotBlank(key), "type_name", key)
//                .or().like(StringUtils.isNotBlank(key), "unit", key)
//        );

        //根据wtypeId 找到 ptypeId (set)
        for (WorkTypeEntity entity : pagnation.getRecords()) {
            Map<String, Object> parms = new HashMap<>();
            parms.put("wtypeId", entity.getId());

            List<Long> PList = new ArrayList<Long>();
            for (WorkProjectTypeEntity ptentity : workprojecttypeservice.queryList(parms)) {
                System.out.println("控制台打印作业类型ID为: " + ptentity.getWtypeid() + "  的项目类型编号是: " + ptentity.getPtypeid());
                PList.add(ptentity.getPtypeid());
            }
            System.out.println("---------------------------------------------------------------------------------------------------");
            entity.setProjectTypeIdList(PList);
        }

        return new PageUtils(pagnation);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<WorkTypeEntity> queryListByPtypeId(Long projectTypeId){
        return this.baseMapper.getListByPtypeId(projectTypeId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<WorkTypeEntity> queryList(){
        List<WorkTypeEntity> list = this.selectList(new EntityWrapper<WorkTypeEntity>().orderBy("order_num", false));

        //根据wtypeId 找到 ptypeId (set)
        for (WorkTypeEntity entity :list) {
            Map<String, Object> parms = new HashMap<>();
            parms.put("wtypeId", entity.getId());
            List<Long> PList = new ArrayList<Long>();
            for (WorkProjectTypeEntity ptentity : workprojecttypeservice.queryList(parms)) {
                PList.add(ptentity.getPtypeid());
            }
            entity.setProjectTypeIdList(PList);
        }
        return list;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void setOrderNum(Map<String, Object> params){
        Long oldNum = Long.valueOf(params.get("oldNum").toString());
        Long newNum = Long.valueOf(params.get("newNum").toString());
        Long max = oldNum > newNum? oldNum: newNum;
        Long min = oldNum > newNum? newNum: oldNum;
        List<WorkTypeEntity> list = this.selectList(
                new EntityWrapper<WorkTypeEntity>().orderBy("order_num", false)
                .between("order_num", min,max)
        );
        // 已经改变的排序号
        List<Long> newList = new ArrayList<>();
        for( int i= 0; i< list.size();i++){
            newList.add( list.get(i).getorderNum() );
        }
        newList.remove(newNum);
        if( oldNum > newNum){
            newList.add(  newList.indexOf( oldNum) , newNum);
        } else {
            if(newList.indexOf(oldNum) >= list.size() - 1){
                newList.add(newNum);
            }else {
                newList.add( newList.indexOf(oldNum) + 1 , newNum);
            }
        }
        // 改变顺序后的顺序号
        for (int i = 0; i< list.size(); i++){
            list.get(i).setorderNum( newList.get(i) );
        }
        this.insertOrUpdateBatch(list);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(WorkTypeEntity entity) {
        this.insert(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(WorkTypeEntity entity) {
        this.updateById(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteBatch(Long[] roleIds) {
        //删除
        this.deleteBatchIds(Arrays.asList(roleIds));
    }

    //返回保存数据后的ID
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long ReturnIDAfterSave(WorkTypeEntity entity){
        //条件
        Wrapper Condition = new EntityWrapper<WorkTypeEntity>().eq("type_name", entity.getTypeName()).eq("unit",entity.getUnit()).eq("unit_output",entity.getUnitOutput());

        // WorkTypeEntity Res = (WorkTypeEntity)this.selectObj(Condition);
        WorkTypeEntity Res = this.selectOne(Condition);
        Long ID =  Res.getId();
        return ID;
    }

}