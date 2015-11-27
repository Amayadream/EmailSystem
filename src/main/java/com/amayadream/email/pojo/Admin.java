package com.amayadream.email.pojo;

/**
 * NAME   :  EmailSystem/com.amayadream.pojo
 * Author :  Amayadream
 * Date   :  2015.10.12 10:31
 * TODO   :  管理员实体类
 */
public class Admin {
    /**
     * 管理员编号
     */
    public int aid;

    /**
     * 管理员账号
     */
    public String username;

    /**
     * 管理员密码
     */
    public String password;

    /**
     * 管理员邮箱
     */
    public String email;

    /**
     * 注册时间
     */
    public String firsttime;

    /**
     * 最后登录时间
     */
    public String lasttime;

    /**
     * getter&setter
     */

    public int getAid() {
        return aid;
    }

    public void setAid(int aid) {
        this.aid = aid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirsttime() {
        return firsttime;
    }

    public void setFirsttime(String firsttime) {
        this.firsttime = firsttime;
    }

    public String getLasttime() {
        return lasttime;
    }

    public void setLasttime(String lasttime) {
        this.lasttime = lasttime;
    }
}
