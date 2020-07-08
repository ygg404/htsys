
package io.renren.modules.sys.service;

import java.awt.image.BufferedImage;

import com.baomidou.mybatisplus.service.IService;

import io.renren.modules.sys.entity.SysCaptchaEntity;

/**
 * 验证码
 *
 * @author Mark sunlightcs@gmail.com
 * @since 2.0.0 2018-02-10
 */
public interface SysCaptchaService extends IService<SysCaptchaEntity> {

	/**
	 * 获取图片验证码
	 */
	BufferedImage getCaptcha(String uuid);

	/**
	 * 验证码效验
	 * 
	 * @param uuid uuid
	 * @param code 验证码
	 * @return true：成功 false：失败
	 */
	boolean validate(String uuid, String code);
}
