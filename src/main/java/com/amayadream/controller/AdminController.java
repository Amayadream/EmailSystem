package com.amayadream.controller;

import com.amayadream.pojo.Admin;
import com.amayadream.pojo.User;
import com.amayadream.service.IAdminService;
import com.amayadream.service.IUserService;
import com.amayadream.tools.getDate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 * NAME   :  EmailSystem/com.amayadream.controller
 * Author :  Amayadream
 * Date   :  2015.10.12 10:34
 * TODO   :  管理员操作
 */

@Controller
@RequestMapping(value = "/admin")
public class AdminController {
    @Resource
    IUserService userService;
    IAdminService adminService;

    @RequestMapping(value = "/login")
    public String login(String username, String password, RedirectAttributes redirectAttributes, HttpSession session, getDate getDate){
        if(username == null || username.equals("")){
            redirectAttributes.addFlashAttribute("ERROR","账号不能为空!");
            return "redirect:/index";
        }else{
            if(password == null || password.equals("")){
                redirectAttributes.addFlashAttribute("ERROR","密码不能为空!");
                return "redirect:/index";
            }else{
                Admin admin = this.adminService.queryAdminByUsername(username);
                if(admin == null){
                    redirectAttributes.addFlashAttribute("ERROR","此账号不存在!");
                    return "redirect:/index";
                }else{
                    if(!admin.getPassword().equals(password)){
                        redirectAttributes.addFlashAttribute("ERROR","账号或者密码不正确!");
                        return "redirect:/index";
                    }else{
                        this.adminService.updateLastTime(admin.getAid(),getDate.getDateTime());
                        session.setAttribute("AdminStatus",true);
                        session.setAttribute("Admin", admin.getUsername());
                        session.setAttribute("Aid",admin.getAid());
                        return "redirect:/index";
                    }
                }
            }
        }
    }

    @RequestMapping(value = "/logout")
    public String logout(HttpSession session){
        session.removeAttribute("Admin");
        session.removeAttribute("Aid");
        return "redirect:/login";
    }



    /**
     * 禁用某个用户账号
     * @param id
     * @param redirectAttributes
     * @return
     */
    @RequestMapping(value = "/ban")
    public String ban(int id, RedirectAttributes redirectAttributes){
        boolean flag = this.userService.updateAvailable(id, 0);
        if(flag == true){
            redirectAttributes.addFlashAttribute("INFO","禁用成功!");
            return "redirect:/index";
        }else{
            redirectAttributes.addFlashAttribute("ERROR","未知原因导致错误,请重试!");
            return "redirect:/index";
        }
    }

    /**
     * 启用某个用户账号
     * @param id
     * @param redirectAttributes
     * @return
     */
    @RequestMapping(value = "/allow")
    public String allow(int id, RedirectAttributes redirectAttributes){
        boolean flag = this.userService.updateAvailable(id, 1);
        if(flag == true){
            redirectAttributes.addFlashAttribute("INFO","启用成功!");
            return "redirect:/index";
        }else{
            redirectAttributes.addFlashAttribute("ERROR","未知原因导致错误,请重试!");
            return "redirect:/index";
        }
    }

    /**
     * 删除某个用户账号
     * @param id
     * @param redirectAttributes
     * @return
     */
    @RequestMapping(value = "/delete")
    public String delete(int id, RedirectAttributes redirectAttributes){
        User user = this.userService.queryUserById(id);
        if(user != null){
            boolean flag = false;
            flag = this.userService.deleteUser(id);
            if(flag == true){
                redirectAttributes.addFlashAttribute("INFO","成功删除"+ user.getUsername() +"用户");
                return "redirect:/index";
            }else{
                redirectAttributes.addFlashAttribute("ERROR","未知错误导致删除失败,请重试!");
                return "redirect:/index";
            }
        }else{
            redirectAttributes.addFlashAttribute("ERROR",user.getUsername()+"用户不存在!");
            return "redirect:/index";
        }
    }
}
