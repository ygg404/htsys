
package io.renren.modules.sys.controller;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import io.renren.common.utils.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.renren.common.annotation.SysLog;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;
import io.renren.common.validator.ValidatorUtils;
import io.renren.modules.sys.entity.SysConfigEntity;
import io.renren.modules.sys.service.SysConfigService;

/**
 * 系统配置信息
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2016年12月4日 下午6:55:53
 */
@RestController
@RequestMapping("/sys/config")
public class SysConfigController extends AbstractController {
	@Autowired
	private SysConfigService sysConfigService;

	/**
	 * 配置分页列表
	 */
	@GetMapping("/page")
//	@RequiresPermissions("sys:config:list")
	public R page(@RequestParam Map<String, Object> params) {
		PageUtils page = sysConfigService.queryPage(params);

		return R.ok().put("page", page);
	}

	/**
	 * 配置列表
	 */
	@GetMapping("/list")
//	@RequiresPermissions("sys:config:list")
	public R list(@RequestParam Map<String, Object> params) {
		List<SysConfigEntity> list = sysConfigService.queryList(params);

		return R.ok().put("list", list);
	}

	/**
	 * 配置信息
	 */
	@GetMapping("/info/{id}")
//	@RequiresPermissions("sys:config:info")
	public R info(@PathVariable("id") Long id) {
		SysConfigEntity config = sysConfigService.selectById(id);

		return R.ok().put("config", config);
	}

	/**
	 * 配置信息(通过key 获取 名称)
	 */
	@GetMapping("/getNameByKey")
//	@RequiresPermissions("sys:config:info")
	public R getNameByKey(@RequestParam Map<String, Object> params) {
		SysConfigEntity config = sysConfigService.selectOne(
				new EntityWrapper<SysConfigEntity>().eq( "param_key" , params.get("key") )
		);

		return R.ok().put("config", config);
	}

	/**
	 * 保存配置
	 */
	@SysLog("保存配置")
	@PostMapping("/save")
//	@RequiresPermissions("sys:config:save")
	public R save(@RequestBody SysConfigEntity config) {
		ValidatorUtils.validateEntity(config);

		sysConfigService.save(config);

		return R.ok();
	}

	/**
	 * 修改配置
	 */
	@SysLog("修改配置")
	@PostMapping("/update")
//	@RequiresPermissions("sys:config:update")
	public R update(@RequestBody SysConfigEntity config) {
		ValidatorUtils.validateEntity(config);

		sysConfigService.update(config);

		return R.ok();
	}

	/**
	 * 删除配置
	 */
	@SysLog("删除配置")
	@PostMapping("/delete")
//	@RequiresPermissions("sys:config:delete")
	public R delete(@RequestBody Long[] ids) {
		sysConfigService.deleteBatch(ids);

		return R.ok();
	}

}
