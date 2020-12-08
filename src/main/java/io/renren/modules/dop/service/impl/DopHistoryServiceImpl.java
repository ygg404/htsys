package io.renren.modules.dop.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.common.utils.UuidUtil;
import io.renren.modules.dop.entity.DopDeviceEntity;
import io.renren.modules.dop.service.DopDeviceService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;
import java.util.List;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.dop.dao.DopHistoryDao;
import io.renren.modules.dop.entity.DopHistoryEntity;
import io.renren.modules.dop.service.DopHistoryService;


@Service("dopHistoryService")
public class DopHistoryServiceImpl extends ServiceImpl<DopHistoryDao, DopHistoryEntity> implements DopHistoryService {

    @Autowired
    public DopDeviceService dopDeviceService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String deviceId = (String)params.get("deviceId");
        String key = (String)params.get("key");

        Page<DopHistoryEntity> page = this.selectPage(
                new Query<DopHistoryEntity>(params).getPage(),
                new EntityWrapper<DopHistoryEntity>().eq("device_id", deviceId)
                        .like(StringUtils.isNotBlank(key),"borrower_name", key)
                        .like(StringUtils.isNotBlank(key),"device_name", key)
        );

        return new PageUtils(page);
    }

    @Override
    public List<DopHistoryEntity> queryList(Map<String, Object> params){
        List<DopHistoryEntity> list = this.selectList(
                new EntityWrapper<DopHistoryEntity>()
        );
        return list;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(DopHistoryEntity entity) throws Exception{
        try {
            entity.setCreateTime(new Date());
            DopDeviceEntity deviceEntity = dopDeviceService.selectOne(new EntityWrapper<DopDeviceEntity>().eq("id" , entity.getDeviceId()));
            switch (entity.getRentStatus().intValue()) {
                // 申请出借为流程开始，初始化流程ID
                case 1:
                    entity.setProcessId(UuidUtil.getShortUUID());
                    break;
                // 审批出借
                case 2:
                    // 查看仪器是否闲置中
                    if (deviceEntity.getDevStation() == 0) {
                        deviceEntity.setDevStation(1L);
                        dopDeviceService.update(deviceEntity);
                    } else {
                        String errMsg = "";
                        switch(deviceEntity.getDevStation().intValue()) {
                            case 1:
                                errMsg = "仪器已经出借中，无法出借！";
                                break;
                            case 2:
                                errMsg = "仪器维修中，无法出借！";
                                break;
                            case 3:
                                errMsg = "仪器已丢失，无法出借！";
                                break;
                            case 4:
                                errMsg = "仪器已报废，无法出借！";
                                break;
                             default:
                                 break;
                        }
                        throw new Exception(errMsg);
                    }
                    break;
                 //确认归还
                case 4:
                    deviceEntity.setDevStation(0L);
                    dopDeviceService.update(deviceEntity);
                    break;
                 default:
                     ;
            }
            this.insert(entity);

        } catch (Exception ex) {
            throw ex;
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(DopHistoryEntity entity) {
        this.updateById(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteBatch(Long[] Ids) {
        //删除
        this.deleteBatchIds(Arrays.asList(Ids));
    }

}