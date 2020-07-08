package io.renren.common.utils;

public class WxPhoneNumToValidate {
    public static void main(String[] args) {
    	String phone="232332434";
    	phone=phone.substring(5);
        Long number =Long.parseLong(phone) ;
        // 写功能实现把number进行加密
        // 调用
        String result = jiaMi(number);
        System.out.println("加密后的结果是：" + result);
    }
 
    
    public static String getPhoneValidate(String phone){
    	phone=phone.substring(5);
        Long number =Long.parseLong(phone) ;
        // 写功能实现把number进行加密
        String result = jiaMi(number);
    	return result;
    }
    /*
     * 需求：写一个功能，把数据number实现加密。 两个明确： 返回值类型：String 做一个字符串的拼接。 参数列表：int number
     */
    public static String jiaMi(Long number) {
        // 定义数组
    	Long[] arr = new Long[11];
 
        // 定义索引
        int index = 0;
        // 把number中的数据想办法放到数组中
        while (number > 0) {
            arr[index] = number % 10;
            index++;
            number /= 10;
        }

        // 把每个数据加5，然后对10取得余数
        for (int x = 0; x < index; x++) {
            arr[x] += 5;
            arr[x] %= 10;
        }
 
        // 把第一位和最后一位交换
        Long temp = arr[0];
        arr[0] = arr[index - 1];
        arr[index - 1] = temp;
 
        // 把数组的元素拼接成一个字符串返回
        // 定义一个空内容字符串
        String s = "";
 
        for (int x = 0; x < index; x++) {
            s += arr[x];
        }
 
        return s;
    }
}
