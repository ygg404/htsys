package io.renren.modules.webapi.controller;

import io.renren.common.utils.R;
import io.renren.common.utils.RedisUtils;
import io.renren.common.utils.SmsUtil;
import io.renren.common.utils.StringUtil;
import io.renren.modules.webapi.vo.SmsVoEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
@RequestMapping("/webapi/sms")
public class SmsController {

    @Autowired
    private RedisUtils redisUtils;

    /**
     * 获取验证码
     */
    @RequestMapping("/getAuthcode")
    public R getAuthcode(@RequestBody SmsVoEntity smsVoEntity){
        SmsVoEntity oEntity = redisUtils.get(smsVoEntity.getPhoneNum(),SmsVoEntity.class);
        if (StringUtil.isEmpty(oEntity)) {
            String authCode = String.format("%04d",new Random().nextInt(9999));
            smsVoEntity.setAuthCode(authCode);
            SmsUtil.sendVerifyCodeMsg(smsVoEntity.getPhoneNum(),new String[]{ authCode });
            redisUtils.set(smsVoEntity.getPhoneNum(),smsVoEntity,90);
        } else {
            return R.error("该手机号已发过短信，请勿重复操作！");
        }

        return R.ok();
    }

    /**
     * 登录验证
     */
    @RequestMapping("/softLogin")
    public R softLogin(@RequestBody SmsVoEntity smsVoEntity){
        SmsVoEntity oEntity = redisUtils.get(smsVoEntity.getPhoneNum(),SmsVoEntity.class);
        if (StringUtil.isEmpty(oEntity)) {
            return R.error("请先填写手机号并获取验证码！");
        } else {
            // 判断是否符合
            if(!oEntity.equals(smsVoEntity)){
                return R.error("验证码和手机号不一致！");
            }
        }

        return R.ok();
    }
}
