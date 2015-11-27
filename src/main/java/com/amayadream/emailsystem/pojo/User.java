package com.amayadream.emailsystem.pojo;

import org.springframework.stereotype.Repository;

/**
 * NAME   :  EmailSystem/com.amayadream.emailsystem.pojo
 * Author :  Amayadream
 * Date   :  2015.11.27 14:56
 * TODO   :  用户实体类,对应Users表
 */

@Repository("user")
public class User {
    private String id;
    private String username;
    private String password;
    private String email;
    private String firsttime;
    private String lasttime;
    private int available;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public int getAvailable() {
        return available;
    }

    public void setAvailable(int available) {
        this.available = available;
    }
}
