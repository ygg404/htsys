package io.renren.modules.sys.service.impl;

import java.util.*;

import io.renren.modules.app.entity.UserEntity;
import io.renren.modules.set.entity.UserGroupEntity;
import io.renren.modules.set.entity.WorkGroupEntity;
import io.renren.modules.set.service.UserGroupService;
import io.renren.modules.set.service.WorkGroupService;
import io.renren.modules.sys.vo.UserVoEntity;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import io.renren.common.exception.RRException;
import io.renren.common.utils.Constant;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;
import io.renren.modules.sys.dao.SysUserDao;
import io.renren.modules.sys.entity.SysUserEntity;
import io.renren.modules.sys.service.SysRoleService;
import io.renren.modules.sys.service.SysUserRoleService;
import io.renren.modules.sys.service.SysUserService;

/**
 * 系统用户
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2016年9月18日 上午9:46:09
 */
@Service("sysUserService")
public class SysUserServiceImpl extends ServiceImpl<SysUserDao, SysUserEntity> implements SysUserService {
	@Autowired
	private SysUserRoleService sysUserRoleService;
	@Autowired
	private SysRoleService sysRoleService;
	@Autowired
	private WorkGroupService workGroupService;
	@Autowired
	private UserGroupService userGroupService;

	@Override
	public PageUtils queryPage(Map<String, Object> params) {
		String username = (String) params.get("username");
		Long createUserId = (Long) params.get("createUserId");
		Page<SysUserEntity> page = this.selectPage(new Query<SysUserEntity>(params).getPage(),
				new EntityWrapper<SysUserEntity>().like(StringUtils.isNotBlank(username), "username", username));
//						.eq(createUserId != null, "create_user_id", createUserId));
		//work_group 工作组数据
		List<WorkGroupEntity> WGList = workGroupService.queryList(params);

		//user_group 用户ID工作组ID数据
		List<UserGroupEntity> UGList = userGroupService.ToSelectqueryList();

		//临时变量
		Long TempGID;

		//设置用户组名称
		for (SysUserEntity entity: page.getRecords() ) {
			//设置角色ID List列表
			List<Long> RList = new ArrayList<Long>();
			for (Long urid : sysUserRoleService.queryRoleIdList(entity.getUserId())){
				RList.add(urid);
			}
			entity.setRoleIdList(RList);

			for(UserGroupEntity UGEntity : UGList){
				if(entity.getUserId() == UGEntity.getUserId()){
					TempGID = UGEntity.getGroupId();
					for(WorkGroupEntity WGEntity : WGList){
						if(TempGID == WGEntity.getId()){
							//设置
							entity.setWorkGroupName(WGEntity.getName());
							entity.setWorkGroupID(WGEntity.getId());
						}
					}
				}
			}
		}

		return new PageUtils(page);
	}

	@Override
	public List<String> queryAllPerms(Long userId) {
		return baseMapper.queryAllPerms(userId);
	}

	@Override
	public List<Long> queryAllMenuId(Long userId) {
		return baseMapper.queryAllMenuId(userId);
	}

	@Override
	public SysUserEntity queryByUserName(String username) {
		return baseMapper.queryByUserName(username);
	}

	@Override
	public SysUserEntity queryByUserAccount(String useraccount) {
		return baseMapper.queryByUserAccount(useraccount);
	}

	@Override
	public Long queryByUserMaxId() {
		return baseMapper.queryByUserMaxId();
	}

	@Override
	@Transactional
	public void save(SysUserEntity user) {
		user.setCreateTime(new Date());
		// sha256加密
		String salt = RandomStringUtils.randomAlphanumeric(20);
		user.setPassword(new Sha256Hash(user.getPassword(), salt).toHex());
		user.setSalt(salt);
		this.insert(user);

		// 检查角色是否越权
		//checkRole(user);

		// 保存用户与角色关系
		sysUserRoleService.saveOrUpdate(user.getUserId(), user.getRoleIdList());

		// 保存  user_group 数据
		if(user.getWorkGroupID() != null) {
			UserGroupEntity OperEntity = new UserGroupEntity();
			OperEntity.setUserId(user.getUserId());
			OperEntity.setGroupId(user.getWorkGroupID());
			userGroupService.save(OperEntity);
		}

	}

	@Override
	@Transactional
	public void update(SysUserEntity user) {
		if (StringUtils.isBlank(user.getPassword())) {
			user.setPassword(null);
		} else {
			user.setPassword(new Sha256Hash(user.getPassword(), user.getSalt()).toHex());
		}
		this.updateById(user);

		// 检查角色是否越权
		//checkRole(user);

		// 操作 sys_user_role 先删后增
		sysUserRoleService.saveOrUpdate(user.getUserId(), user.getRoleIdList());

		// 操作 sys_user_group 先删后增

		//参数 user_id group_id  修改  通过user_id
		userGroupService.saveOrUpdate(user.getUserId(),user.getWorkGroupID());

	}

	@Override
	public void deleteBatch(Long[] userId) {
		//用户数据批量删除
		this.deleteBatchIds(Arrays.asList(userId));

		//批量删除 user_role
		sysUserRoleService.deleteBatchByUId(userId);

		//批量删除 user_group
		userGroupService.deleteBatchByUId(userId);

	}

	@Override
	public boolean updatePassword(Long userId, String password, String newPassword) {
		SysUserEntity userEntity = new SysUserEntity();
		userEntity.setPassword(newPassword);
		return this.update(userEntity,
				new EntityWrapper<SysUserEntity>().eq("user_id", userId).eq("password", password));
	}

	/**
	 * 检查角色是否越权
	 */
	private void checkRole(SysUserEntity user) {
		if (user.getRoleIdList() == null || user.getRoleIdList().size() == 0) {
			return;
		}
		// 如果不是超级管理员，则需要判断用户的角色是否自己创建
		if (user.getCreateUserId() == Constant.SUPER_ADMIN) {
			return;
		}

		// 查询用户创建的角色列表
		List<Long> roleIdList = sysRoleService.queryRoleIdList(user.getCreateUserId());

		// 判断是否越权
		if (!roleIdList.containsAll(user.getRoleIdList())) {
			throw new RRException("新增用户所选角色，不是本人创建");
		}
	}

	@Override
	public List<UserVoEntity> getUserList(Long roleId){
		List<UserVoEntity> userList = this.baseMapper.queryUserList(roleId);
		return  userList;
	}

	/**
	 * 获取所有的用户列表
	 */
	@Override
	public List<UserVoEntity> queryAllUserList(){
		return this.baseMapper.queryAllList();
	}
}
