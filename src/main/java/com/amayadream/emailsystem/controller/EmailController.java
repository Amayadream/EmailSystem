package com.amayadream.emailsystem.controller;

import com.amayadream.emailsystem.service.IEmailService;
import com.amayadream.emailsystem.util.DateUtil;
import com.amayadream.emailsystem.util.UploadUtil;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * NAME   :  EmailSystem/com.amayadream.emailsystem.controller
 * Author :  Amayadream
 * Date   :  2015.12.04 21:49
 * TODO   :  邮件控制器
 */
@Controller
@RequestMapping(value = "email")
@SessionAttributes("userid")
public class EmailController {
    @Resource
    private IEmailService emailService;

    private static final String FILE_URI = "/upload";
    private static final String RENAME_MARK = "(1)";

    @RequestMapping(value = "send")
    public String send(@ModelAttribute("userid") String userid, String subject, String emails, String content, HttpServletRequest request,
                       DateUtil dateUtil, UploadUtil uploadUtil, RedirectAttributes redirectAttributes){
        String meg = uploadUtil.upload(request, FILE_URI, RENAME_MARK);
        System.out.println("用户ID=>"+userid);
        System.out.println("邮件主题=>"+subject);
        System.out.println("收件人=>"+emails);
        System.out.printf("邮件内容=>"+content);
        redirectAttributes.addFlashAttribute("result",meg);
        return "redirect:/result";
    }
}
