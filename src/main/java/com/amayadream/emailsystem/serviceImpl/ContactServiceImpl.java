package com.amayadream.emailsystem.serviceImpl;

import com.amayadream.emailsystem.dao.IContactDao;
import com.amayadream.emailsystem.pojo.Contact;
import com.amayadream.emailsystem.service.IContactService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * NAME   :  EmailSystem/com.amayadream.emailsystem.serviceImpl
 * Author :  Amayadream
 * Date   :  2015.11.27 17:38
 * TODO   :
 */
@Service("contactService")
public class ContactServiceImpl implements IContactService {
    @Resource
    private IContactDao contactDao;

    @Resource
    private Contact contact;

    public List<Contact> selectAll(int pageNo, int pageSize, String userid) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("userid",userid);
        if(pageNo==1){
            map.put("startRow", 1);
            map.put("endRow", pageSize);
        }
        else{
            map.put("startRow", pageSize*(pageNo-1)+1);
            map.put("endRow", pageSize*pageNo);
        }
        return contactDao.selectAll(map);
    }

    public Contact selectContactById(String cid, String userid) {
        contact.setCid(cid);
        contact.setUserid(userid);
        return contactDao.selectContactById(contact);
    }

    public Contact selectContactByName(String name, String userid) {
        contact.setName(name);
        contact.setUserid(userid);
        return contactDao.selectContactByName(contact);
    }

    public Contact count(String userid) {
        return contactDao.count(userid);
    }

    public boolean insert(String userid, String name, String email, String groupid) {
        contact.setUserid(userid);
        contact.setName(name);
        contact.setEmail(email);
        contact.setGroupid(groupid);
        return contactDao.insert(contact);
    }

    public boolean update(String cid, String userid, String name, String email, String groupid) {
        contact.setCid(cid);
        contact.setUserid(userid);
        contact.setName(name);
        contact.setEmail(email);
        contact.setGroupid(groupid);
        return contactDao.update(contact);
    }

    public boolean delete(String cid, String userid) {
        contact.setCid(cid);
        contact.setUserid(userid);
        return contactDao.delete(contact);
    }
}
