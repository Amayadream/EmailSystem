package com.amayadream.emailsystem.controller;

import com.amayadream.emailsystem.pojo.User;
import com.amayadream.emailsystem.service.IUserService;
import com.amayadream.emailsystem.util.DateUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 * NAME   :  EmailSystem/com.amayadream.emailsystem.controller
 * Author :  Amayadream
 * Date   :  2015.11.28 00:39
 * TODO   :
 */
@Controller
@RequestMapping(value = "/user")
public class LoginController {
    @Resource
    private IUserService userService;
    @Resource
    private User user;

    @RequestMapping(value = "/login")
    public String login(String username, String password, HttpSession session, RedirectAttributes redirectAttributes, DateUtil dateUtil){
        if(username == null || username.equals("")){
            redirectAttributes.addFlashAttribute("info", "账号不能为空!!!");
            return "redirect:/login";
        }
        else if(password == null || password.equals("")){
            redirectAttributes.addFlashAttribute("info", "密码不能为空!!!");
            return "redirect:/login";
        }
        else{
            User user = userService.selectUserByUsername(username);
            if(user==null){
                redirectAttributes.addFlashAttribute("info", "查无此账号!!!");
                return "redirect:/login";
            }else{
                if(user.getAvailable() == 1){
                    if(password.equals(user.getPassword())){
                        userService.updateLasttime(user.getUsername(),dateUtil.getDateTime24());
                        session.setAttribute("loginStatus",true);
                        session.setAttribute("username",user.getUsername());
                        session.setAttribute("userid",user.getId());
                        return "redirect:/index";
                    }
                    else{
                        redirectAttributes.addFlashAttribute("info", "账号或密码错误!!!");
                        return "redirect:/login";
                    }
                }else{
                    redirectAttributes.addFlashAttribute("info","账号已被禁用!!!");
                    return "redirect:/login";
                }
            }
        }
    }

    @RequestMapping("/logout")
    public String logout(HttpSession session){
        session.removeAttribute("username");
        session.removeAttribute("id");
        session.removeAttribute("loginStatus");
        return "redirect:/login";
    }

}
