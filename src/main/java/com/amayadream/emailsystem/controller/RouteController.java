package com.amayadream.emailsystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * NAME   :  EmailSystem/com.amayadream.emailsystem.controller
 * Author :  Amayadream
 * Date   :  2015.11.28 15:35
 * TODO   :  路由控制器
 */
@Controller
public class RouteController {

    @RequestMapping("login")
    public String login(){
        return "apps/emailsystem/login";
    }

    @RequestMapping("receiver")
    public String receiver(){
        return "apps/emailsystem/receiver";
    }

    @RequestMapping("setting")
    public String setting(){
        return "apps/emailsystem/setting";
    }

    @RequestMapping("othersetting")
    public String othersetting(){
        return "apps/emailsystem/othersetting";
    }

    @RequestMapping("about")
    public String about(){
        return "apps/emailsystem/about";
    }

    @RequestMapping("view")
    public String view(){
        return "apps/emailsystem/view";
    }

    @RequestMapping("test")
    public String test(){
        return "apps/emailsystem/test";
    }

    @RequestMapping("upload")
    public String upload(){
        return "apps/emailsystem/upload";
    }

    @RequestMapping("result")
    public String result(){
        return "apps/emailsystem/result";
    }
}
