package io.renren.common.utils;

import cn.hutool.json.JSONObject;
import sun.misc.BASE64Encoder;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

public class WxQrCode {

    //获取AccessToken路径
    private static final String AccessToken_URL
            = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";//小程序id
    //获取二维码路径
    private static final String WxCode_URL
            = "https://api.weixin.qq.com/wxa/getwxacodeunlimit?access_token=ACCESS_TOKEN";//小程序密钥
    /**
     * 用于获取access_token
     * @return  access_token
     * @throws Exception
     */
    public static String getAccessToken(String appid,String secret) throws Exception {
        String requestUrl = AccessToken_URL.replace("APPID",appid).replace("APPSECRET",secret);
        URL url = new URL(requestUrl);
        // 打开和URL之间的连接
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        // 设置通用的请求属性
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestProperty("Connection", "Keep-Alive");
        connection.setUseCaches(false);
        connection.setDoOutput(true);
        connection.setDoInput(true);

        // 得到请求的输出流对象
        DataOutputStream out = new DataOutputStream(connection.getOutputStream());
        out.writeBytes("");
        out.flush();
        out.close();

        // 建立实际的连接
        connection.connect();
        // 定义 BufferedReader输入流来读取URL的响应
        BufferedReader in = null;
        if (requestUrl.contains("nlp"))
            in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "GBK"));
        else
            in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
        String result = "";
        String getLine;
        while ((getLine = in.readLine()) != null) {
            result += getLine;
        }
        in.close();
        JSONObject jsonObject = new JSONObject(result);
        String accesstoken = (String)jsonObject.get("access_token");
        return accesstoken;
    }


    /*
     * 获取 二维码图片(返回 base64字节码 图片)
     *
     */
    public static String getminiqrQr(String accessToken,String projectNo, HttpServletRequest request) {
        try
        {
//            URL url = new URL("https://api.weixin.qq.com/wxa/getwxacode?access_token="+accessToken);
            String wxCodeURL = WxCode_URL.replace("ACCESS_TOKEN",accessToken);
            URL url = new URL(wxCodeURL);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");// 提交模式
            // conn.setConnectTimeout(10000);//连接超时 单位毫秒
            // conn.setReadTimeout(2000);//读取超时 单位毫秒
            // 发送POST请求必须设置如下两行
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            PrintWriter printWriter = new PrintWriter(httpURLConnection.getOutputStream());
            // 发送请求参数
            JSONObject paramJson = new JSONObject();
            paramJson.put("page", "pages/auth/auth"); //小程序暂未发布我没有带page参数
            paramJson.put("scene", projectNo);
            paramJson.put("width", 200);
            paramJson.put("is_hyaline", true);
            paramJson.put("auto_color", true);
            /**
             * line_color生效
             * paramJson.put("auto_color", false);
             * JSONObject lineColor = new JSONObject();
             * lineColor.put("r", 0);
             * lineColor.put("g", 0);
             * lineColor.put("b", 0);
             * paramJson.put("line_color", lineColor);
             * */

            printWriter.write(paramJson.toString());
            // flush输出流的缓冲
            printWriter.flush();
            //开始获取数据
            BufferedInputStream bis = new BufferedInputStream(httpURLConnection.getInputStream());
            String encode = "";
            if (bis != null) {
                byte[] buffer = new byte[1024];
                //每次读取的字符串长度，如果为-1，代表全部读取完毕
                int len;
                ByteArrayOutputStream outStream = new ByteArrayOutputStream();
                while ((len = bis.read(buffer)) != -1) {
                    outStream.write(buffer, 0, len);
                }
                bis.close();
                byte[] bytes = outStream.toByteArray();
                BASE64Encoder base64Encoder = new BASE64Encoder();
                encode = "data:image/png;base64," + base64Encoder.encode(bytes);
            }
            return encode;

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return "";
    }

}