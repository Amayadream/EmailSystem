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
    public List<Contact> selectAll(int pageNo, int pageSize, String userid);

    public Contact selectContactById(String cid, String userid);

    public Contact selectContactByName(String name, String userid);

    public Contact count(String userid);

    public boolean insert(String userid, String name, String email, String groupid);

    public boolean update(String cid, String userid, String name, String email, String groupid);

    public boolean delete(String cid, String userid);
}
