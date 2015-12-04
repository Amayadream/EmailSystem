package com.amayadream.emailsystem.service;

import com.amayadream.emailsystem.pojo.Email;

import java.util.List;
import java.util.Map;

/**
 * NAME   :  EmailSystem/com.amayadream.emailsystem.service
 * Author :  Amayadream
 * Date   :  2015.12.04 21:23
 * TODO   :
 */
public interface IEmailService {
    public List<Email> selectAll(String userid, int startRow, int endRow);
    public Email selectEmailById(String userid, String eid);
    public Email count(String userid);
    public boolean insert(String uesrid, String emails, String subject, String content, String sendtime, String files, int status);
    public boolean update(String userid, int status);
    public boolean delete(String userid, String eid);
}
