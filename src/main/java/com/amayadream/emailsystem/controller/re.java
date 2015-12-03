package com.amayadream.emailsystem.controller;

import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Store;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Properties;

/**
 * NAME   :  EmailSystem/com.amayadream.emailsystem.controller
 * Author :  Amayadream
 * Date   :  2015.12.03 21:49
 * TODO   :
 */
public class re {
    public static void main(String[] args) throws Exception {
        // 连接pop3服务器的主机名、协议、用户名、密码
        String pop3Server = "pop3.163.com";
        String protocol = "pop3";
        String user = "Amaya_first@163.com";
        String pwd = "8162108.ok.ok";

        // 创建一个有具体连接信息的Properties对象
        Properties props = new Properties();
        props.setProperty("mail.store.protocol", protocol);
        props.setProperty("mail.pop3.host", pop3Server);

        // 使用Properties对象获得Session对象
        Session session = Session.getInstance(props);
        session.setDebug(true);

        // 利用Session对象获得Store对象，并连接pop3服务器
        Store store = session.getStore();
        store.connect(pop3Server, user, pwd);

        // 获得邮箱内的邮件夹Folder对象，以"只读"打开
        Folder folder = store.getFolder("inbox");
        folder.open(Folder.READ_ONLY);

        // 获得邮件夹Folder内的所有邮件Message对象
        Message[] messages = folder.getMessages();

        int mailCounts = messages.length;
        for(int i = 0; i < mailCounts; i++) {

            String subject = messages[i].getSubject();
            String from = (messages[i].getFrom()[0]).toString();

            System.out.println("第 " + (i+1) + "封邮件的主题：" + subject);
            System.out.println("第 " + (i+1) + "封邮件的发件人地址：" + from);
            System.out.println("发信人:"+messages[i].getFrom());
            System.out.println("收件人:"+messages[i].getReplyTo());
            System.out.println("接受时间:"+messages[i].getReceivedDate());
            System.out.println("邮件主题:"+messages[i].getSubject());
            System.out.println("邮件内容:"+messages[i].getContent());

            System.out.println("是否打开该邮件(yes/no)?：");
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String input = br.readLine();
            if("yes".equalsIgnoreCase(input)) {
                // 直接输出到控制台中
                messages[i].writeTo(System.out);
            }
        }
        folder.close(false);
        store.close();
    }
}
