package com.amayadream.emailsystem.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * NAME   :  EmailSystem/com.amayadream.tools
 * Author :  Amayadream
 * Date   :  2015.10.08 14:33
 * TODO   :  正则验证用具类
 */
public class Regex {
    private final static String EMAIL_CHECK =  "^[A-Za-z0-9][\\w\\-\\.]{3,12}@([\\w\\-]+\\.)+[\\w]{2,3}$";
    private final static int[] sizeTable = { 9, 99, 999, 9999, 99999, 999999, 9999999,99999999, 999999999, Integer.MAX_VALUE };

    public static final boolean checkEmail(String email){
        Pattern reg = Pattern.compile(EMAIL_CHECK);
        Matcher matcher = reg.matcher(email);
        return matcher.matches();
    }

    public static final int sizeOfInt(int x){
        for(int i = 0;;i++){
            if(x <= sizeTable[i]){
                return i + 1;
            }
        }
    }

}
