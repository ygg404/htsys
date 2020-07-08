package io.renren.modules.project.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;
import io.renren.modules.project.dao.ProjectManageDao;
import io.renren.modules.project.service.ProjectManageService;
import io.renren.modules.project.vo.ProjectVoEntity;
import io.renren.modules.project.vo.RecycleVoEntity;
import io.renren.modules.set.entity.WorkGroupEntity;
import io.renren.modules.set.service.WorkGroupService;
import io.renren.modules.sys.entity.SysUserEntity;
import io.renren.modules.sys.service.SysUserRoleService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service("ProjectManageService")
public class ProjectManageServiceImpl extends ServiceImpl<ProjectManageDao, ProjectVoEntity> implements ProjectManageService {

    @Autowired
    private SysUserRoleService sysUserRoleService;

    @Autowired
    private WorkGroupService workGroupService;

    /**
     * 获取项目管理分页列表
     * @param params
     * @return
     */
    @Override
    public PageUtils getProjectManagPage(Map<String, Object> params){
        // 获取当前用户角色
        SysUserEntity curUser = (SysUserEntity) SecurityUtils.getSubject().getPrincipal();
        List<Long> roleList = sysUserRoleService.queryRoleIdList(curUser.getUserId());

        // 角色是管理员
        if( roleList.indexOf(1L) >= 0){
            ;
        }   // 角色是质检、核算、生产负责人
        else if ( roleList.indexOf(10L) >= 0 || roleList.indexOf(3L) >= 0 || roleList.indexOf(6L) >= 0  ) {
            params.put("projectchargeAccount", curUser.getUseraccount());
        }
        //如果当前角色有项目负责人的角色，而且不是管理员角色
        else if(roleList.indexOf(4L) >= 0 ){
            params.put("projectchargeAccount", curUser.getUseraccount());
            WorkGroupEntity groupEntity = workGroupService.selectOne(
                    new EntityWrapper<WorkGroupEntity>().eq( "head_id",curUser.getUserId())
            );
//            // 并且角色不是质检、核算、生产负责人
//            if( roleList.indexOf(3L) == -1 && roleList.indexOf(3L) == -1 && roleList.indexOf(6L) == -1 )
                // 如果项目负责人 不属于任何组
                if ( groupEntity != null) {
                    params.put("groupId" , groupEntity.getId());
                }
        }

        Page<ProjectVoEntity> pagnation = new Query<ProjectVoEntity>(params).getPage();
        pagnation = pagnation.setRecords( baseMapper.getProjectManagPage(pagnation , params ) );
        return new PageUtils(pagnation);
    }

    /**
     * 项目管理列表
     * @param params
     * @return
     */
    public List<ProjectVoEntity> getProjectManagList(Map<String, Object> params){
        return baseMapper.getProjectManagPage(params);
    }
}
