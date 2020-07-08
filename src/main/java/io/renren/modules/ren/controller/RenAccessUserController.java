package io.renren.modules.ren.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import io.renren.modules.ren.entity.RenAccessEntity;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.renren.modules.ren.entity.RenAccessUserEntity;
import io.renren.modules.ren.service.RenAccessUserService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * 考核目标与用户关系表
 *
 * @author ygg
 * @date 2020-03-03 09:18:50
 */
@RestController
@RequestMapping("ren/accessuser")
public class RenAccessUserController {
    @Autowired
    private RenAccessUserService renAccessUserService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        List<RenAccessUserEntity> list = renAccessUserService.queryList(params);

        return R.ok().put("list", list);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{userId}")

    public R info(@PathVariable("userId") Long userId){
		List<RenAccessUserEntity> accessUserList = renAccessUserService.queryByUserId(userId);

        return R.ok().put("accessUserList", accessUserList);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody RenAccessUserEntity renAccessUser){
        if(renAccessUser.getuserId() == null){
            return R.error("用户 ID 为空！");
        }
        // 保存前先删除有关用户的所有目标Id
        renAccessUserService.deleteByUserId(renAccessUser.getuserId());
        List<RenAccessUserEntity> list = new ArrayList<>();
        for(Long accessId : renAccessUser.getAccessIdList()){
            RenAccessUserEntity entity = new RenAccessUserEntity();
            entity.setuserId(renAccessUser.getuserId());
            entity.setaccessId(accessId);
            list.add(entity);
        }
        if(list.size() > 0) renAccessUserService.insertBatch(list);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("ren:accessuser:update")
    public R update(@RequestBody RenAccessUserEntity renAccessUser){
		renAccessUserService.updateById(renAccessUser);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("ren:accessuser:delete")
    public R delete(@RequestBody Long[] ids){
		renAccessUserService.deleteBatch(ids);

        return R.ok();
    }

}
