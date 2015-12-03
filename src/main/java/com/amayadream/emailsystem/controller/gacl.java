package com.amayadream.emailsystem.controller;

import java.io.FileOutputStream;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

/**
 * NAME   :  EmailSystem/com.amayadream.emailsystem.controller
 * Author :  Amayadream
 * Date   :  2015.12.03 21:23
 * TODO   :
 */
public class gacl {
    /**
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {

        Properties prop = new Properties();
        prop.setProperty("mail.host", "smtp.163.com");
        prop.setProperty("mail.transport.protocol", "smtp");
        prop.setProperty("mail.smtp.auth", "true");
        //使用JavaMail发送邮件的5个步骤
        //1、创建session
        Session session = Session.getInstance(prop);
        //开启Session的debug模式，这样就可以查看到程序发送Email的运行状态
        session.setDebug(true);
        //2、通过session得到transport对象
        Transport ts = session.getTransport();
        //3、连上邮件服务器
        ts.connect("smtp.163.com", "Amaya_first@163.com", "8162108.ok.ok");
        //4、创建邮件
        Message message = createMixedMail(session);
        //5、发送邮件
        ts.sendMessage(message, message.getAllRecipients());
        ts.close();
    }

    public static MimeMessage createMixedMail(Session session) throws Exception {
        //创建邮件
        MimeMessage message = new MimeMessage(session);

        //设置邮件的基本信息
        message.setFrom(new InternetAddress("Amaya_first@163.com"));
        message.setRecipient(Message.RecipientType.TO, new InternetAddress("524806599@qq.com"));
        message.setSubject("这是一封测试邮件哈");

        //正文
        MimeBodyPart text = new MimeBodyPart();
        text.setContent("<a href='http://www.fkjava.org'>测试的HTML邮件</a>", "text/html;charset=UTF-8");

        //图片
        MimeBodyPart image = new MimeBodyPart();
        image.setDataHandler(new DataHandler(new FileDataSource("C:\\Users\\Administrator\\Desktop\\QQ截图20151203152914.png")));
        image.setContentID("aaa.jpg");

//        //附件1
//        MimeBodyPart attach = new MimeBodyPart();
//        DataHandler dh = new DataHandler(new FileDataSource("C:\\Users\\Administrator\\Desktop\\QQ截图20151203152914.png"));
//        attach.setDataHandler(dh);
//        attach.setFileName(dh.getName());
//
//        //附件2
//        MimeBodyPart attach2 = new MimeBodyPart();
//        DataHandler dh2 = new DataHandler(new FileDataSource("C:\\Users\\Administrator\\Desktop\\bootstrap-table-master.zip"));
//        attach2.setDataHandler(dh2);
//        attach2.setFileName(MimeUtility.encodeText(dh2.getName()));

        //描述关系:正文和图片
        MimeMultipart mp1 = new MimeMultipart();
        mp1.addBodyPart(text);
        mp1.addBodyPart(image);
        mp1.setSubType("related");

        //描述关系:正文和附件
//        MimeMultipart mp2 = new MimeMultipart();
//        mp2.addBodyPart(attach);
//        mp2.addBodyPart(attach2);

        //代表正文的bodypart
        MimeBodyPart content = new MimeBodyPart();
        content.setContent(mp1);
//        mp2.addBodyPart(content);
//        mp2.setSubType("mixed");

//        message.setContent(mp2);
        message.setContent(mp1);
        message.saveChanges();

//        message.writeTo(new FileOutputStream("E:\\MixedMail.eml"));
        //返回创建好的的邮件
        return message;
    }
}
