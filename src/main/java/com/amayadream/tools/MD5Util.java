package com.amayadream.tools;

import java.security.MessageDigest;

/**
 * NAME   :  EmailSystem/com.amayadream.tools
 * Author :  Amayadream
 * Date   :  2015.10.13 09:26
 * TODO   :  MD5加密工具类
 */

public class MD5Util {
    /**
     * 对一个字符串进行MD5加密,输出16进制的字符串密文
     * @param s
     * @return
     */
    public final static String MD5(String s) {
        char hexDigits[]={'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};

        try {
            byte[] btInput = s.getBytes();
            // 获得MD5摘要算法的 MessageDigest 对象
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            // 使用指定的字节更新摘要
            mdInst.update(btInput);
            // 获得密文
            byte[] md = mdInst.digest();
            // 把密文转换成十六进制的字符串形式
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 测试组
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(MD5Util.MD5("admin"));
        System.out.println(MD5Util.MD5("123456"));
    }
}
