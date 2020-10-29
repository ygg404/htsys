package io.renren.modules.dop.controller;

import java.io.File;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;
import java.util.List;

import io.renren.common.annotation.SysLog;
import io.renren.common.utils.FileUtil;
import io.renren.modules.dop.service.DopDeviceVoService;
import io.renren.modules.dop.vo.DeviceVoEntity;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.renren.modules.dop.entity.DopDeviceEntity;
import io.renren.modules.dop.service.DopDeviceService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;
import org.springframework.web.multipart.MultipartFile;


/**
 * 仪器表
 *
 * @author ygg
 * @date 2020-09-07 10:28:20
 */
@RestController
@RequestMapping("dop/device")
public class DopDeviceController {

    @Value("${spring.file.upDopFolder}")
    private String upDopFolder;

    @Autowired
    private DopDeviceService dopDeviceService;

    @Autowired
    private DopDeviceVoService dopDeviceVoService;

    /**
     * 分页查询
     */
    @RequestMapping("/page")
    @RequiresPermissions("dop:device:page")
    public R page(@RequestParam Map<String, Object> params){
        PageUtils page = dopDeviceService.queryPage(params);

        return R.ok().put("page", page);
    }

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        List<DopDeviceEntity> list = dopDeviceService.queryList(params);

        return R.ok().put("list", list);
    }

    /**
     * 获取仪器的最新的租借情况
     */
    @RequestMapping("/list/{deviceId}")
    public R list(@PathVariable("deviceId") Long deviceId){
        List<DeviceVoEntity> list = dopDeviceVoService.getDeviceLendList(deviceId);

        return R.ok().put("list", list);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
		DopDeviceEntity dopDevice = dopDeviceService.selectById(id);

        return R.ok().put("dopDevice", dopDevice);
    }

    /**
     * 保存
     */
    @SysLog("保存仪器内容")
    @RequestMapping("/save")
    @RequiresPermissions("dop:device:save")
    public R save(@RequestBody DopDeviceEntity dopDevice){
        dopDevice.setCreateTime(new Date());
		dopDeviceService.save(dopDevice);

        return R.ok();
    }

    /**
     * 修改
     */
    @SysLog("修改仪器内容")
    @RequestMapping("/update")
    @RequiresPermissions("dop:device:update")
    public R update(@RequestBody DopDeviceEntity dopDevice){
		dopDeviceService.updateById(dopDevice);

        return R.ok();
    }

    /**
     * 删除
     */
    @SysLog("删除仪器")
    @RequestMapping("/delete")
    @RequiresPermissions("dop:device:delete")
    public R delete(@RequestBody Long[] ids){
		dopDeviceService.deleteBatch(ids);

        return R.ok();
    }

    /**
     * 上传仪器文件
     * @param file
     * @return
     */
    @SysLog("上传仪器文件")
    @RequestMapping("/upDopFile")
    public R uploadFile(@PathVariable MultipartFile file) {
        String fileName = "";
        try {
            String filePath = FileUtil.setFilePath(upDopFolder, file.getOriginalFilename() , true);
            File dest = new File(filePath);
            fileName = dest.getName();
            file.transferTo(dest);
        }catch (Exception ex){
            return R.error(ex.getMessage());
        }
        return R.ok().put("fileName", fileName);
    }
}
