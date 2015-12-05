package com.amayadream.emailsystem.controller;

import com.alibaba.fastjson.JSON;
import com.amayadream.emailsystem.pojo.User;
import com.amayadream.emailsystem.service.IUserService;
import com.amayadream.emailsystem.util.DateUtil;
import com.amayadream.emailsystem.util.MD5Util;
import com.amayadream.emailsystem.util.RegexUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * NAME   :  EmailSystem/com.amayadream.emailsystem.controller
 * Author :  Amayadream
 * Date   :  2015.11.29 16:37
 * TODO   :  用户控制器
 */
@Controller
@RequestMapping(value = "/user")
public class UserController {
    @Resource
    private IUserService userService;
    @Resource
    User user;

    @RequestMapping(value = "/all", produces = "application/json;charset=utf-8")
    @ResponseBody
    public String all(int pageNo, Map<String, Object> map){
        int pageSize = 2;
        User user = userService.count();
        int rowCount = Integer.parseInt(user.getId());
        List<User> list = userService.selectAll(pageNo, pageSize);
        if(rowCount%pageSize!=0){
            rowCount = rowCount/pageSize+1;
        }
        else{
            rowCount = rowCount/pageSize;
        }
        map.put("pageCount", rowCount);
        map.put("CurrentPage", pageNo);
        map.put("list", list);
        return JSON.toJSONString(map);
    }

    @RequestMapping(value = "/id", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public User id(String id){
        return userService.selectUserById(id);
    }

    @RequestMapping(value = "/username", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public User username(String username){
        return userService.selectUserByUsername(username);
    }

    @RequestMapping(value = "/add")
    public String add(String username, String password1, String password2, String email,
                      DateUtil dateUtil, RegexUtil regexUtil, MD5Util md5Util, RedirectAttributes redirectAttributes){
        String firsttime = dateUtil.getDateTime24();
        if(username == null || username.equals("")){
            redirectAttributes.addFlashAttribute("ERROR","用户名不能为空!");
        }else{
            if(username.length() < 2){
                redirectAttributes.addFlashAttribute("ERROR","用户名过短!");
            }else{
                if(password1 == null || password1.equals("")){
                    redirectAttributes.addFlashAttribute("ERROR","密码不能为空!");
                }else{
                    if(password1.length() < 5){
                        redirectAttributes.addFlashAttribute("ERROR","密码过短!");
                    }else{
                        if(!password2.equals(password1)){
                            redirectAttributes.addFlashAttribute("ERROR","两次密码不一致!");
                        }else{
                            if(!regexUtil.checkEmail(email)){
                                redirectAttributes.addFlashAttribute("ERROR","邮箱格式不正确!");
                            }else {
                                boolean flag = userService.insert(username, md5Util.MD5(password1) , email, firsttime, firsttime, 1);
                                if(flag){
                                    redirectAttributes.addFlashAttribute("INFO","成功添加"+ username +"用户!");
                                }else{
                                    redirectAttributes.addFlashAttribute("ERROR","添加失败,请重试!");
                                }
                            }
                        }
                    }
                }
            }
        }
        return "redirect:/index";
    }

}
