package com.amayadream.emailsystem.util;

import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

/**
 * NAME   :  EmailSystem/com.amayadream.emailsystem.util
 * Author :  Amayadream
 * Date   :  2015.12.03 22:17
 * TODO   :
 */
public class MailUtil {
    public static void sendMail(String username, String password, String host, String from, String to,
                                String subject, String htmlContent) {
        JavaMailSenderImpl senderImpl = new JavaMailSenderImpl();

        // 发送邮箱的邮件服务器
        senderImpl.setHost(host);

        // 建立邮件消息,发送简单邮件和html邮件的区别
        MimeMessage mailMessage = senderImpl.createMimeMessage();
        // 为防止乱码，添加编码集设置
        MimeMessageHelper messageHelper = new MimeMessageHelper(mailMessage,
                "UTF-8");

        try {
            // 接收方邮箱
            messageHelper.setTo(to);
        } catch (MessagingException e) {
            throw new RuntimeException("收件人邮箱地址出错！");
        }
        try {
            // 发送方邮箱
            messageHelper.setFrom(from);
        } catch (MessagingException e) {
            throw new RuntimeException("发件人邮箱地址出错！");
        }
        try {
            messageHelper.setSubject(subject);
        } catch (MessagingException e) {
            throw new RuntimeException("邮件主题出错！");
        }
        try {
            // true 表示启动HTML格式的邮件
            messageHelper.setText(htmlContent, true);
        } catch (MessagingException e) {
            throw new RuntimeException("邮件内容出错！");
        }

        Properties prop = new Properties();
        // 将这个参数设为true，让服务器进行认证,认证用户名和密码是否正确
        prop.put("mail.smtp.auth", "true");
        // 超时时间
        prop.put("mail.smtp.timeout", "25000");

        // 添加验证
        MyAuthenticator auth = new MyAuthenticator(username,
                password);

        Session session = Session.getDefaultInstance(prop, auth);
        senderImpl.setSession(session);

        // senderImpl.setJavaMailProperties(prop);
        // 发送邮件
        senderImpl.send(mailMessage);

    }

}
