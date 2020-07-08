

package io.renren.modules.sys.dao;

import java.util.List;

import io.renren.modules.sys.vo.UserVoEntity;
import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;

import io.renren.modules.sys.entity.SysUserEntity;
import org.apache.ibatis.annotations.Param;

/**
 * 系统用户
 *
 * @author Mark sunlightcs@gmail.com
 */
@Mapper
public interface SysUserDao extends BaseMapper<SysUserEntity> {

	/**
	 * 查询用户的所有权限
	 * 
	 * @param userId 用户ID
	 */
	List<String> queryAllPerms(Long userId);

	/**
	 * 查询用户的所有菜单ID
	 */
	List<Long> queryAllMenuId(Long userId);

	/**
	 * 根据用户名，查询系统用户
	 */
	SysUserEntity queryByUserName(String username);

	/**
	 * 根据用户账号，查询系统用户
	 */
	SysUserEntity queryByUserAccount(String useraccount);

	/**
	 * 获取用户最大ID
	 * @return
	 */
	Long queryByUserMaxId();

	/**
	 * 获取（相关角色的）用户列表
	 * @return
	 */
	List<UserVoEntity> queryUserList(@Param(value="roleId") Long roleId);

	/**
	 * 获取所有的用户列表
	 */
	List<UserVoEntity> queryAllList();
}
