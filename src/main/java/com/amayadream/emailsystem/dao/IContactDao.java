package com.amayadream.emailsystem.dao;

import com.amayadream.emailsystem.pojo.Contact;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * NAME   :  EmailSystem/com.amayadream.emailsystem.dao
 * Author :  Amayadream
 * Date   :  2015.11.27 17:26
 * TODO   :
 */
@Service("contactDao")
public interface IContactDao {
    public List<Contact> selectAll(Map<String, Object> map);

    public Contact selectContactById(Contact contact);

    public Contact selectContactByName(Contact contact);

    public List<Contact> selectContactByGroupid(Contact contact);

    public Contact selectContactByEmail(Contact contact);

    public Contact count(String userid);

    public Contact countByGroup(Contact contact);

    public boolean insert(Contact contact);

    public boolean update(Contact contact);

    public boolean delete(Contact contact);

    public boolean deleteAll(String userid);
}
