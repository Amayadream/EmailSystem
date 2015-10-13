package com.amayadream.pojo;

import java.util.List;

/**
 * NAME   :  EmailSystem/com.amayadream.pojo
 * Author :  Amayadream
 * Date   :  2015.10.07 22:31
 * TODO   :
 */
public class Contactor {

    /**
     * 联系人编号
     */
    public int cid;

    /**
     * 所属用户的用户编号

     */
    public int userid;

    /**
     * 联系人姓名
     */
    public String cname;

    /**
     * 联系人邮箱
     */
    public String email;

    /**
     * 分组
     */
    public int groups;

    public String groupsname;

    /**
     * getter&setter
     */
    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getGroups() {
        return groups;
    }

    public void setGroups(int groups) {
        this.groups = groups;
    }

    public String getGroupsname() {
        return groupsname;
    }

    public void setGroupsname(String groupsname) {
        this.groupsname = groupsname;
    }
}
