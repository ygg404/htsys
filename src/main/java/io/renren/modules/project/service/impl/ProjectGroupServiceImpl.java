package io.renren.modules.project.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.modules.project.entity.ProjectWorkEntity;
import io.renren.modules.project.service.ProjectWorkService;
import io.renren.modules.project.vo.ProjectGroupVoEntity;
import io.renren.modules.project.vo.WorkGroupVoEntity;
import io.renren.modules.set.entity.WorkGroupEntity;
import io.renren.modules.set.service.WorkGroupService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.project.dao.ProjectGroupDao;
import io.renren.modules.project.entity.ProjectGroupEntity;
import io.renren.modules.project.service.ProjectGroupService;
import org.springframework.transaction.annotation.Transactional;


@Service("projectGroupService")
public class ProjectGroupServiceImpl extends ServiceImpl<ProjectGroupDao, ProjectGroupEntity> implements ProjectGroupService {

    @Autowired
    public WorkGroupService workGroupService;

    @Autowired
    public ProjectWorkService projectWorkService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String key = (String)params.get("key");

        Page<ProjectGroupEntity> page = this.selectPage(
                new Query<ProjectGroupEntity>(params).getPage(),
                new EntityWrapper<ProjectGroupEntity>().like(StringUtils.isNotBlank(key),"id", key)
        );

        return new PageUtils(page);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<WorkGroupVoEntity> getProjectDataCoe(Map<String, Object> params){
        List<ProjectGroupVoEntity> list = this.baseMapper.getProjectDataCoe();
        List<WorkGroupEntity> groupList = workGroupService.queryList(params);
        List<WorkGroupVoEntity> groupVoList = new ArrayList<>();
        for(WorkGroupEntity workGroupEntity: groupList ){
            // 设置每个工作组的 项目明细
            WorkGroupVoEntity entity = new WorkGroupVoEntity();
            entity.setgroupId(workGroupEntity.getId());
            entity.setgroupName(workGroupEntity.getName());
            // 未完成 项目数
            Integer undoNum = 0;
            // 已安排产值
            Float isSetOutput = 0f;
            // 未安排产值
            Float isNotOutput = 0f;

            List<ProjectGroupVoEntity> newprojectList = new ArrayList<>();
            for( ProjectGroupVoEntity pgroupVo : list){
                if(pgroupVo.getgroupId() == entity.getgroupId()){
                    newprojectList.add(pgroupVo);
                    Float output = 0f;
                    if(pgroupVo.getprojectActuallyOutput() != null) {
                        output = pgroupVo.getprojectActuallyOutput();
                    }else{
                        output = pgroupVo.getprojectOutput();
                    }
                    isSetOutput += output;
                    if(pgroupVo.getscheduleRate() == null) pgroupVo.setscheduleRate(0L);
                    if(pgroupVo.getscheduleRate() != 100) {
                        undoNum ++;
                        isNotOutput += output * ( 100 - pgroupVo.getscheduleRate()) /100 ;
                    }
                }
            }
            entity.setIsNotOutput(isNotOutput);
            entity.setisSetOutput(isSetOutput);
            entity.setundoneNum(undoNum);
            entity.setProjectList(newprojectList);

            groupVoList.add(entity);
        }
        return  groupVoList;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<ProjectGroupVoEntity> getChartCollect(Map<String, Object> params){
        return this.baseMapper.getChartCollect(params);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<ProjectGroupVoEntity> getListByProjectNo(Map<String, Object> params){
        String porjectNo = (String) params.get("projectNo");

        //work_group 工作组数据
        List<WorkGroupEntity> wgList = workGroupService.queryList(params);
        //获取工作分组数据（占比 产值）
        List<ProjectGroupEntity> list = this.selectList( new EntityWrapper<ProjectGroupEntity>().eq("project_no", porjectNo));
        List<ProjectGroupVoEntity> volist = new ArrayList<>();
        for(WorkGroupEntity wgEntity : wgList){
            ProjectGroupVoEntity entity = new ProjectGroupVoEntity();
            entity.setchecked(false);
            entity.setgroupId(wgEntity.getId());
            entity.setgroupName(wgEntity.getName());
            entity.setHeadId(wgEntity.getHeadId());
            entity.setHeadMan(wgEntity.getHeadMan());

            for(ProjectGroupEntity groupEntity : list) {
                if(wgEntity.getId() == groupEntity.getgroupId()) {
                    entity.setchecked(true);
                    entity.setoutputRate(groupEntity.getoutputRate());
                    entity.setprojectOutput(groupEntity.getprojectOutput());
                    entity.setshortDateTime(groupEntity.getshortDateTime());
                    entity.setlastDateTime(groupEntity.getlastDateTime());
                    entity.setprojectActuallyOutput(groupEntity.getprojectActuallyOutput());
                    entity.setlastDateTime(groupEntity.getlastDateTime());
                    entity.setshortDateTime(groupEntity.getshortDateTime());
                }
            }
            volist.add(entity);
        }
        return  volist;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(ProjectGroupEntity entity) {
        this.insert(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(ProjectGroupEntity entity) {
        this.updateById(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteBatch(Long[] roleIds) {
        //删除
        this.deleteBatchIds(Arrays.asList(roleIds));
    }

}