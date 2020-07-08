package io.renren.modules.sys.controller;

import java.util.List;
import java.util.Map;

import io.renren.modules.set.entity.UserGroupEntity;
import io.renren.modules.set.entity.WorkGroupEntity;
import io.renren.modules.set.service.BranchUserService;
import io.renren.modules.set.service.UserGroupService;
import io.renren.modules.set.service.WorkGroupService;
import io.renren.modules.sys.service.SysConfigService;
import io.renren.modules.sys.vo.UserVoEntity;
import org.apache.commons.lang.ArrayUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.renren.common.annotation.SysLog;
import io.renren.common.utils.Constant;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;
import io.renren.common.validator.Assert;
import io.renren.common.validator.ValidatorUtils;
import io.renren.common.validator.group.AddGroup;
import io.renren.common.validator.group.UpdateGroup;
import io.renren.modules.sys.entity.SysUserEntity;
import io.renren.modules.sys.form.PasswordForm;
import io.renren.modules.sys.service.SysUserRoleService;
import io.renren.modules.sys.service.SysUserService;

/**
 * 系统用户
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2016年10月31日 上午10:40:10
 */
@RestController
@RequestMapping("/sys/user")
public class SysUserController extends AbstractController {
	@Autowired
	private SysUserService sysUserService;
	@Autowired
	private SysUserRoleService sysUserRoleService;
	@Autowired
	private WorkGroupService workGroupService;
	@Autowired
	private UserGroupService userGroupService;
	@Autowired
	private SysConfigService sysConfigService;
	@Autowired
	private BranchUserService branchUserService;
	/**
	 * 所有用户列表
	 */
	@GetMapping("/list")
	@RequiresPermissions("sys:user:list")
	public R list(@RequestParam Map<String, Object> params) {
		// 只有超级管理员，才能查看所有管理员列表
		if (getUserId() != Constant.SUPER_ADMIN) {
			params.put("createUserId", getUserId());
		}
		PageUtils page = sysUserService.queryPage(params);

		return R.ok().put("page", page);
	}

	/**
	 * 获取登录的用户信息
	 */
	@GetMapping("/info")
	public R info() {
		return R.ok().put("user", getUser());
	}

	/**
	 * 修改登录用户密码
	 */
	@SysLog("修改密码")
	@PostMapping("/password")
	public R password(@RequestBody PasswordForm form) {
		Assert.isBlank(form.getNewPassword(), "新密码不为能空");

		// sha256加密
		String password = new Sha256Hash(form.getPassword(), getUser().getSalt()).toHex();
		// sha256加密
		String newPassword = new Sha256Hash(form.getNewPassword(), getUser().getSalt()).toHex();

		// 更新密码
		boolean flag = sysUserService.updatePassword(getUserId(), password, newPassword);
		if (!flag) {
			return R.error("原密码不正确");
		}

		return R.ok();
	}

	/**
	 * 用户信息
	 */
	@GetMapping("/info/{userId}")
	@RequiresPermissions("sys:user:info")
	@SysLog("查询用户单条记录")
	public R info(@PathVariable("userId") Long userId) {
		SysUserEntity user = sysUserService.selectById(userId);

		// 获取用户所属的角色列表
		List<Long> roleIdList = sysUserRoleService.queryRoleIdList(userId);
		user.setRoleIdList(roleIdList);

		// 获取用户工作组ID 工作组名称
		UserGroupEntity UGEntity = userGroupService.selectByUID(userId);
		if(UGEntity != null) {
			WorkGroupEntity WGEntity = workGroupService.selectById(UGEntity.getGroupId());
			user.setWorkGroupID(WGEntity.getId());
			user.setWorkGroupName(WGEntity.getName());
		}
		else{
			user.setWorkGroupID(null);
			user.setWorkGroupName(null);
		}
		return R.ok().put("user", user);
	}

	/**
	 * 保存用户
	 */
	@SysLog("保存用户")
	@PostMapping("/save")
//	@RequiresPermissions("sys:user:save")
	public R save(@RequestBody SysUserEntity user) {
		ValidatorUtils.validateEntity(user, AddGroup.class);
		Long userid = user.getUserId();
		if (userid == null) {
			user.setUserId( sysUserService.queryByUserMaxId() + 1);
		}
		user.setCreateUserId(getUserId());
		sysUserService.save(user);

		return R.ok();
	}

	/**
	 * 修改用户
	 */
	@SysLog("修改用户")
	@PostMapping("/update")
	@RequiresPermissions("sys:user:update")
	public R update(@RequestBody SysUserEntity user) {
		ValidatorUtils.validateEntity(user, UpdateGroup.class);

		user.setCreateUserId(getUserId());
		sysUserService.update(user);

		return R.ok();
	}

	/**
	 * 删除用户
	 */
	@SysLog("删除用户")
	@PostMapping("/delete")
	@RequiresPermissions("sys:user:delete")
	public R delete(@RequestBody Long[] userIds) {
		if (ArrayUtils.contains(userIds, 1L)) {
			return R.error("超级管理员不能删除");
		}

		if (ArrayUtils.contains(userIds, getUserId())) {
			return R.error("当前用户不能删除");
		}

		sysUserService.deleteBatch(userIds);

		// （人事管理）用户删除
		if ( sysConfigService.getValue("sysFlag").equals("ren")) {
			for (Long userId : userIds) branchUserService.deleteByUserId(userId);
		}

		return R.ok();
	}

	/**
	 * 获取所有的用户列表
	 * @return
	 */
	@GetMapping("/getAllUserList")
	public R getAllUserList(){
		List<UserVoEntity> list = sysUserService.queryAllUserList();
		return R.ok().put("list" , list);
	}
}
