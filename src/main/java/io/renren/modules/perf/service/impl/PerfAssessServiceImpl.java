package io.renren.modules.perf.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.modules.perf.vo.PerfAssessVoEntity;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Arrays;
import java.util.Map;
import java.util.List;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.perf.dao.PerfAssessDao;
import io.renren.modules.perf.entity.PerfAssessEntity;
import io.renren.modules.perf.service.PerfAssessService;


@Service("perfAccessService")
public class PerfAssessServiceImpl extends ServiceImpl<PerfAssessDao, PerfAssessEntity> implements PerfAssessService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String key = (String)params.get("key");

        Page<PerfAssessEntity> page = this.selectPage(
                new Query<PerfAssessEntity>(params).getPage(),
                new EntityWrapper<PerfAssessEntity>().like(StringUtils.isNotBlank(key),"id", key)
        );

        return new PageUtils(page);
    }

    @Override
    public List<PerfAssessEntity> queryList(Map<String, Object> params){
        // 年份 上下半年
        String year = params.get("year").toString();
        String updown = params.get("updown").toString();
//        String userId = params.get("userId").toString();

        List<PerfAssessEntity> list = this.selectList(
                new EntityWrapper<PerfAssessEntity>().eq("year" , year).eq("updown",updown)
                        .orderBy("check_user_id",true).orderBy("user_id",true)
                        .orderBy("kbi_id",true)
        );
        return list;
    }

    @Override
    public void deleteByMap(PerfAssessVoEntity voEntity){
        this.delete(
                new EntityWrapper<PerfAssessEntity>().eq("year", voEntity.getYear())
                        .eq("updown",voEntity.getUpdown())
                        .eq("user_id",voEntity.getUserId()));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(PerfAssessEntity entity) {
        this.insert(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(PerfAssessEntity entity) {
        this.updateById(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteBatch(Long[] Ids) {
        //删除
        this.deleteBatchIds(Arrays.asList(Ids));
    }

}