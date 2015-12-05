package com.amayadream.emailsystem.util;

import java.io.File;

/**
 * NAME   :  EmailSystem/com.amayadream.emailsystem.util
 * Author :  Amayadream
 * Date   :  2015.12.04 22:58
 * TODO   :
 */
public class FileUtil {

    /**
     * 判断路径后是否有反斜杠,如果有则直接返回路径,如果没有则返回添加反斜杠后的路径
     * @param path  路径
     * @return
     */
    public String checkPathEnd(String path){
        if(path.endsWith("\\")){
            return path;
        }else{
            return path + "\\";
        }
    }

    /**
     * 判断字符串后是否有斜杠,如果有则返回字符串,没有则返回添加斜杠的字符串......
     * 这尼玛真是坑爹,正斜杠反斜杠傻傻分不清楚,就这么写了,不管了
     * @param string    字符串
     * @return
     */
    public String checkStringEnd(String string){
        if(string.endsWith("/")){
            return string;
        }else{
            return string + "/";
        }
    }

    /**
     * 判断文件是否存在
     * @param path  文件路径,有没有结尾反斜杠
     * @param fileName  文件名
     * @return
     */
    public boolean isFileExists(String path, String fileName){
        File file = new File(checkPathEnd(path) + fileName);
        if(file.exists()){
            return true;
        }else{
            return false;
        }
    }

    /**
     * 循环判断文件是否存在,如果存在则给文件后面加上特定标记
     * @param path      文件路径
     * @param fileName  文件名
     * @param mark      标记
     * @return  返回可用的文件名
     */
    public String getNotrepeatFileName(String path, String fileName, String mark){
        if(fileName.indexOf(".") != -1){    //如果文件有后缀名
            while(isFileExists(path,fileName)){
                String name = fileName.substring(0, fileName.lastIndexOf("."));
                String prefix=fileName.substring(fileName.lastIndexOf(".")+1);
                fileName = name + mark + "." + prefix;
                getNotrepeatFileName(path, fileName, mark);
            }
        }else{      //如果没有后缀名
            while(isFileExists(path, fileName)){
                fileName = fileName + mark;
                getNotrepeatFileName(path, fileName, mark);
            }
        }
        return fileName;
    }
}
