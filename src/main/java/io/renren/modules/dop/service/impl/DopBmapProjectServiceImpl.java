package io.renren.modules.dop.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.common.utils.MapUtils;
import io.renren.modules.dop.dao.DopBmapProjectDao;
import io.renren.modules.dop.entity.DopBmapEntity;
import io.renren.modules.dop.entity.DopBmapProjectEntity;
import io.renren.modules.dop.service.DopBmapProjectService;
import io.renren.modules.dop.service.DopBmapService;
import io.renren.modules.sys.entity.SysUserEntity;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;


@Service("dopBmapProjectService")
public class DopBmapProjectServiceImpl extends ServiceImpl<DopBmapProjectDao, DopBmapProjectEntity> implements DopBmapProjectService {

    @Autowired
    private DopBmapService dopBmapService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String key = (String)params.get("key");

        Page<DopBmapProjectEntity> page = this.selectPage(
                new Query<DopBmapProjectEntity>(params).getPage(),
                new EntityWrapper<DopBmapProjectEntity>().like(StringUtils.isNotBlank(key),"project_name", key)
                .orderBy("id",false)
        );
        // 查找该页项目编号的最大及最小
        int size = page.getRecords().size();
        MapUtils parms = new MapUtils();
        if ( size > 0) {
            parms.put("minNo",page.getRecords().get(size - 1).getId().toString());
            parms.put("maxNo",page.getRecords().get(0).getId().toString());
        }
        // 获取该项目下所有标注信息
        List<DopBmapEntity> bMapList = dopBmapService.queryList(parms);
        size = 0;
        for (DopBmapProjectEntity entity : page.getRecords()) {
            List<DopBmapEntity> newList = new ArrayList<>();
            for (int i = size; i < bMapList.size(); i++,size++) {
                if (bMapList.get(i).getProjectId() == entity.getId()) {
                    newList.add(bMapList.get(i));
                } else {
                    break;
                }
            }
            entity.setBmapList(newList);
        }
        return new PageUtils(page);
    }

    @Override
    public List<DopBmapProjectEntity> queryList(Map<String, Object> params){
        List<DopBmapProjectEntity> list = this.selectList(
                new EntityWrapper<DopBmapProjectEntity>()
        );
        return list;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long getMaxId() {
        return this.baseMapper.getMaxId();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(DopBmapProjectEntity entity) {
        Long maxId = this.getMaxId() == null ? 1L: this.getMaxId() + 1L;
        SysUserEntity userEntity = (SysUserEntity) SecurityUtils.getSubject().getPrincipal();
        entity.setId(maxId);
        entity.setCreateTime(new Date());
        entity.setCreateUserId(userEntity.getUserId());
        entity.setCreateUserName(userEntity.getUsername());
        this.insert(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(DopBmapProjectEntity entity) {
        this.updateById(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteBatch(Long[] Ids) {
        //删除
        this.deleteBatchIds(Arrays.asList(Ids));
    }

}