package io.renren.modules.oss.controller;

import cn.hutool.json.JSONObject;
import io.renren.common.utils.*;
import io.renren.common.utils.cache.CacheListener;
import io.renren.common.utils.cache.CacheManager;
import io.renren.common.utils.cache.CacheManagerImpl;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * 微信接口
 *
 * @author YGG
 * @date 2017-03-25 12:13:26
 */
@RestController
@RequestMapping("sys/wx")
public class SysWxController {

    Logger logger = LoggerFactory.getLogger(SysWxController.class);

    @Autowired
    public RedisUtils redisUtils;

    @Value("${wx.miniId}")
    private String miniId;

    @Value("${wx.miniSecret}")
    private String miniSecret;

    @Value("${wx.accountId}")
    private String accountId;

    @Value("${wx.accountSecret}")
    private String accountSecret;

    @GetMapping("/getWxToken")
    public R getWxToken(HttpServletRequest request,@RequestParam String wechaturl){
        String wxTicket = redisUtils.get("wxTicket");
        if (StringUtils.isNotBlank(wxTicket) ) {
            logger.warn("已经获取微信ticket：");
        } else {
            String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="+ miniId + "&secret=" + miniSecret;
            String result = HttpClientUtils.doGet(url);
            JSONObject json = new JSONObject(result);
            String access_token = (String)json.get("access_token");
            String ticketUrl = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=" + access_token + "&type=jsapi";
            String ticketRes = HttpClientUtils.doGet(ticketUrl);
            JSONObject ticketJson = new JSONObject(ticketRes);
            wxTicket = (String)ticketJson.get("ticket");
            // 将微信Ticket 放入缓存区
            redisUtils.set("wxTicket", wxTicket,2*60*60);
            logger.info("重新获取微信ticket：" + wxTicket);
        }

        Map<String, String> signMap = WxJsApiSignUtil.sign( wxTicket , wechaturl);
        signMap.put("appId",miniId);
        return R.ok().put("sign",signMap);
    }

    /**
     * 生成微信小程序二维码（带项目编号参数）
     * @param request
     * @param projectNo
     * @return
     */
    @GetMapping("/getWxQR")
    public R getWxQR(HttpServletRequest request, @RequestParam String projectNo){
        // 缓存数据 查看是否有wxQrToken
        String wxQrToken = redisUtils.get("wxQrToken");
        try {
            if (StringUtils.isNotBlank(wxQrToken) ) {
                ;
            } else {
                wxQrToken = WxQrCode.getAccessToken(accountId,accountSecret);
                logger.info("accessToken;"+ wxQrToken);
                // 将微信wxQrToken 放入缓存区
                redisUtils.set("wxQrToken", wxQrToken,1*60*60);
            }
            String imgData = WxQrCode.getminiqrQr(wxQrToken,projectNo,request);
            return R.ok().put("imgData",imgData);

        } catch (Exception ex) {
            return R.error("生成二维码失败！");
        }
    }
}
