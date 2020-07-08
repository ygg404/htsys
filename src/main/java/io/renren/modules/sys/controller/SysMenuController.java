
package io.renren.modules.sys.controller;

import java.util.List;
import java.util.Set;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.renren.common.annotation.SysLog;
import io.renren.common.exception.RRException;
import io.renren.common.utils.Constant;
import io.renren.common.utils.R;
import io.renren.modules.sys.entity.SysMenuEntity;
import io.renren.modules.sys.service.ShiroService;
import io.renren.modules.sys.service.SysMenuService;

/**
 * 系统菜单
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2016年10月27日 下午9:58:15
 */
@RestController
@RequestMapping("/sys/menu")
public class SysMenuController extends AbstractController {
	@Autowired
	private SysMenuService sysMenuService;
	@Autowired
	private ShiroService shiroService;

	/**
	 * 导航菜单
	 */
	@GetMapping("/nav")
	public R nav() {
		List<SysMenuEntity> menuList = sysMenuService.getUserMenuList(getUserId());
		//获取 按钮列表（含有路由的 按钮）
		List<SysMenuEntity> btnList = sysMenuService.getUserBtnList(getUserId());
		Set<String> permissions = shiroService.getUserPermissions(getUserId());
		return R.ok().put("menuList", menuList).put("btnList",btnList).put("permissions", permissions);
	}

	/**
	 * 所有菜单列表
	 */
	@GetMapping("/list")
//	@RequiresPermissions("sys:menu:list")
	public List<SysMenuEntity> list() {
		List<SysMenuEntity> menuList = sysMenuService.selectList(new EntityWrapper<SysMenuEntity>().orderBy("orderNum" , true));
		for (SysMenuEntity sysMenuEntity : menuList) {
//			sysMenuEntity.setOpen(true);
			SysMenuEntity parentMenuEntity = sysMenuService.selectById(sysMenuEntity.getParentId());
			if (parentMenuEntity != null) {
				sysMenuEntity.setParentName(parentMenuEntity.getName());
			}
		}

		return menuList;
	}

	/**
	 * 所有(有效的)菜单列表
	 */
	@GetMapping("/validlist")
//	@RequiresPermissions("sys:menu:list")
	public List<SysMenuEntity> validlist() {
		List<SysMenuEntity> menuList = sysMenuService.selectList(new EntityWrapper<SysMenuEntity>().eq("valid_flag", true).orderBy("orderNum" , true));
		for (SysMenuEntity sysMenuEntity : menuList) {
//			sysMenuEntity.setOpen(true);
			SysMenuEntity parentMenuEntity = sysMenuService.selectById(sysMenuEntity.getParentId());
			if (parentMenuEntity != null) {
				sysMenuEntity.setParentName(parentMenuEntity.getName());
			}
		}

		return menuList;
	}

	/**
	 * 选择菜单(添加、修改菜单)
	 */
	@GetMapping("/select")
//	@RequiresPermissions("sys:menu:select")
	public R select() {
		// 查询列表数据
		List<SysMenuEntity> menuList = sysMenuService.queryNotButtonList();

		// 添加顶级菜单
		SysMenuEntity root = new SysMenuEntity();
		root.setMenuId(0L);
		root.setName("一级菜单");
		root.setParentId(-1L);
		root.setOpen(true);
		menuList.add(root);

		return R.ok().put("menuList", menuList);
	}

	/**
	 * 菜单信息
	 */
	@GetMapping("/info/{menuId}")
//	@RequiresPermissions("sys:menu:info")
	public R info(@PathVariable("menuId") Long menuId) {
		SysMenuEntity menu = sysMenuService.selectById(menuId);
		return R.ok().put("menu", menu);
	}

	/**
	 * 保存
	 */
	@SysLog("保存菜单")
	@PostMapping("/save")
//	@RequiresPermissions("sys:menu:save")
	public R save(@RequestBody SysMenuEntity menu) {
		// 数据校验
		verifyForm(menu);

		sysMenuService.insert(menu);

		return R.ok();
	}

	/**
	 * 修改
	 */
	@SysLog("修改菜单")
	@PostMapping("/update")
//	@RequiresPermissions("sys:menu:update")
	public R update(@RequestBody SysMenuEntity menu) {
		// 数据校验
		verifyForm(menu);

		sysMenuService.updateById(menu);

		return R.ok();
	}

	/**
	 * 删除
	 */
	@SysLog("删除菜单")
	@PostMapping("/delete/{menuId}")
//	@RequiresPermissions("sys:menu:delete")
	public R delete(@PathVariable("menuId") long menuId) {
//		if (menuId <= 31) {
//			return R.error("系统菜单，不能删除");
//		}

		// 判断是否有子菜单或按钮
		List<SysMenuEntity> menuList = sysMenuService.queryListParentId(menuId);
		if (menuList.size() > 0) {
			return R.error("请先删除子菜单或按钮");
		}

		sysMenuService.delete(menuId);

		return R.ok();
	}

	/**
	 * 验证参数是否正确
	 */
	private void verifyForm(SysMenuEntity menu) {
		if (StringUtils.isBlank(menu.getName())) {
			throw new RRException("菜单名称不能为空");
		}

		if (menu.getParentId() == null) {
			throw new RRException("上级菜单不能为空");
		}

		// 菜单
		if (menu.getType() == Constant.MenuType.MENU.getValue()) {
			if (StringUtils.isBlank(menu.getUrl())) {
				throw new RRException("菜单URL不能为空");
			}
		}

		// 上级菜单类型
		int parentType = Constant.MenuType.CATALOG.getValue();
		if (menu.getParentId() != 0) {
			SysMenuEntity parentMenu = sysMenuService.selectById(menu.getParentId());
			parentType = parentMenu.getType();
		}

		// 目录、菜单
		if (menu.getType() == Constant.MenuType.CATALOG.getValue()
				|| menu.getType() == Constant.MenuType.MENU.getValue()) {
			if (parentType != Constant.MenuType.CATALOG.getValue()) {
				throw new RRException("上级菜单只能为目录类型");
			}
			return;
		}

		// 按钮
		if (menu.getType() == Constant.MenuType.BUTTON.getValue()) {
			if (parentType != Constant.MenuType.MENU.getValue()) {
				throw new RRException("上级菜单只能为菜单类型");
			}
			return;
		}
	}
}
