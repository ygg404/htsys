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

import io.renren.modules.set.dao.SetScoreHouseDao;
import io.renren.modules.set.entity.SetScoreHouseEntity;
import io.renren.modules.set.service.SetScoreHouseService;


@Service("setScoreHouseService")
public class SetScoreHouseServiceImpl extends ServiceImpl<SetScoreHouseDao, SetScoreHouseEntity> implements SetScoreHouseService {

    @Override
    public List<SetScoreHouseEntity> queryList(Map<String, Object> params){
        List<SetScoreHouseEntity> list = this.selectList(
                new EntityWrapper<SetScoreHouseEntity>().orderBy("order_num" , true)
        );
        return list;
    }

    @Override
    public void updateSort(Map<String, Object> params){
        Long preNum = Long.parseLong(params.get("preOrderNum").toString());
        Long nextNum = Long.parseLong(params.get("nextOrderNum").toString());
        List<SetScoreHouseEntity> list = this.selectList(
                new EntityWrapper<SetScoreHouseEntity>().orderBy("order_num",true)
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
    public Long getMaxOrderNum(){
        Long maxOrderNum = 0L;
        List<SetScoreHouseEntity> list = this.selectList(
                new EntityWrapper<SetScoreHouseEntity>().orderBy("order_num",false)
        );
        if(list.size() > 0){
            maxOrderNum = list.get(0).getOrderNum() + 1;
        } else {
            maxOrderNum = 1L;
        }
        return maxOrderNum;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(SetScoreHouseEntity entity) {
        this.insert(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(SetScoreHouseEntity entity) {
        this.updateById(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteBatch(Long[] Ids) {
        //删除
        this.deleteBatchIds(Arrays.asList(Ids));
    }

}