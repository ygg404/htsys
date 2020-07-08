
package io.renren.modules.sys.dao;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;

import io.renren.modules.sys.entity.SysCaptchaEntity;

/**
 * 验证码
 *
 * @author Mark sunlightcs@gmail.com
 * @since 3.1.0 2018-02-10
 */
@Mapper
public interface SysCaptchaDao extends BaseMapper<SysCaptchaEntity> {

}
