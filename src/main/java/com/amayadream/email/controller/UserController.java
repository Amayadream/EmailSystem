package com.amayadream.email.controller;

import com.alibaba.fastjson.JSON;
import com.amayadream.email.service.IUserService;
import com.amayadream.email.tools.Regex;
import com.amayadream.email.tools.getDate;
import com.amayadream.email.pojo.Contactor;
import com.amayadream.email.pojo.User;
import com.amayadream.email.service.IUserService;
import com.amayadream.email.tools.Regex;
import com.amayadream.email.tools.getDate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * NAME   :  EmailSystem/com.amayadream.controller
 * Author :  Amayadream
 * Date   :  2015.10.07 17:30
 * TODO   :
 */

@Controller
@RequestMapping(value = "/user")
public class UserController {
    @Resource
    private IUserService userService;

    @RequestMapping(value = "/all", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String all(int page, HttpServletResponse response, Map map, Map map2){
        int pageSize = 2;
        if(page==1){
            map.put("startRow", 1);
            map.put("endRow", pageSize);
        }
        else{
            map.put("startRow", pageSize*(page-1)+1);
            map.put("endRow", pageSize*page);
        }
        User user = this.userService.countUser();
        int rowCount = user.getId();
        List<Contactor> list = this.userService.queryAllUser(map);
        response.setCharacterEncoding("utf-8");
        if(rowCount%pageSize!=0){
            rowCount = rowCount/pageSize+1;
        }
        else{
            rowCount = rowCount/pageSize;
        }
        map2.put("pageCount", rowCount);
        map2.put("CurrentPage", page);
        map2.put("list", list);
        return JSON.toJSONString(map2);
    }

    @RequestMapping(value = "/id", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public User id(int id){
        User user = this.userService.queryUserById(id);
        return user;
    }

    @RequestMapping(value = "/username", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public User username(String username){
        User user = this.userService.queryUserByUsername(username);
        return user;
    }

    @RequestMapping(value = "/add")
    public String add(String username, String password1, String password2, String email, getDate getDate, Regex regex,
                      Map map, RedirectAttributes redirectAttributes){
        String firsttime = getDate.getDateTime();
        if(username == null || username.equals("")){
            redirectAttributes.addFlashAttribute("ERROR","用户名不能为空!");
            return "redirect:/index";
        }else{
            if(username.length() < 2){
                redirectAttributes.addFlashAttribute("ERROR","用户名过短!");
                return "redirect:/index";
            }else{
                if(password1 == null || password1.equals("")){
                    redirectAttributes.addFlashAttribute("ERROR","密码不能为空!");
                    return "redirect:/index";
                }else{
                    if(password1.length() < 5){
                        redirectAttributes.addFlashAttribute("ERROR","密码过短!");
                        return "redirect:/index";
                    }else{
                        if(!password2.equals(password1)){
                            redirectAttributes.addFlashAttribute("ERROR","两次密码不一致!");
                            return "redirect:/index";
                        }else{
                            if(!regex.checkEmail(email)){
                                redirectAttributes.addFlashAttribute("ERROR","邮箱格式不正确!");
                                return "redirect:/index";
                            }else {
                                map.put("username",username);
                                map.put("password",password1);
                                map.put("email",email);
                                map.put("firsttime",firsttime);
                                map.put("available",1);
                                this.userService.insertUser(map);
                                redirectAttributes.addFlashAttribute("INFO","成功添加"+ username +"用户!");
                                return "redirect:/index";
                            }
                        }
                    }
                }
            }
        }
    }

}
