package io.renren.modules.sys.controller;

import io.renren.common.utils.R;
import io.renren.modules.sys.entity.SysKbiEntity;
import io.renren.modules.sys.service.SysRoleKbiService;
import io.renren.modules.sys.vo.RoleKBIVoEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("sys/kbiVo")
public class SysKbiVoController {

    @Autowired
    public SysRoleKbiService sysRoleKbiService;

    /**
     * 岗位与KBI列表
     */
    @RequestMapping("/roleKBIList")
//    @RequiresPermissions("ren:syskbi:list")
    public R roleKBIList(){
        List<RoleKBIVoEntity> list = sysRoleKbiService.queryRoleKBIList();
        return R.ok().put("list", list);
    }
}
