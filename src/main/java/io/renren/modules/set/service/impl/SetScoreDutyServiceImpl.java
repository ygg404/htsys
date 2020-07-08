package io.renren.modules.set.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.List;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.set.dao.SetScoreDutyDao;
import io.renren.modules.set.entity.SetScoreDutyEntity;
import io.renren.modules.set.service.SetScoreDutyService;


@Service("setScoreDutyService")
public class SetScoreDutyServiceImpl extends ServiceImpl<SetScoreDutyDao, SetScoreDutyEntity> implements SetScoreDutyService {

    @Override
    public List<SetScoreDutyEntity> queryList(Map<String, Object> params){
        List<SetScoreDutyEntity> list = this.selectList(
                new EntityWrapper<SetScoreDutyEntity>().orderBy("order_num" , true)
        );
        return list;
    }

    @Override
    public Long getMaxOrderNum(){
        Long maxOrderNum = 0L;
        List<SetScoreDutyEntity> list = this.selectList(
                new EntityWrapper<SetScoreDutyEntity>().orderBy("order_num",false)
        );
        if(list.size() > 0){
            maxOrderNum = list.get(0).getOrderNum() + 1;
        } else {
            maxOrderNum = 1L;
        }
        return maxOrderNum;
    }

    @Override
    public void updateSort(Map<String, Object> params){
        Long preNum = Long.parseLong(params.get("preOrderNum").toString());
        Long nextNum = Long.parseLong(params.get("nextOrderNum").toString());
        List<SetScoreDutyEntity> list = this.selectList(
                new EntityWrapper<SetScoreDutyEntity>().orderBy("order_num",true)
        );
        // 已经改变的排序号
        List<Long> newList = new ArrayList<>();
        for( int i= 0; i< list.size();i++){
            newList.add( list.get(i).getOrderNum() );
        }
        newList.remove(nextNum);
        if( preNum < nextNum){
            newList.add(  newList.indexOf( preNum) , nextNum);
        } else {
            if(newList.indexOf(preNum) >= list.size() - 1){
                newList.add(nextNum);
            }else {
                newList.add( newList.indexOf(preNum) + 1 , nextNum);
            }
        }
        // 改变顺序后的顺序号
        for (int i = 0; i< list.size(); i++){
            list.get(i).setOrderNum( newList.get(i) );
        }
        this.insertOrUpdateBatch(list);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(SetScoreDutyEntity entity) {
        this.insert(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(SetScoreDutyEntity entity) {
        this.updateById(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteBatch(Long[] Ids) {
        //删除
        this.deleteBatchIds(Arrays.asList(Ids));
    }

}