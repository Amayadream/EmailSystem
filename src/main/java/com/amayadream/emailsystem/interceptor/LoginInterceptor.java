package com.amayadream.emailsystem.interceptor;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;

/**
 * NAME   :  EmailSystem/com.amayadream.interceptor
 * Author :  Amayadream
 * Date   :  2015.10.06 17:33
 * TODO   :
 */
public class LoginInterceptor extends HandlerInterceptorAdapter {
    private static final String[] IGNORE_URI = {"user/login","/login","/user/logout"};  //忽略的URI
    private static final String LOGIN_URI = "/login";   //登陆URI

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String url = request.getRequestURL().toString();
        String contextPath = request.getContextPath();
        for (String s : IGNORE_URI) {
            if (url.contains(s)) {
//                System.out.printf(url + "=>>>>>已自动忽略!" + "\n");
                return true;
            }
        }
        HttpSession session = request.getSession();
        if(session != null && session.getAttribute("loginStatus") != null){
//            System.out.printf(url + "=>>>>>已登录,欢迎访问!" + "\n");
            return true;
        }else{
//            System.out.printf(url + "=>>>>>未登录,禁止访问!" + "\n");
            response.setContentType("text/html; charset=utf-8");
            PrintWriter out = response.getWriter();
            StringBuilder builder = new StringBuilder();
            builder.append("<script type=\"text/javascript\">");
            builder.append("alert('网页过期，请重新登录！');");
            builder.append("window.location.href='");
            builder.append(contextPath + LOGIN_URI);
            builder.append("';");
            builder.append("</script>");
            out.print(builder.toString());
//            response.sendRedirect(contextPath + LOGIN_URI);
            return false;
        }
    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);
    }
}
