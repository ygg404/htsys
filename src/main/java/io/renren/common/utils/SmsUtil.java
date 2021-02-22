package io.renren.common.utils;

import com.github.qcloudsms.SmsSingleSender;
import com.github.qcloudsms.SmsSingleSenderResult;
import org.springframework.beans.factory.annotation.Value;

/**
 * 腾讯云短信服务接口
 */
public class SmsUtil {

    //    SDK AppID 1400485470
    //    SDK AppID是短信应用的唯一标识，调用短信API接口时，需要提供该参数。
    //    App Key 8e676043f8ceb484af18da93b36217cb
    //    短信签名“杰信测绘”
    private final static int appId = 1400485470;
    private final static String appKey = "8e676043f8ceb484af18da93b36217cb";
    private final static String smsSign = "杰信测绘";

    public static void sendMessage(int templateId, String phoneNumber,String[] replacedValues) throws Exception{
        SmsSingleSender ssender = new SmsSingleSender(appId,appKey);
        //地区，电话，模板ID，验证码，签名
        try {
            SmsSingleSenderResult result = ssender.sendWithParam("86", phoneNumber, templateId, replacedValues, smsSign, "", "");
            if (!result.errMsg.equals("OK")) {
                throw new Exception(result.errMsg);
            }
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    /**
     * 发送项目安排短信
     * @param phoneNumber
     * @param replacedValues
     * @return
     */
    public static R sendAllocationMsg(String phoneNumber,String[] replacedValues) {
        try {
            sendMessage(874147,phoneNumber,replacedValues);
            return R.ok();
        } catch (Exception e) {
            return R.error(e.getMessage());
        }
    }

    /**
     * 发送验证码短信
     * @param phoneNumber
     * @param replacedValues
     * @return
     */
    public static R sendVerifyCodeMsg(String phoneNumber,String[] replacedValues) {
        try {
            sendMessage(873770,phoneNumber,replacedValues);
            return R.ok();
        } catch (Exception e) {
            return R.error(e.getMessage());
        }
    }

    public static void main(String[] args) {
        try {
            String phoneNumber = "15917919286";
            String[] replacedValues= {"787878","勇哥","第二组","11111"};
            String[] replacedValue = {"434355"};
            sendVerifyCodeMsg(phoneNumber,replacedValue);  // 888878 是templateId “短信签名名字” 这个是腾讯天申请的短信签名  replacedValues 是短信内容需要的参数
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
