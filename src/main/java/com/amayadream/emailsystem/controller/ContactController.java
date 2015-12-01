package com.amayadream.emailsystem.controller;

import com.alibaba.fastjson.JSON;
import com.amayadream.emailsystem.pojo.Contact;
import com.amayadream.emailsystem.service.IContactService;
import com.amayadream.emailsystem.util.RegexUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * NAME   :  EmailSystem/com.amayadream.emailsystem.controller
 * Author :  Amayadream
 * Date   :  2015.11.29 18:54
 * TODO   :
 */

@Controller
@RequestMapping(value = "contact")
@SessionAttributes("userid")
public class ContactController {
    @Resource
    private IContactService contactService;
    @Resource
    private Contact contact;

    @RequestMapping(value = "", produces = "application/json;charset=utf-8")
    public String all(Model model, @ModelAttribute("userid") String userid,@RequestParam(value = "page", defaultValue = "1") int pageNo){
        int pageSize = 10;
        List<Contact> list = contactService.selectAll(pageNo, pageSize, userid);
        model.addAttribute("result",list);
        return "apps/emailsystem/contact";
    }

    @RequestMapping(value = "id", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Contact id(@RequestParam("id") String cid, @ModelAttribute("userid") String userid){
        return contactService.selectContactById(cid, userid);
    }

    @RequestMapping(value = "name", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Contact name(String name, @ModelAttribute("userid") String userid){
        return contactService.selectContactByName(name, userid);
    }

    @RequestMapping(value = "add")
    public String add(String name, @ModelAttribute("userid") String userid, String email, String groupid,
                      RedirectAttributes redirectAttributes, RegexUtil regexUtil){
        if(name == null || name.equals("")){
            redirectAttributes.addFlashAttribute("ERROR", "姓名不能为空,请重新输入!");
        }else{
            if(name.length() < 2){
                redirectAttributes.addFlashAttribute("ERROR", "姓名太短,请重新输入!");
            }else{
                //验证联系人是否存在
                if(contactService.selectContactByName(name, userid) == null){
                    //验证邮箱是否符合规则
                    if(regexUtil.checkEmail(email)){
                        boolean flag = contactService.insert(userid, name, email, groupid);
                        if(flag){
                            redirectAttributes.addFlashAttribute("INFO", name + "已成功添加到通讯录!");
                        }else{
                            redirectAttributes.addFlashAttribute("ERROR","添加错误,请重新输入!");
                        }
                    }else{
                        redirectAttributes.addFlashAttribute("ERROR","邮箱" + email + "不符合规则,请重新输入!");
                    }
                }else{
                    redirectAttributes.addFlashAttribute("ERROR","联系人"+ name +"已存在!");
                }
            }
        }
        return "redirect:/contact";
    }


    @RequestMapping(value = "/edit")
    public String edit(@RequestParam("id") String cid, String name, @ModelAttribute("userid") String userid, String email, String groupid,
                       RedirectAttributes redirectAttributes, RegexUtil regexUtil){
        if(name == null || name.equals("")){
            redirectAttributes.addFlashAttribute("ERROR","姓名不能为空,请重新输入!");
        }else{
            if(name.length() < 2){
                redirectAttributes.addFlashAttribute("ERROR","姓名过短,请重新输入!");
            }else{
                if(contactService.selectContactByName(name, userid) == null){
                    if(regexUtil.checkEmail(email)){
                        boolean flag = contactService.update(cid, userid, name, email, groupid);
                        if(flag){
                            redirectAttributes.addFlashAttribute("INFO","编辑[" + name + "]成功!");
                        }else{
                            redirectAttributes.addFlashAttribute("ERROR","未知原因导致添加失败,请重试");
                        }
                    }else{
                        redirectAttributes.addFlashAttribute("ERROR","邮箱[" + email + "]不符合规则,请重新输入!");
                    }
                }else {
                    redirectAttributes.addFlashAttribute("ERROR","联系人[" + name + "]已存在!");
                }
            }
        }
        return "redirect:/contact";
    }

    @RequestMapping("/delete")
    public String delete(@RequestParam("id") String cid, @ModelAttribute("userid") String userid, RedirectAttributes redirectAttributes){
        if(contactService.selectContactById(cid, userid) == null){
            redirectAttributes.addFlashAttribute("ERROR","此联系人不存在!");
        }else{
            boolean flag = contactService.delete(cid, userid);
            if(flag){
                redirectAttributes.addFlashAttribute("INFO","删除成功!");
            }else{
                redirectAttributes.addFlashAttribute("ERROR","未知原因导致删除失败,请重试!");
            }
        }
        return "redirect:/contact";
    }

}
