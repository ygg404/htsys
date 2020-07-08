package io.renren.modules.perf.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.modules.perf.vo.PerfExtraVoEntity;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Arrays;
import java.util.Map;
import java.util.List;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.perf.dao.PerfExtraScoringDao;
import io.renren.modules.perf.entity.PerfExtraScoringEntity;
import io.renren.modules.perf.service.PerfExtraScoringService;


@Service("perfExtraScoringService")
public class PerfExtraScoringServiceImpl extends ServiceImpl<PerfExtraScoringDao, PerfExtraScoringEntity> implements PerfExtraScoringService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String key = (String)params.get("key");

        Page<PerfExtraScoringEntity> page = this.selectPage(
                new Query<PerfExtraScoringEntity>(params).getPage(),
                new EntityWrapper<PerfExtraScoringEntity>().like(StringUtils.isNotBlank(key),"id", key)
        );

        return new PageUtils(page);
    }

    @Override
    public void deleteByParms(PerfExtraVoEntity voEntity){
        this.delete(
                new EntityWrapper<PerfExtraScoringEntity>().eq("check_user_id" ,voEntity.getCheckUserId())
                .eq("year" , voEntity.getYear()).eq("updown",voEntity.getUpdown())
        );
    }

    @Override
    public List<PerfExtraScoringEntity> queryList(Map<String, Object> params){
        String year = params.get("year").toString();
        String updown = params.get("updown").toString();
        String checkUserId = (String)params.get("checkUserId");

        List<PerfExtraScoringEntity> list = this.selectList(
                new EntityWrapper<PerfExtraScoringEntity>()
                        .eq("year",year).eq("updown",updown)
                        .eq(StringUtils.isNotBlank(checkUserId),"check_user_id",checkUserId)
                        .orderBy("check_user_id", true)
        );
        return list;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(PerfExtraScoringEntity entity) {
        this.insert(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(PerfExtraScoringEntity entity) {
        this.updateById(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteBatch(Long[] Ids) {
        //删除
        this.deleteBatchIds(Arrays.asList(Ids));
    }

}