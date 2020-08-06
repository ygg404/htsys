package io.renren.common.utils;

import java.util.UUID;

public class UuidUtil {

    public static String get32UUID() {
        String uuid = UUID.randomUUID().toString().trim().replaceAll("-", "");
        return uuid;
    }

    public static  String getShortUUID(){
        String[] uuidChars = {"a", "b", "c", "d", "e", "f",
                "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s",
                "t", "u", "v", "w", "x", "y", "z", "0", "1", "2", "3", "4", "5",
                "6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H", "I",
                "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
                "W", "X", "Y", "Z"};
        String uuidstr = UUID.randomUUID().toString().trim().replaceAll("-", "");
        String result = "";
        for(int i = 0; i < 8; i++){
            String r1 = uuidstr.substring(i*4, (i+1)*4);
            int rnum = Integer.valueOf(r1 , 16);
            result += uuidChars[rnum % 0x3E];
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(get32UUID());
    }
}

