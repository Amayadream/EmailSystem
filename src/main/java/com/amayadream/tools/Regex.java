package com.amayadream.tools;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * NAME   :  EmailSystem/com.amayadream.tools
 * Author :  Amayadream
 * Date   :  2015.10.08 14:33
 * TODO   :  正则验证用具类
 */
public class Regex {
    private static final String EMAIL_CHECK =  "^[A-Za-z0-9][\\w\\-\\.]{3,12}@([\\w\\-]+\\.)+[\\w]{2,3}$";

    public static final boolean checkEmail(String email){
        Pattern reg = Pattern.compile(EMAIL_CHECK);
        Matcher matcher = reg.matcher(email);
        return matcher.matches();
    }
}
