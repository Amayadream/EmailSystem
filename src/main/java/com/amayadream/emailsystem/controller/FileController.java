package com.amayadream.emailsystem.controller;

import com.amayadream.emailsystem.util.FileUtil;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.Date;
import java.util.Iterator;

/**
 * NAME   :  EmailSystem/com.amayadream.controller
 * Author :  Amayadream
 * Date   :  2015.10.06 00:17
 * TODO   :
 */

@Controller
@RequestMapping("/file")
public class FileController {

    private static final String FILE_URI = "/upload";
    private static final String RENAME_MARK = "(1)";

    @RequestMapping("/simpleUpload")
    public void asd(MultipartHttpServletRequest request,HttpServletResponse response){
        try {
            //System.out.printf("username="+request.getParameter("username"));
            MultipartFile file = request.getFile("uploadFile");     //获取上传文件
            String uploadFileName = file.getOriginalFilename();     //文件原始名称
            InputStream isRef = file.getInputStream();
            String targetDir = request.getSession().getServletContext().getRealPath(FILE_URI);
            File targetFile = new File(targetDir, uploadFileName);
            FileOutputStream fosRef = new FileOutputStream(targetFile);
            IOUtils.copy(isRef, fosRef);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/multiUpload"  )
    public String multiUpload(HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes, FileUtil fileUtil) throws IllegalStateException, IOException {
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
                        String path = request.getSession().getServletContext().getRealPath(FILE_URI);
                        //如果文件重复,则进行特定规则的重命名
                        fileName = fileUtil.getNotrepeatFileName(path, fileName, RENAME_MARK);
                        File localFile = new File(path, fileName);
                        if(!localFile.exists()){
                            localFile.mkdirs();
                        }
                        file.transferTo(localFile);
                        redirectAttributes.addFlashAttribute("result","上传成功");
                    }
                }
            }
        }
        return "redirect:/result";
    }

    @RequestMapping("download")
    public void download(HttpServletRequest request, HttpServletResponse response){
        try{
            String fileName = "photo.png";
            String fileNameEncode = new String(fileName.getBytes(),"ISO8859-1");
            response.setContentType("application/x-msdownload");
            FileInputStream FileInputStreamRef = new FileInputStream(new File(request.getSession().getServletContext().getRealPath(FILE_URI)+"\\"+fileName));
            response.setHeader("Content-Disposition","attachment;filename="+fileNameEncode);
            OutputStream osRef = response.getOutputStream();
            IOUtils.copy(FileInputStreamRef,osRef);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
