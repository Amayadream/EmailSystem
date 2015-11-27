package com.amayadream.email.pojo;

/**
 * NAME   :  EmailSystem/com.amayadream.pojo
 * Author :  Amayadream
 * Date   :  2015.10.09 09:30
 * TODO   :  设置实体类
 */
public class Setting {
    /**
     * 设置id
     */
    public int sid;

    /**
     * 所属用户编号
     */
    public int userid;

    /**
     * 发信邮箱
     */
    public String sendmail;

    /**
     * 发信昵称
     */
    public String sendname;

    /**
     * 发信密码
     */
    public String sendpass;

    /**
     * SMTP服务器地址
     */
    public String smtpserver;

    /**
     * SMTP服务器端口
     */
    public String smtpport;

    /**
     * 是否设置
     */
    public int isset;

    /**
     * getter&setter
     */
    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getSendmail() {
        return sendmail;
    }

    public void setSendmail(String sendmail) {
        this.sendmail = sendmail;
    }

    public String getSendname() {
        return sendname;
    }

    public void setSendname(String sendname) {
        this.sendname = sendname;
    }

    public String getSendpass() {
        return sendpass;
    }

    public void setSendpass(String sendpass) {
        this.sendpass = sendpass;
    }

    public String getSmtpserver() {
        return smtpserver;
    }

    public void setSmtpserver(String smtpserver) {
        this.smtpserver = smtpserver;
    }

    public String getSmtpport() {
        return smtpport;
    }

    public void setSmtpport(String smtpport) {
        this.smtpport = smtpport;
    }

    public int getIsset() {
        return isset;
    }

    public void setIsset(int isset) {
        this.isset = isset;
    }
}
