package com.amayadream.emailsystem.util;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;

/**
 * NAME   :  EmailSystem/com.amayadream.emailsystem.util
 * Author :  Amayadream
 * Date   :  2015.12.04 14:00
 * TODO   :  邮件发送工具类
 */
public class MailUtil {

    // -- 验证要素 -- //
    private String sendhost;    //邮件服务器
    private String sendport;    //端口号
    private String sendmail;    //发信邮箱
    private String sendpass;    //发信密码

    //-- 邮件内容 -- //
    private String subject;     //邮件主题
    private String sendname;    //发信昵称
    private String content;     //邮件内容
    private String[] receiver;  //收件人,类型为数组
    private String[] cc;        //抄送,类型为数组
    private String[] bcc;       //密送,类型为数组
    private File[] files;       //附件,类型为数组

    private MimeMessage message;
    private Session session;
    private Transport transport;

    /**
     * 初始化
     */
    public void init(String sendhost, String sendport, String sendmail, String sendpass, String subject,
                        String sendname, String content, String[] receiver, String[] cc, String[] bcc, File[] files){
        this.sendhost = sendhost;
        this.sendport = sendport;
        this.sendmail = sendmail;
        this.sendpass = sendpass;
        this.subject = subject;
        this.sendname = sendname;
        this.content = content;
        this.receiver = receiver;
        this.cc = cc;
        this.bcc = bcc;
        this.files = files;
    }


    /**
     * 获取session对象
     * @param debug     是否开启debug模式
     * @return
     */
    public Session getSession(boolean debug){
        Properties properties = new Properties();
        properties.put("mail.smtp.host",sendhost);
        properties.put("mail.smtp.port",sendport);
        properties.put("mail.sender.username",sendmail);
        properties.put("mail.sender.password",sendpass);
        session = Session.getInstance(properties);
        session.setDebug(debug);// 开启后有调试信息
        return session;
    }

    /**
     * 设置发信的内容
     * @param session
     * @return
     */
    public MimeMessage getMessage(){
        message = new MimeMessage(session);
        try {
            // 邮件主题
            message.setSubject(subject);
            // 发信时间
            message.setSentDate(new Date());
            // 发件人
            if(sendname != null){
                InternetAddress from = new InternetAddress(sendmail, sendname);
                message.setFrom(from);
            }else{
                InternetAddress from = new InternetAddress(sendmail);
                message.setFrom(from);
            }
            // 收件人
            InternetAddress[] to = new InternetAddress[receiver.length];
            for (int i = 0; i < receiver.length; i++) {
                to[i] = new InternetAddress(receiver[i]);
            }
            message.setRecipients(Message.RecipientType.TO, to);
            if (cc != null) {
                InternetAddress[] add_cc = new InternetAddress[cc.length];
                for (int i = 0; i < cc.length; i++) {
                    add_cc[i] = new InternetAddress(cc[i]);
                }
                message.setRecipients(Message.RecipientType.CC, add_cc);
            }
            if (bcc != null) {
                InternetAddress[] add_bcc = new InternetAddress[bcc.length];
                for (int i = 0; i < bcc.length; i++) {
                    add_bcc[i] = new InternetAddress(bcc[i]);
                }
                message.setRecipients(Message.RecipientType.BCC, add_bcc);
            }
            // 向multipart对象中添加邮件的各个部分内容，包括文本内容和附件
            Multipart multipart = new MimeMultipart();
            // 添加邮件正文
            BodyPart contentPart = new MimeBodyPart();
            contentPart.setContent(content, "text/html;charset=UTF-8");
            multipart.addBodyPart(contentPart);
            if (files != null) {
                for (int i = 0; i < files.length; i++) {
                    BodyPart attachmentBodyPart = new MimeBodyPart();
                    DataSource source = new FileDataSource(files[i]);
                    attachmentBodyPart.setDataHandler(new DataHandler(source));

                    //MimeUtility.encodeWord可以避免文件名乱码
                    attachmentBodyPart.setFileName(MimeUtility.encodeWord(files[i].getName()));
                    multipart.addBodyPart(attachmentBodyPart);
                }
            }
            // 将multipart对象放到message中
            message.setContent(multipart);
            // 保存邮件
            message.saveChanges();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (AddressException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return message;
    }

    /**
     * 发送邮件
     * @param message
     * @param session
     * @return
     */
    public String sendMail(boolean debug){
        session = getSession(debug);
        message = getMessage();
        try {
            transport = session.getTransport("smtp");
            // smtp验证，就是你用来发邮件的邮箱用户名密码
            transport.connect(sendhost, sendmail, sendpass);
            transport.sendMessage(message, message.getAllRecipients());
            return "success";
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
            return "providerError";
        } catch (MessagingException e) {
            e.printStackTrace();
            return "messageError";//密码错误在这里触发,网络连接失败也在这里触发
        } finally {
            if (transport != null) {
                try {
                    transport.close();
                } catch (MessagingException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
