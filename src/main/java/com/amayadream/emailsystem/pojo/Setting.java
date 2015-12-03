package com.amayadream.emailsystem.pojo;

import org.springframework.stereotype.Repository;

/**
 * NAME   :  EmailSystem/com.amayadream.emailsystem.pojo
 * Author :  Amayadream
 * Date   :  2015.11.27 15:01
 * TODO   :  设置实体类,对应Setting表
 */
@Repository("setting")
public class Setting {
    private String userid;      //用户编号
    private String sendname;    //发信昵称
    private String sendmail;    //发信邮箱
    private String sendpass;    //发信密码
    private String server;      //邮件服务器地址
    private String port;        //邮件服务器端口
    private int isset;          //设置标记

    /**
     * getter&setter
     * @return
     */
    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getSendname() {
        return sendname;
    }

    public void setSendname(String sendname) {
        this.sendname = sendname;
    }

    public String getSendmail() {
        return sendmail;
    }

    public void setSendmail(String sendmail) {
        this.sendmail = sendmail;
    }

    public String getSendpass() {
        return sendpass;
    }

    public void setSendpass(String sendpass) {
        this.sendpass = sendpass;
    }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public int getIsset() {
        return isset;
    }

    public void setIsset(int isset) {
        this.isset = isset;
    }
}
