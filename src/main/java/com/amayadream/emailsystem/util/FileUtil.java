package com.amayadream.emailsystem.util;

import java.io.File;

/**
 * NAME   :  EmailSystem/com.amayadream.emailsystem.util
 * Author :  Amayadream
 * Date   :  2015.12.04 22:58
 * TODO   :
 */
public class FileUtil {
    private String fileName;
    private String path;

    /**
     * 判断文件是否存在
     * @param path  文件路径,有没有结尾反斜杠
     * @param fileName  文件名
     * @return
     */
    private boolean isFileExists(String path, String fileName){
        File file;
        if(path.endsWith("\\")){       //如果有反斜杠后缀
            file = new File(path + fileName);
        }else{
            file = new File(path + "\\" + fileName);
        }
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
     * @return
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
