package io.renren.modules.oss.controller;

import cn.hutool.json.JSONObject;
import io.renren.common.utils.*;
import io.renren.common.utils.cache.CacheListener;
import io.renren.common.utils.cache.CacheManager;
import io.renren.common.utils.cache.CacheManagerImpl;
import org.apache.commons.lang.StringUtils;
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

    @GetMapping("/getWxToken")
    public R getWxToken(HttpServletRequest request,@RequestParam String wechaturl){
        CacheManager cacheManager = CacheManagerImpl.getInstance();
        // 缓存数据 查看是否有wxticket
        String wxTicket = cacheManager.getCacheByKey("wxTicket") == null ? null : (String)cacheManager.getCacheByKey("wxTicket").getData();
        String appId = "wx3cb1dcfcd74edebf";
        String appsecret = "7b68cbe6b21394a7af0a24d02426f37b";
        if (StringUtils.isNotBlank(wxTicket) ) {
            System.out.println("已经获取微信ticket：");
        } else {
            String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="+ appId + "&secret=" + appsecret;
            String result = HttpClientUtils.doGet(url);
            JSONObject json = new JSONObject(result);
            String access_token = (String)json.get("access_token");
            String ticketUrl = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=" + access_token + "&type=jsapi";
            String ticketRes = HttpClientUtils.doGet(ticketUrl);
            JSONObject ticketJson = new JSONObject(ticketRes);
            wxTicket = (String)ticketJson.get("ticket");
            // 将微信Ticket 放入缓存区
            cacheManager.put("wxTicket", wxTicket,2*60*60*1000L);
            CacheListener cacheListener = new CacheListener(cacheManager);
            cacheListener.startListen();
            System.out.println("重新获取微信ticket：");
        }

        Map<String, String> signMap = WxJsApiSignUtil.sign( wxTicket , wechaturl);
        signMap.put("appId",appId);
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
        String appId = "wxdbda2ef3390a8529";
        String appsecret = "a2389f5383a3ebbb267a4d40a64a47f9";
        CacheManager cacheManager = CacheManagerImpl.getInstance();
        // 缓存数据 查看是否有wxticket
        String wxQrToken = cacheManager.getCacheByKey("wxQrToken") == null ? null : (String)cacheManager.getCacheByKey("wxQrToken").getData();
        try {
            if (StringUtils.isNotBlank(wxQrToken) ) {
                ;
            } else {
                wxQrToken = WxQrCode.getAccessToken(appId,appsecret);
                System.out.println("accessToken;"+ wxQrToken);
                // 将微信wxQrToken 放入缓存区
                cacheManager.put("wxQrToken", wxQrToken,1*60*60*1000L);
                CacheListener cacheListener = new CacheListener(cacheManager);
                cacheListener.startListen();
            }
            String imgData = WxQrCode.getminiqrQr(wxQrToken,projectNo,request);
            return R.ok().put("imgData",imgData);

        } catch (Exception ex) {
            return R.error("生成二维码失败！");
        }
    }
}
