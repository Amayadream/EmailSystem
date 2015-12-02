package com.amayadream.emailsystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * NAME   :  EmailSystem/com.amayadream.emailsystem.controller
 * Author :  Amayadream
 * Date   :  2015.11.28 15:35
 * TODO   :
 */
@Controller
public class RouteController {

    @RequestMapping("login")
    public String login(){
        return "apps/emailsystem/login";
    }

    @RequestMapping("index")
    public String index(){
        return "apps/emailsystem/index";
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
}
