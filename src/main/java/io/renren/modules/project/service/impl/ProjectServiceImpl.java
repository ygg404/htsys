package io.renren.modules.project.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.modules.set.entity.ShortTypeEntity;
import io.renren.modules.sys.entity.SysUserEntity;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.util.StringUtil;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Service;

import java.util.*;

import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.project.dao.ProjectDao;
import io.renren.modules.project.entity.ProjectEntity;
import io.renren.modules.project.service.ProjectService;
import org.springframework.transaction.annotation.Transactional;


@Service("projectService")
public class ProjectServiceImpl extends ServiceImpl<ProjectDao, ProjectEntity> implements ProjectService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String key = (String)params.get("key");

        Page<ProjectEntity> page = this.selectPage(
                new Query<ProjectEntity>(params).getPage(),
                new EntityWrapper<ProjectEntity>().like(StringUtils.isNotBlank(key),"project_name", key)
        );

        return new PageUtils(page);
    }

    @Override
    public List<ProjectEntity> queryList(Map<String, Object> params){
        String contractNo = (String)params.get("contractNo");
        String pStage = (String)params.get("pStage");
        List<ProjectEntity> lsit = this.selectList(
                new EntityWrapper<ProjectEntity>().eq("contract_no", contractNo)
                .eq(StringUtils.isNotBlank(pStage), "p_stage" ,pStage )
        );
        return lsit;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(ProjectEntity entity) {
        // 获取当前用户Id
        Long createUserId =  ((SysUserEntity)SecurityUtils.getSubject().getPrincipal()).getUserId();
        entity.setcreateuserid(createUserId);
        entity.setpStage("1");
        entity.setprojectCreateDateTime(new Date());
        // 保存前判断 当前项目编号是否存在
        ProjectEntity project = this.selectOne(new EntityWrapper<ProjectEntity>().eq("project_no", entity.getprojectNo()));
        if(project != null){
            entity.setId(project.getId());
        }
        this.insertOrUpdate(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(ProjectEntity entity) {
        this.updateById(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteBatch(Long Id) {
        //删除项目时将 项目p_stage 置为 2 表示放入回收站
        ProjectEntity pEntity = this.selectById(Id);
        pEntity.setpStage("2");
        this.update(pEntity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String getProjectNo(String contractNo){
        Map<String, Object> params = new HashMap<>();
        params.put("contractNo" , contractNo);
        // 获取相同合同编号 的所有项目
        List<ProjectEntity> plist = this.queryList(params);
        int maxsize = plist.size();
        // 后期项目编号 含有“-”的字符
        for(ProjectEntity project : plist){
            if(project.getprojectNo().lastIndexOf("-") > 0){
                if( Integer.parseInt( project.getprojectNo().split("-")[1]) > maxsize){
                    maxsize = Integer.parseInt( project.getprojectNo().split("-")[1]);
                }
            }
        }

        return contractNo + "-" + String.format( "%02d",maxsize + 1);
    }
}