package com.amayadream.email.interceptor;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;

/**
 * NAME   :  EmailSystem/com.amayadream.interceptor
 * Author :  Amayadream
 * Date   :  2015.10.12 11:23
 * TODO   :
 */
public class AdminInterceptor extends HandlerInterceptorAdapter {
    private static final String[] FOR_URI = {"admin/delete","admin/ban","admin/allow","admin/logout"};
    private static final String LOGIN_URI = "/login";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String url = request.getRequestURL().toString();
        String contextPath = request.getContextPath();
        HttpSession session = request.getSession();

        for(String s : FOR_URI){
            if(!url.contains(s)){
                return true;
            }
        }
        if(session != null && session.getAttribute("AdminStatus") != null){
            return true;
        }else{
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
            return false;
        }
    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);
    }
}
