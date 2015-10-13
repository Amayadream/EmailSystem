package com.amayadream.controller;

import com.amayadream.pojo.User;
import com.amayadream.service.IUserService;
import com.amayadream.tools.getDate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * NAME   :  EmailSystem/com.amayadream.controller
 * Author :  Amayadream
 * Date   :  2015.10.06 16:34
 * TODO   :
 */

@Controller
@RequestMapping(value = "/user")
public class LoginController {
    @Resource
    private IUserService userService;

    @RequestMapping("/login")
    public String login(String username,String password,HttpServletRequest request, RedirectAttributes redirectAttributes, getDate date){
        if(username == null || username.equals("")){
            redirectAttributes.addFlashAttribute("info", "账号不能为空!!!");
            return "redirect:/login";
        }
        else if(password == null || password.equals("")){
            redirectAttributes.addFlashAttribute("info", "密码不能为空!!!");
            return "redirect:/login";
        }
        else{
            User user = this.userService.queryUserByUsername(username);
//            System.out.printf(user.username);
            if(user==null){
                redirectAttributes.addFlashAttribute("info", "查无此账号!!!");
                return "redirect:/login";
            }else{
                if(user.getAvailable() == 1){
                    if(password.equals(user.getPassword())){
                        String lasttime = date.getDateTime();
                        this.userService.updateLastTime(lasttime,user.getUsername());
                        request.getSession().setAttribute("loginStatus",true);
                        request.getSession().setAttribute("username",user.getUsername());
                        request.getSession().setAttribute("id",user.getId());
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
    public String logout(HttpServletRequest request){
//        request.getSession().removeAttribute(request.getSession().getAttributeNames().nextElement().toString());
        request.getSession().removeAttribute("username");
        request.getSession().removeAttribute("id");
        request.getSession().removeAttribute("loginStatus");
        return "redirect:/login";
    }
}
