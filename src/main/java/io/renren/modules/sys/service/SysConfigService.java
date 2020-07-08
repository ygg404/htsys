
package io.renren.modules.sys.service;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.service.IService;

import io.renren.common.utils.PageUtils;
import io.renren.modules.sys.entity.SysConfigEntity;

/**
 * 系统配置信息
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2016年12月4日 下午6:49:01
 */
public interface SysConfigService extends IService<SysConfigEntity> {
	/**
	 * 分页查询
	 * @param params
	 * @return
	 */
	PageUtils queryPage(Map<String, Object> params);
	/**
	 * 列表查询
	 * @param params
	 * @return
	 */
	List<SysConfigEntity> queryList(Map<String, Object> params);
	/**
	 * 保存配置信息
	 */
	public void save(SysConfigEntity config);

	/**
	 * 更新配置信息
	 */
	public void update(SysConfigEntity config);

	/**
	 * 根据key，更新value
	 */
	public void updateValueByKey(String key, String value);

	/**
	 * 删除配置信息
	 */
	public void deleteBatch(Long[] ids);

	/**
	 * 根据key，获取配置的value值
	 * 
	 * @param key key
	 */
	public String getValue(String key);

	/**
	 * 根据key，获取value的Object对象
	 * 
	 * @param key   key
	 * @param clazz Object对象
	 */
	public <T> T getConfigObject(String key, Class<T> clazz);

}
