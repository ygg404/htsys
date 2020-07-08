package io.renren.common.utils;

/**
 * 字符串相关方法
 */
public class StringUtil {

    /**
     * 将以逗号分隔的字符串转换成字符串数组
     *
     * @param valStr
     * @return String[]
     */
    public static String[] StrList(String valStr) {
        int i = 0;
        String TempStr = valStr;
        String[] returnStr = new String[valStr.length() + 1
                - TempStr.replace(",", "").length()];
        valStr = valStr + ",";
        while (valStr.indexOf(',') > 0) {
            returnStr[i] = valStr.substring(0, valStr.indexOf(','));
            valStr = valStr.substring(valStr.indexOf(',') + 1, valStr.length());

            i++;
        }
        return returnStr;
    }

    public static boolean isEmpty(String str) {
        if (str == null || "".equals(str.trim()))
            return true;
        else
            return false;
    }

    public static boolean isEmpty(Object obj) {
        if (obj == null)
            return true;
        else
            return false;
    }

    public static boolean stringIsNull(String paramString) {
        return (paramString == null) || ("".equals(paramString.trim()));
    }

    public static boolean isNull(String paramString) {
        return stringIsNull(paramString);
    }
}
