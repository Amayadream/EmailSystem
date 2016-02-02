package com.amayadream.emailsystem.controller;

import com.amayadream.emailsystem.pojo.Contact;
import com.amayadream.emailsystem.pojo.Email;
import com.amayadream.emailsystem.pojo.Setting;
import com.amayadream.emailsystem.service.IContactService;
import com.amayadream.emailsystem.service.IEmailService;
import com.amayadream.emailsystem.service.ISettingService;
import com.amayadream.emailsystem.util.*;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;

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
    private ISettingService settingService;
    @Resource
    private IContactService contactService;


    private static final String FILE_URI = "upload";    //上传的文件夹位置,根目录为webapps目录
    private static final String RENAME_MARK = "(1)";    //干扰码,即文件重复时在文件后加上的尾缀

    @RequestMapping(value = "send")
    public String send(@ModelAttribute("userid") String userid, String subject, String emails, String content, HttpServletRequest request,
                       DateUtil dateUtil, FileUtil fileUtil, UploadUtil uploadUtil, MailUtil mailUtil, RedirectAttributes redirectAttributes){
        Setting setting = settingService.selectSettingByUserid(userid);             //获取系统配置,即发信邮箱,发信密码,发信昵称,邮件服务器,端口号
        String prefix_path = request.getSession().getServletContext().getRealPath("/");
        String str = uploadUtil.upload(request, FILE_URI, RENAME_MARK);    //上传附件到指定目录,然后将文件路径以分号隔开返回
        String[] files_uri = str.split(";");
        File[] files = new File[files_uri.length];
        int x = 0;
        for(String item : files_uri){
            files[x] = new File(prefix_path + "/" + item);
            x++;
        }

        String[] receiver = fileUtil.getStringArrayByString(emails,";");        //将收件人按分号切割开
        mailUtil.init(setting.getServer(),setting.getPort(),setting.getSendmail(),setting.getSendpass(),subject,setting.getSendname(),content,receiver,null,null,files);
        String msg = mailUtil.sendMail(true);
        if (msg == "success") {
            emailService.insert(userid, emails, subject, content, dateUtil.getDateTime24(), str, 1);
            redirectAttributes.addFlashAttribute("INFO","发送成功!");
        }else{
            emailService.insert(userid, emails, subject, content, dateUtil.getDateTime24(), str, 0);
            redirectAttributes.addFlashAttribute("ERROR","各种原因发送失败!");
        }
        return "redirect:/email";
    }

    /**
     * 重新发送
     * @param userid
     * @param eid
     * @param mailUtil
     * @param fileUtil
     * @param redirectAttributes
     * @param request
     * @return
     */
    @RequestMapping(value = "sendAgain/{eid}")
    public String sendAgain(@ModelAttribute("userid") String userid,@PathVariable("eid") String eid, MailUtil mailUtil, FileUtil fileUtil, RedirectAttributes redirectAttributes, HttpServletRequest request){
        Setting setting = settingService.selectSettingByUserid(userid);
        Email email = emailService.selectEmailById(userid, eid);
        String prefix_path = request.getSession().getServletContext().getRealPath("/");
        String[] files_uri = email.getFiles().split(";");
        File[] files = new File[files_uri.length];
        int x = 0;
        for(String item : files_uri){
            files[x] = new File(prefix_path + "/" + item);
            x++;
        }
        String[] receiver = fileUtil.getStringArrayByString(email.getEmails(),";");
        mailUtil.init(setting.getServer(),setting.getPort(),setting.getSendmail(),setting.getSendpass(),email.getSubject(),setting.getSendname(),email.getContent(),receiver,null,null,files);
        String meg = mailUtil.sendMail(true);
        if (meg == "success") {
            emailService.update(userid, eid, 1);
            redirectAttributes.addFlashAttribute("INFO","发送成功!");
        }else{
            redirectAttributes.addFlashAttribute("ERROR","各种原因发送失败!");
        }
        return "redirect:/email";
    }

    @RequestMapping(value = "")
    public String all(@ModelAttribute("userid") String userid, Model model, HttpServletRequest request, FileUtil fileUtil){
        Page<Email> page = new Page<Email>(PageUtil.PAGE_SIZE);
        int[] pageParams = PageUtil.init(page, request);
        List<Email> list = emailService.selectAll(userid, pageParams[0]+1,pageParams[0]+pageParams[1]);
        int index = 0;
        for(Email item : list){     //遍历数组,目的是将收信人从邮箱变成姓名,事实上这里存储的收信人应该是联系人的id,这里下一步再改
            String[] receiver = fileUtil.getStringArrayByString(item.getEmails(),";");      //使用工具类,将收信人按照分号切割开
            String name = "";       //收件人姓名
            for(String rec : receiver){     //遍历数组,分次查询....这里写的优点挫,将就做吧
                Contact contact = contactService.selectContactByEmail(userid, rec);
                if(contact != null){
                    name += contact.getName()+";";
                }else{
                    name += rec + ";";
                }
            }
            list.get(index).setEmails(name);
            String file = "";
            if(item.getFiles()!=null){      //处理附件显示
                String[] files = fileUtil.getStringArrayByString(item.getFiles(),";");
                for(String f : files){
                    file += f.substring(f.lastIndexOf("/")+1) + ";";
                }
            }
            list.get(index).setFiles(file);
            index += 1;
        }
        int count = Integer.parseInt(emailService.count(userid).getEid());
        page.setTotalCount(count);
        page.setResult(list);
        model.addAttribute("page",page);
        return "apps/emailsystem/email";
    }

    @RequestMapping(value = "view/{eid}")
    public String id(@ModelAttribute("userid") String userid, @PathVariable("eid") String eid, FileUtil fileUtil, Model model) {
        Email email = emailService.selectEmailById(userid, eid);
        String[] receiver = fileUtil.getStringArrayByString(email.getEmails(), ";");      //使用工具类,将收信人按照分号切割开
        String name = "";       //收件人姓名
        for (String rec : receiver) {     //遍历数组,分次查询
            Contact contact = contactService.selectContactByEmail(userid, rec);
            name += contact != null ? contact.getName() + ";" : rec + ";";
        }
        email.setEmails(name);
        String file = "";
        if(email.getFiles()!=null){      //处理附件显示
            String[] files = fileUtil.getStringArrayByString(email.getFiles(),";");
            for(String f : files){
                file += f.substring(f.lastIndexOf("/")+1) + ";";
            }
        }
        model.addAttribute("email",email);
        model.addAttribute("file",file.split(";"));
        return "apps/emailsystem/view";
    }

    @RequestMapping("download")
    public void download(@RequestParam("id") String eid, HttpServletRequest request, HttpServletResponse response){
        try{
            String fileUrl = eid.substring(0,eid.lastIndexOf("/"));
            String fileName = eid.substring(eid.lastIndexOf("/")+1);
            String fileNameEncode = new String(fileName.getBytes(),"ISO8859-1");
            response.setContentType("application/x-msdownload");
            FileInputStream FileInputStreamRef = new FileInputStream(new File(request.getSession().getServletContext().getRealPath(fileUrl)+"\\"+fileName));
            response.setHeader("Content-Disposition","attachment;filename=\""+fileNameEncode+"\"");     //文件名经过处理,防止有空格时出现文件名不全的情况
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
