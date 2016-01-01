package com.amayadream.emailsystem.service;

import com.amayadream.emailsystem.pojo.Inbox;

import java.util.List;

/**
 * NAME   :  EmailSystem/com.amayadream.emailsystem.service
 * Author :  Amayadream
 * Date   :  2015.12.13 16:02
 * TODO   :
 */
public interface IInboxService {
    List<Inbox> selectAll();
    Inbox selectInboxById(String userid, String id);
    Inbox count(String userid);
    boolean insert(String userid, String subject, String content, String sendmail, String receivemail, String sendtime, int isread, String files);
    boolean updateStatus(String userid, String id, int isread);
    boolean update(String id, String userid, String subject, String content, String sendmail, String receivemail, String sendtime, int isread, String files);
    boolean delete(String userid, String id);
    boolean deleteAll(String userid);
}
