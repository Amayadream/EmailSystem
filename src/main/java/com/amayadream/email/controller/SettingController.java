package com.amayadream.email.controller;

import com.amayadream.email.tools.Regex;
import com.amayadream.email.pojo.Setting;
import com.amayadream.email.service.ISettingService;
import com.amayadream.email.tools.Regex;
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
 * NAME   :  EmailSystem/com.amayadream.controller
 * Author :  Amayadream
 * Date   :  2015.10.09 10:04
 * TODO   :
 */

@Controller
@SessionAttributes("id")
@RequestMapping("/setting")
public class SettingController {
    @Resource
    private ISettingService settingService;

    /**
     * 展示设置内容
     * @param userid
     * @return
     */
    @RequestMapping("/show")
    @ResponseBody
    public Setting show(@ModelAttribute("id") int userid){
        Setting setting = this.settingService.querySettingByUserid(userid);
        return setting;
    }

    /**
     * 修改设置,设置完成后将isset设为1,代表已经设置完成
     * @param session
     * @param sendmail
     * @param sendname
     * @param sendpass
     * @param smtpserver
     * @param smtpport
     * @param map
     * @param redirectAttributes
     * @return
     */
    @RequestMapping("/edit")
    public String edit(HttpSession session, String sendmail, String sendname, String sendpass, Regex regex,
                         String smtpserver, String smtpport,Map map, RedirectAttributes redirectAttributes){
        int userid = (Integer)session.getAttribute("id");
        if(regex.checkEmail(sendmail)){
            map.put("userid",userid);
            map.put("sendmail",sendmail);
            map.put("sendname",sendname);
            map.put("sendpass",sendpass);
            map.put("smtpserver",smtpserver);
            map.put("smtpport",smtpport);
            this.settingService.updateSetting(map);
            this.settingService.updateIsset(userid,1);
            redirectAttributes.addFlashAttribute("INFO","修改成功!");
            return "redirect:/setting";
        }else{
            redirectAttributes.addFlashAttribute("INFO","邮箱格式不正确!");
            return "redirect:/setting";
        }
    }

}
