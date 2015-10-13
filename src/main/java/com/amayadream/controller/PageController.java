package com.amayadream.controller;

import org.apache.ibatis.annotations.ResultMap;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * NAME   :  EmailSystem/com.amayadream.controller
 * Author :  Amayadream
 * Date   :  2015.10.07 17:50
 * TODO   :
 */

@Controller
//@RequestMapping("Email")
public class PageController {

    @RequestMapping("login")
    public String login(){
        return "login";
    }

    @RequestMapping("index")
    public String index(){
        return "index";
    }

    @RequestMapping("receiver")
    public String receiver(){
        return "receiver";
    }

    @RequestMapping("contact")
    public String contact(){
        return "contact";
    }

    @RequestMapping("groups")
    public String groups(){
        return "groups";
    }

    @RequestMapping("setting")
    public String setting(){
        return "setting";
    }

    @RequestMapping("othersetting")
    public String othersetting(){
        return "othersetting";
    }

    @RequestMapping("about")
    public String about(){
        return "about";
    }

    @RequestMapping("view")
    public String view(){
        return "view";
    }

    @RequestMapping("test")
    public String test(){
        return "test";
    }
}
