package com.amayadream.emailsystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

/**
 * NAME   :  EmailSystem/com.amayadream.emailsystem.controller
 * Author :  Amayadream
 * Date   :  2015.12.04 21:49
 * TODO   :  邮件控制器
 */
@Controller
@RequestMapping(value = "email")
@SessionAttributes("userid")
public class EmailController {
}
