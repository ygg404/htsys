package io.renren.modules.set.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Arrays;
import java.util.Map;
import java.util.List;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.set.dao.SetQualityScoreDao;
import io.renren.modules.set.entity.SetQualityScoreEntity;
import io.renren.modules.set.service.SetQualityScoreService;


@Service("setQualityScoreService")
public class SetQualityScoreServiceImpl extends ServiceImpl<SetQualityScoreDao, SetQualityScoreEntity> implements SetQualityScoreService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String key = (String)params.get("key");

        Page<SetQualityScoreEntity> page = this.selectPage(
                new Query<SetQualityScoreEntity>(params).getPage(),
                new EntityWrapper<SetQualityScoreEntity>().like(StringUtils.isNotBlank(key),"id", key)
        );

        return new PageUtils(page);
    }

    @Override
    public List<String> getFileList(){
        return this.baseMapper.getFileList();
    }

    @Override
    public List<SetQualityScoreEntity> queryList(Map<String, Object> params){
        String fileNo = (String)params.get("fileNo");

        List<SetQualityScoreEntity> list = this.selectList(
                new EntityWrapper<SetQualityScoreEntity>().eq("file_no" , fileNo)
        );
        return list;
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(SetQualityScoreEntity entity) {
        this.insert(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(SetQualityScoreEntity entity) {
        this.updateById(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteBatch(Long[] Ids) {
        //删除
        this.deleteBatchIds(Arrays.asList(Ids));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteByFileNo(String fileNo){
        this.delete(new EntityWrapper<SetQualityScoreEntity>().eq("file_no",fileNo));
    }
}