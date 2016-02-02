package com.amayadream.emailsystem.util;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;

/**
 * NAME   :  EmailSystem/com.amayadream.emailsystem.util
 * Author :  Amayadream
 * Date   :  2015.12.05 15:22
 * TODO   :  文件上传工具类
 */
public class UploadUtil {

    /**
     * Spring MVC文件上传,返回的是经过处理的path+fileName,以分号;结尾
     * @param request
     * @param fileUrl
     * @param renameMark
     * @return
     */
    public String upload(HttpServletRequest request, String fileUrl, String renameMark){
        CommonDate date = new CommonDate();
        CommonValidate validate = new CommonValidate();
        String relative_file = "";
        //创建一个通用的多部分解析器
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
        //判断 request 是否有文件上传,即多部分请求
        if(multipartResolver.isMultipart(request)){
            //转换成多部分request
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest)request;
            //取得request中的所有文件名
            Iterator<String> iter = multiRequest.getFileNames();
            while(iter.hasNext()){
                //取得上传文件
                MultipartFile file = multiRequest.getFile(iter.next());
                if(file != null){
                    //取得当前上传文件的文件名称
                    String myFileName = file.getOriginalFilename();
                    //如果名称不为“”,说明该文件存在，否则说明该文件不存在
                    if(myFileName.trim() !=""){
                        System.out.println(myFileName);
                        //重命名上传后的文件名
                        String fileName =  file.getOriginalFilename();
                        //定义上传路径
                        String path = request.getSession().getServletContext().getRealPath(fileUrl);
                        //如果文件重复,则进行特定规则的重命名
                        Long stamp = date.getStamp();
                        path = validate.checkPathEnd(path) + stamp;
                        fileName = validate.validateRepeatFileName(path, fileName, renameMark);
                        File localFile = new File(path, fileName);
                        if(!localFile.exists()){
                            localFile.mkdirs();
                        }
                        try {
                            file.transferTo(localFile);
                            relative_file += validate.checkStringEnd(fileUrl) + stamp + "/" + fileName + ";";
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
        return relative_file;
    }
}
