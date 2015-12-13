package com.amayadream.emailsystem.pojo;

import org.springframework.stereotype.Repository;

/**
 * NAME   :  EmailSystem/com.amayadream.emailsystem.pojo
 * Author :  Amayadream
 * Date   :  2015.12.13 15:53
 * TODO   :  收件表
 */
@Repository("inbox")
public class Inbox {
    private String id;          //邮件编号
    private String userid;      //用户编号
    private String subject;     //邮件主题
    private String content;     //邮件内容
    private String sendmail;    //发信邮箱
    private String receivemail; //收信邮箱
    private String sendtime;    //发送时间
    private int isread;         //是否已读
    private String files;       //附件

    /**
     * getter&setter
     * @return
     */
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSendmail() {
        return sendmail;
    }

    public void setSendmail(String sendmail) {
        this.sendmail = sendmail;
    }

    public String getReceivemail() {
        return receivemail;
    }

    public void setReceivemail(String receivemail) {
        this.receivemail = receivemail;
    }

    public String getSendtime() {
        return sendtime;
    }

    public void setSendtime(String sendtime) {
        this.sendtime = sendtime;
    }

    public int getIsread() {
        return isread;
    }

    public void setIsread(int isread) {
        this.isread = isread;
    }

    public String getFiles() {
        return files;
    }

    public void setFiles(String files) {
        this.files = files;
    }
}
