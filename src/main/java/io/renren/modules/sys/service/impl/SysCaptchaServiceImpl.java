
package io.renren.modules.sys.service.impl;

import java.awt.image.BufferedImage;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.google.code.kaptcha.Producer;

import io.renren.common.exception.RRException;
import io.renren.common.utils.DateUtils;
import io.renren.modules.sys.dao.SysCaptchaDao;
import io.renren.modules.sys.entity.SysCaptchaEntity;
import io.renren.modules.sys.service.SysCaptchaService;

/**
 * 验证码
 *
 * @author Mark sunlightcs@gmail.com
 * @since 2.0.0 2018-02-10
 */
@Service("sysCaptchaService")
public class SysCaptchaServiceImpl extends ServiceImpl<SysCaptchaDao, SysCaptchaEntity> implements SysCaptchaService {
	@Autowired
	private Producer producer;

	@Override
	public BufferedImage getCaptcha(String uuid) {
		if (StringUtils.isBlank(uuid)) {
			throw new RRException("uuid不能为空");
		}
		// 生成文字验证码
		String code = producer.createText();

		SysCaptchaEntity captchaEntity = new SysCaptchaEntity();
		captchaEntity.setUuid(uuid);
		captchaEntity.setCode(code);
		// 5分钟后过期
		captchaEntity.setExpireTime(DateUtils.addDateMinutes(new Date(), 5));
		this.insert(captchaEntity);

		return producer.createImage(code);
	}

	@Override
	public boolean validate(String uuid, String code) {
		SysCaptchaEntity captchaEntity = this.selectOne(new EntityWrapper<SysCaptchaEntity>().eq("uuid", uuid));
		if (captchaEntity == null) {
			return false;
		}

		// 删除验证码
		this.deleteById(uuid);

		if (captchaEntity.getCode().equalsIgnoreCase(code)
				&& captchaEntity.getExpireTime().getTime() >= System.currentTimeMillis()) {
			return true;
		}

		return false;
	}
}
