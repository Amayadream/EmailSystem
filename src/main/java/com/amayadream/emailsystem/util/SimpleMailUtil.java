package com.amayadream.emailsystem.util;


import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;
import java.io.File;
import java.util.Date;
import java.util.Properties;

/**
 * NAME   :  EmailSystem/com.amayadream.emailsystem.controller
 * Author :  Amayadream
 * Date   :  2015.12.03 22:14
 * TODO   :  邮件发送工具类
 */
public class SimpleMailUtil {

    private MimeMessage message;
    private Session session;
    private Transport transport;

    private Properties properties = new Properties();

    public void send(String host, String sendmail, String sendpass, String subject, String content, String[] receiveUser, File[] files) {
        properties.put("mail.smtp.host",host);
        properties.put("mail.sender.username",sendmail);
        properties.put("mail.sender.password",sendpass);
        session = Session.getInstance(properties);
        session.setDebug(true);// 开启后有调试信息
        message = new MimeMessage(session);
        try {
            // 发件人
            InternetAddress from = new InternetAddress(sendmail,"有昵称的邮件哦");
            message.setFrom(from);

            // 收件人
            InternetAddress[] to = new InternetAddress[receiveUser.length];
            for (int i = 0; i < receiveUser.length; i++){
                to[i] = new InternetAddress(receiveUser[i]);
            }
            message.setRecipients(Message.RecipientType.TO, to);

            //抄送
            //message.setRecipients(Message.RecipientType.CC, to);

            //密送
            //message.setRecipients(Message.RecipientType.BCC, to);

            // 邮件主题
            message.setSubject(subject);

            // 发信时间
            message.setSentDate(new Date());

            // 向multipart对象中添加邮件的各个部分内容，包括文本内容和附件
            Multipart multipart = new MimeMultipart();

            // 添加邮件正文
            BodyPart contentPart = new MimeBodyPart();
            contentPart.setContent(content, "text/html;charset=UTF-8");
            multipart.addBodyPart(contentPart);

            for(int i=0;i<files.length;i++){
                BodyPart attachmentBodyPart = new MimeBodyPart();
                DataSource source = new FileDataSource(files[i]);
                attachmentBodyPart.setDataHandler(new DataHandler(source));

                //MimeUtility.encodeWord可以避免文件名乱码
                attachmentBodyPart.setFileName(MimeUtility.encodeWord(files[i].getName()));
                multipart.addBodyPart(attachmentBodyPart);
            }

            // 将multipart对象放到message中
            message.setContent(multipart);
            // 保存邮件
            message.saveChanges();

            transport = session.getTransport("smtp");
            // smtp验证，就是你用来发邮件的邮箱用户名密码
            transport.connect(host, sendmail, sendpass);
            // 发送
            transport.sendMessage(message, message.getAllRecipients());
        } catch (Exception e) {
            e.printStackTrace();
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

    public static void main(String[] args) {
        SimpleMailUtil simpleMailUtil = new SimpleMailUtil();
        File file = new File("C:\\Users\\Administrator\\Desktop\\QQ截图20151203152914.png");
        File file2 = new File("C:\\Users\\Administrator\\Desktop\\QQ截图20151203152706.png");
        String[] sendto = {"13638040@qq.com","524806599@qq.com"};
        File[] files = new File[]{file,file2};
        simpleMailUtil.send("smtp.163.com","Amaya_first@163.com","8162108.ok.ok","今天太阳真好!","<h1>出来嗨</h1>",sendto,files);
    }
}
