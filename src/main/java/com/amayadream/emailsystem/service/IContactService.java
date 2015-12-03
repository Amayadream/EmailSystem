package com.amayadream.emailsystem.service;

import com.amayadream.emailsystem.pojo.Contact;

import java.util.List;
import java.util.Map;

/**
 * NAME   :  EmailSystem/com.amayadream.emailsystem.service
 * Author :  Amayadream
 * Date   :  2015.11.27 17:32
 * TODO   :
 */
public interface IContactService {
    public List<Contact> selectAll(int startRow, int endRow, String userid);

    public Contact selectContactById(String cid, String userid);

    public Contact selectContactByName(String name, String userid);

    public List<Contact> selectContactByGroupid(String groupid, String userid);

    public Contact count(String userid);

    public Contact countByGroup(String userid, String groupid);

    public boolean insert(String userid, String name, String email, String groupid);

    public boolean update(String cid, String userid, String name, String email, String groupid);

    public boolean delete(String cid, String userid);
}
