package com.amayadream.emailsystem.controller;

import com.amayadream.emailsystem.dao.ISettingDao;
import com.amayadream.emailsystem.pojo.Setting;
import com.amayadream.emailsystem.service.IEmailService;
import com.amayadream.emailsystem.util.DateUtil;
import com.amayadream.emailsystem.util.FileUtil;
import com.amayadream.emailsystem.util.MailUtil;
import com.amayadream.emailsystem.util.UploadUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;

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
    @Resource
    private ISettingDao settingDao;


    private static final String FILE_URI = "upload";
    private static final String RENAME_MARK = "(1)";

    @RequestMapping(value = "send")
    public String send(@ModelAttribute("userid") String userid, String subject, String emails, String content, HttpServletRequest request,
                       DateUtil dateUtil, FileUtil fileUtil, UploadUtil uploadUtil, MailUtil mailUtil, RedirectAttributes redirectAttributes){
        Setting setting = settingDao.selectSettingByUserid(userid);             //获取系统配置,即发信邮箱,发信密码,发信昵称,邮件服务器,端口号
        String [] array = uploadUtil.upload(request, FILE_URI, RENAME_MARK);    //上传附件到FILE_URL目录,然后将文件路径以分号隔开返回
        String absolute_file = array[0];
        String relative_file = array[1];
        File[] attachments = fileUtil.getFileArrayByString(absolute_file,";");          //将文件路径按分号切割开,并获取File数组

        String[] receiver = fileUtil.getStringArrayByString(emails,";");        //将收件人按分号切割开
        mailUtil.init(setting.getServer(),setting.getPort(),setting.getSendmail(),setting.getSendpass(),subject,setting.getSendname(),content,receiver,null,null,attachments);
        String meg = mailUtil.sendMail(true);
        if (meg == "success") {
            redirectAttributes.addFlashAttribute("INFO","发送成功!");
        }else{
            redirectAttributes.addFlashAttribute("ERROR","各种原因发送失败,自己猜吧!");
        }
        return "redirect:/index";
    }
}
