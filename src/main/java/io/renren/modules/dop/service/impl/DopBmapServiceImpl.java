package io.renren.modules.dop.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.common.utils.StringUtil;
import io.renren.modules.dop.dao.DopBmapDao;
import io.renren.modules.dop.entity.DopBmapEntity;
import io.renren.modules.dop.service.DopBmapService;
import io.renren.modules.sys.entity.SysUserEntity;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;
import java.util.List;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;


@Service("dopBmapService")
public class DopBmapServiceImpl extends ServiceImpl<DopBmapDao, DopBmapEntity> implements DopBmapService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String key = (String)params.get("key");

        Page<DopBmapEntity> page = this.selectPage(
                new Query<DopBmapEntity>(params).getPage(),
                new EntityWrapper<DopBmapEntity>().like(StringUtils.isNotBlank(key),"label", key)
                .or().like(StringUtils.isNotBlank(key),"remark", key)
        );

        return new PageUtils(page);
    }

    @Override
    public List<DopBmapEntity> queryList(Map<String, Object> params){
        // 获取经纬度范围
        String minlng = (String) params.get("minlng");
        String minlat = (String) params.get("minlat");
        String maxlng = (String) params.get("maxlng");
        String maxlat = (String) params.get("maxlat");
        // 项目编号大小
        String maxNo = (String) params.get("maxNo");
        String minNo = (String) params.get("minNo");

        List<DopBmapEntity> list = this.selectList(
                new EntityWrapper<DopBmapEntity>()
                        .and(StringUtils.isNotBlank(minlng) , "lng>={0}",minlng)
                        .and(StringUtils.isNotBlank(maxlng) , "lng<={0}",maxlng)
                        .and(StringUtils.isNotBlank(minlat) , "lat>={0}",minlat)
                        .and(StringUtils.isNotBlank(maxlat) , "lat<={0}",maxlat)
                        .and(StringUtils.isNotBlank(minNo), "project_id>={0}",minNo)
                        .and(StringUtils.isNotBlank(maxNo), "project_id<={0}",maxNo)
                .orderBy("project_id",false)
        );
        return list;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(DopBmapEntity entity) {
        // 获取当前用户Id
        SysUserEntity createUser =  (SysUserEntity) SecurityUtils.getSubject().getPrincipal();
        entity.setCreateUserId(createUser.getUserId());
        entity.setCreateUserName(createUser.getUsername());
        entity.setCreateTime(new Date());
        this.insert(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteByProId(Long projectId) {
        this.delete(new EntityWrapper<DopBmapEntity>().eq("project_id",projectId));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(DopBmapEntity entity) {
        this.updateById(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteBatch(Long[] Ids) {
        //删除
        this.deleteBatchIds(Arrays.asList(Ids));
    }

}