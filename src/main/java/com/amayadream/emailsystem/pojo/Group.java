package com.amayadream.emailsystem.pojo;

import org.springframework.stereotype.Repository;

/**
 * NAME   :  EmailSystem/com.amayadream.emailsystem.pojo
 * Author :  Amayadream
 * Date   :  2015.11.27 15:09
 * TODO   :  联系分组实体类,对应Groups表
 */
@Repository("group")
public class Group {
    private String gid;         //分组编号
    private String userid;      //用户编号
    private String groupname;   //分组名称

    // -- 临时属性 -- //
    private int count;          //组员数

    /**
     * getter&setter
     * @return
     */
    public String getGid() {
        return gid;
    }

    public void setGid(String gid) {
        this.gid = gid;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getGroupname() {
        return groupname;
    }

    public void setGroupname(String groupname) {
        this.groupname = groupname;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
