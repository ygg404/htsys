package io.renren.modules.set.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import io.renren.common.annotation.SysLog;
import io.renren.modules.ren.service.RenRecordVoService;
import io.renren.modules.ren.vo.RenRecordVoEntity;
import io.renren.modules.set.entity.BranchEntity;
import io.renren.modules.set.entity.BranchUserEntity;
import io.renren.modules.set.service.BranchService;
import io.renren.modules.set.service.BranchUserService;
import io.renren.modules.set.service.BranchVoService;
import io.renren.modules.set.vo.BranchVoEntity;
import io.renren.modules.sys.entity.SysUserEntity;
import io.renren.modules.sys.vo.UserVoEntity;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;

/**
 * 部门图表
 *
 * @author ygg
 * @date 2020-02-21 15:03:54
 */
@RestController
@RequestMapping("set/branch")
public class BranchController {
    @Autowired
    private BranchService branchService;

    @Autowired
    private BranchUserService branchUserService;

    @Autowired
    private BranchVoService branchVoService;

    @Autowired
    private RenRecordVoService renRecordVoService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("set:branch:list")
    public R list(){
        List<BranchVoEntity> list = branchVoService.getBranchList();

        return R.ok().put("list", list);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("set:branch:info")
    public R info(@PathVariable("id") Long id){
		BranchVoEntity branchVo = branchVoService.getEntityById(id);
		// 获取该部门所有 成员ID
        List<BranchUserEntity> bulist = branchUserService.getListByBranchId(branchVo.getid());
        List<UserVoEntity> userList = new ArrayList<>();
        for(BranchUserEntity branchUserEntity : bulist){
            UserVoEntity userVoEntity = new UserVoEntity();
            userVoEntity.setUserId(branchUserEntity.getuserId());
            userList.add(userVoEntity);
        }
        branchVo.setUserList(userList);

        return R.ok().put("branchVo", branchVo);
    }

    /**
     * 保存
     */
    @SysLog("保存部门")
    @RequestMapping("/save")
    @RequiresPermissions("set:branch:save")
    public R save(@RequestBody BranchVoEntity branchVo){
        // 部门列表 并返回部门ID
        branchVoService.saveBranchVo(branchVo);
        // 部门与成员关系表
        branchUserService.deleteByBranchId(branchVo.getid());
        List<BranchUserEntity> list = new ArrayList<>();
        for(UserVoEntity user : branchVo.getUserList()){
            BranchUserEntity temp = new BranchUserEntity();
            temp.setbranchId(branchVo.getid());
            temp.setuserId(user.getUserId());
            list.add(temp);
        }
        if(list.size() > 0) branchUserService.insertBatch(list);

        return R.ok();
    }

    /**
     * 修改
     */
    @SysLog("修改部门")
    @RequestMapping("/update")
    @RequiresPermissions("set:branch:update")
    public R update(@RequestBody BranchVoEntity branchVo){
        BranchEntity entity = new BranchEntity();
        entity.setid(branchVo.getid());
        entity.setsdeputyId(branchVo.getsdeputyId());
        entity.setmdeputyId(branchVo.getmdeputyId());
        entity.setbranchName(branchVo.getbranchName());
        entity.setorderNum(branchVo.getorderNum());
        entity.setparentId(branchVo.getparentId());
        branchService.updateAllColumnById(entity);
        // 所属成员的列表
        branchUserService.deleteByBranchId(entity.getid());
        List<BranchUserEntity> list = new ArrayList<>();
        for( UserVoEntity userVo : branchVo.getUserList()){
            BranchUserEntity buEntity = new BranchUserEntity();
            buEntity.setuserId(userVo.getUserId());
            buEntity.setbranchId(entity.getid());
            list.add(buEntity);
        }
        if(list.size() > 0 )branchUserService.insertBatch(list);

        return R.ok();
    }

    /**
     * 排序
     */
    @SysLog("修改部门排序")
    @RequestMapping("/sort")
    @RequiresPermissions("set:branch:update")
    public R sort(@RequestBody BranchEntity branch){
        // 获取同一个上级部门的所有子部门(列表根据 orderNum 排序)
        List<BranchEntity> list = branchService.queryByParentId(branch.getparentId());
        int orgIndex = 0; //被替换的元素下标
        for(int i=0; i<list.size();i++) {
            if (list.get(i).getid() == branch.getid()) {
                orgIndex = i;
            }
        }
        if(orgIndex != 0){
            BranchEntity pre = list.get(orgIndex-1);
            BranchEntity cur = list.get(orgIndex);
            Long temp = pre.getorderNum();
            if(temp == cur.getorderNum()) temp-=1;
            pre.setorderNum( cur.getorderNum());
            cur.setorderNum(temp);
            branchService.update(pre);
            branchService.update(cur);
        }
        return R.ok();
    }

    /**
     * 删除
     */
    @SysLog("删除部门")
    @RequestMapping("/delete")
    @RequiresPermissions("set:branch:delete")
    public R delete(@RequestBody Long[] ids){
        List<BranchEntity> list = branchService.selectList( new EntityWrapper<>());
        branchService.deleteBatch(ids);
        // 删除该部门关联的成员部门表
        for(Long id : ids){
            branchUserService.deleteByBranchId(id);
            // 递归方式 获取所有子部门所有Id
            List<Long> childList = getAllChildId(list , id);
            if(childList.size() > 0){
                Long[] childIds = childList.stream().toArray(Long[]::new);
                branchService.deleteBatch(childIds);
                for(Long childId : childIds){
                    branchUserService.deleteByBranchId(childId);
                }
            }
        }
        return R.ok();
    }

    public List<Long> getAllChildId(List<BranchEntity> list, Long parentId){
        List<Long> curIds = new ArrayList<Long>();
        for(BranchEntity entity : list){
            if( entity.getparentId() == parentId){
                curIds.add( entity.getid() );
                curIds.addAll( getAllChildId( list , entity.getid()) );
            }
        }
        return curIds;
    }
}
