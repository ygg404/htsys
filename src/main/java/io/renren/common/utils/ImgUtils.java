package io.renren.common.utils;

import sun.misc.BASE64Decoder;

import javax.imageio.ImageIO;
import javax.xml.bind.DatatypeConverter;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ImgUtils {
    /**
     * base64转图片
     * @param base64str base64码
     * @param savePath 图片路径
     * @return
     */
    public static boolean GenerateImage(String base64str, String savePath) {
        //对字节数组字符串进行Base64解码并生成图片
        if (base64str == null) {
            return false;
        }
        BASE64Decoder decoder;
        decoder = new BASE64Decoder();
        try {
            //Base64解码
            byte[] b = decoder.decodeBuffer(base64str);
            for (int i = 0; i < b.length; ++i) {
                //调整异常数据
                if (b[i] < 0) {
                    b[i] += 256;
                }
            }
            //生成jpeg图片
            OutputStream out = new FileOutputStream(savePath);
            out.write(b);
            out.flush();
            out.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * base64转图片
     * @param base64Code base64码
     */
    public static void convertBase64ToImage(String base64Code, String imgPath) throws Exception {
        // 匹配图片头部
        String regHeader = "^data:?.*base64,";
        Matcher matcher = Pattern.compile(regHeader).matcher(base64Code);
        int count = 0;
        String regex = "";
        String imgType = ""; // 图片文件格式
        while (matcher.find()){
            regex = matcher.group();
            count ++;
            break;
        }
        if(count < 1){
            throw new Exception("图片格式有误！");
        } else{
            imgType = regex.replace("data:image/", "").replace(";base64,", "");
        }
        try {
            // 图片路径不存在 则创建路径
            File imgfile = new File(imgPath);
            File fileParent = imgfile.getParentFile();
            if(!fileParent.exists()){
                fileParent.mkdirs();
            }
            imgfile.createNewFile();

            BASE64Decoder decoder = new sun.misc.BASE64Decoder();
            byte[] bytes1 = decoder.decodeBuffer(base64Code.replaceFirst( regex , ""));
            ByteArrayInputStream bais = new ByteArrayInputStream(bytes1);
            BufferedImage bi1 =ImageIO.read(bais);
            File file = new File(imgPath);//可以是jpg,png,gif格式
            ImageIO.write(bi1, imgType, file);
        }catch (Exception e){
            throw e;
        }

    }
}
