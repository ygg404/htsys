package io.renren.modules.ren.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.renren.common.utils.MapUtils;
import io.renren.modules.ren.entity.RenAccessUserEntity;
import io.renren.modules.ren.service.RenAccessUserService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.renren.modules.ren.entity.RenAccessEntity;
import io.renren.modules.ren.service.RenAccessService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * 考核目标
 *
 * @author ygg
 * @date 2020-02-28 16:07:08
 */
@RestController
@RequestMapping("ren/access")
public class RenAccessController {

    @Autowired
    private RenAccessService renAccessService;

    @Autowired
    private RenAccessUserService renAccessUserService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("ren:access:list")
    public R list(@RequestParam Map<String, Object> params){
        List<RenAccessEntity> list = renAccessService.queryList(params);

        return R.ok().put("list", list);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("ren:access:info")
    public R info(@PathVariable("id") Long id){
		RenAccessEntity access = renAccessService.selectById(id);

        return R.ok().put("access", access);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("ren:access:save")
    public R save(@RequestBody RenAccessEntity renAccess){
        if(renAccess.getparentId() == 0) renAccess.setaccessScore(null);
		renAccessService.save(renAccess);
        setFirstAccess(renAccess);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("ren:access:update")
    public R update(@RequestBody RenAccessEntity renAccess){
		renAccessService.updateById(renAccess);
        setFirstAccess(renAccess);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("ren:access:delete")
    public R delete(@RequestBody RenAccessEntity renAccess){
        HashMap<String,Object> parms = new HashMap();
        parms.put("accessId", renAccess.getid().toString());
        parms.put("parentId", renAccess.getid().toString());
        Long[] ids = new Long[]{renAccess.getid()};
		renAccessService.deleteBatch(ids);
		// 获取其二级的子目标
        List<RenAccessEntity> zlist = renAccessService.queryList(parms);
        // 删除其二级的目标
        renAccessService.deleteByParentId(renAccess.getid());
        // 删除后同时删除目标与用户的关系
        List<RenAccessUserEntity> bulist = renAccessUserService.queryList(parms);
       for(RenAccessUserEntity entity : bulist){
           renAccessUserService.deleteById(entity.getid());
       }
       for(RenAccessEntity entity: zlist){
           HashMap<String,Object> param = new HashMap();
           parms.put("accessId", entity.getid());
           List<RenAccessUserEntity> zulist = renAccessUserService.queryList(param);
           for(RenAccessUserEntity zu : zulist){
               renAccessUserService.deleteById(zu.getid());
           }
       }

        //如果目标是二级目标 则重新计算总分
        setFirstAccess(renAccess);

        return R.ok();
    }

    /**
     * 修改 一级总分
     */
    public void setFirstAccess(RenAccessEntity renAccess){
        // 修改了 二级目标 然后修改 一级目标 总分
        if(renAccess.getparentId() != 0){
            RenAccessEntity parent = renAccessService.selectById(renAccess.getparentId());
            HashMap<String, Object> parms = new HashMap<>();
            parms.put("parentId" , String.valueOf(renAccess.getparentId()));
            List<RenAccessEntity> list = renAccessService.queryList(parms);
            Long allScore = 0L; // 获取二级目标的总分
            for(RenAccessEntity child : list){
                allScore += child.getaccessScore();
            }
            parent.setaccessScore(allScore);
            renAccessService.update(parent);
        }
    }

}
