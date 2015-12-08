package com.amayadream.emailsystem.dao;

import com.amayadream.emailsystem.pojo.Email;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * NAME   :  EmailSystem/com.amayadream.emailsystem.dao
 * Author :  Amayadream
 * Date   :  2015.12.04 21:08
 * TODO   :
 */
@Service("emailDao")
public interface IEmailDao {
    public List<Email> selectAll(Map<String, Object> map);
    public Email selectEmailById(Email email);
    public Email count(String userid);
    public boolean insert(Email email);
    public boolean update(Email email);
    public boolean delete(Email email);
    public boolean deleteAll(String userid);
}
