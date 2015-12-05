package com.amayadream.emailsystem.controller;

import com.amayadream.emailsystem.util.FileUtil;
import com.amayadream.emailsystem.util.UploadUtil;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

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
    public String multiUpload(HttpServletRequest request, RedirectAttributes redirectAttributes,
                              UploadUtil uploadUtil) throws IllegalStateException, IOException {
        String result = uploadUtil.upload(request,FILE_URI,RENAME_MARK);
        redirectAttributes.addFlashAttribute("result",result);
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
