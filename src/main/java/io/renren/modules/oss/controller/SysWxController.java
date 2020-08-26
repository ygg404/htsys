package io.renren.modules.oss.controller;

import cn.hutool.json.JSONObject;
import io.renren.common.utils.HttpClient;
import io.renren.common.utils.R;
import io.renren.common.utils.WxJsApiSignUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.cache.CacheManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
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

    private CacheManager cacheManager;

    @GetMapping("/getWxToken")
    public R getWxToken(HttpServletRequest request,@RequestParam String wechaturl){
        // 缓存数据 查看是否有wxticket
        String wxTicket = null;
        String appId = "wx3cb1dcfcd74edebf";
        String appsecret = "7b68cbe6b21394a7af0a24d02426f37b";
        if (StringUtils.isNotBlank(wxTicket) ) {
            ;
        } else {
            String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="+ appId + "&secret=" + appsecret;
            String result = HttpClient.doGet(url);
            JSONObject json = new JSONObject(result);
            String access_token = (String)json.get("access_token");
            String ticketUrl = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=" + access_token + "&type=jsapi";
            String ticketRes = HttpClient.doGet(ticketUrl);
            JSONObject ticketJson = new JSONObject(ticketRes);
            wxTicket = (String)ticketJson.get("ticket");
        }

        // 当前请求路径的url
        String requestUrl = request.getRequestURL().toString();
        Map<String, String> signMap = WxJsApiSignUtil.sign( wxTicket , wechaturl);
        signMap.put("appId",appId);
        return R.ok().put("sign",signMap);
    }

    //测试
    public static void main(String[] args) {
//        String appId = "wx3cb1dcfcd74edebf";
//        String appsecret = "7b68cbe6b21394a7af0a24d02426f37b";
//        String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="+ appId + "&secret=" + appsecret;
//        String result = HttpClient.doGet(url);
//        JSONObject json = new JSONObject(result);
//        String access_token = (String)json.get("access_token");
//        String ticketUrl = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=" + access_token + "&type=jsapi";
//        String ticketRes = HttpClient.doGet(ticketUrl);
//        JSONObject ticketJson = new JSONObject(ticketRes);



//        System.out.println(ticketJson);
    };


}