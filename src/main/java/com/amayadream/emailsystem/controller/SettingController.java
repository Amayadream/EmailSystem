package com.amayadream.emailsystem.controller;

import com.amayadream.emailsystem.pojo.Setting;
import com.amayadream.emailsystem.service.ISettingService;
import com.amayadream.emailsystem.util.RegexUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * NAME   :  EmailSystem/com.amayadream.emailsystem.controller
 * Author :  Amayadream
 * Date   :  2015.11.29 17:33
 * TODO   :
 */
@Controller
@RequestMapping(value = "setting")
@SessionAttributes("userid")
public class SettingController {
    @Resource
    private ISettingService settingService;

    @RequestMapping(value = "show", produces = "application/json;charset=utf-8")
    @ResponseBody
    public Setting show(@ModelAttribute("userid") String userid){
        return settingService.selectSettingByUserid(userid);
    }

    @RequestMapping("/edit")
    public String edit(@ModelAttribute("userid") String userid, String sendmail, String sendname, String sendpass, RegexUtil regexUtil,
                       String server, String port, RedirectAttributes redirectAttributes){
        if(regexUtil.checkEmail(sendmail)){
            boolean flag = settingService.update(userid, sendmail, sendname, sendpass, server, port);
            if(flag){
                settingService.updateIsset(userid,1);
                redirectAttributes.addFlashAttribute("INFO","修改成功!");
            }else{
                redirectAttributes.addFlashAttribute("ERROR","修改失败,请重试!");
            }
        }else{
            redirectAttributes.addFlashAttribute("INFO","邮箱格式不正确!");
        }
        return "redirect:/setting";
    }
}
