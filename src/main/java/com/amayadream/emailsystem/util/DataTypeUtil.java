package com.amayadream.emailsystem.util;

import java.util.ArrayList;
import java.util.List;

/**
 * NAME   :  EmailSystem/com.amayadream.emailsystem.util
 * Author :  Amayadream
 * Date   :  2015.12.04 10:00
 * TODO   :  数据类型转换工具类
 */
public class DataTypeUtil {

    public String[] listToArray(List list){
        int size = list.size();
        if(size != 0){
            return (String[]) list.toArray(new String[size]);
        }else {
            return null;
        }
    }

    public static void main(String[] args) {
    }
}
