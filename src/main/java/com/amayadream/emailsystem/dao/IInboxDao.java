package com.amayadream.emailsystem.dao;

import com.amayadream.emailsystem.pojo.Inbox;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * NAME   :  EmailSystem/com.amayadream.emailsystem.dao
 * Author :  Amayadream
 * Date   :  2015.12.13 15:57
 * TODO   :
 */
@Service("inboxDao")
public interface IInboxDao {
    List<Inbox> selectAll();
    Inbox selectInboxById(Inbox inbox);
    Inbox count(String userid);
    boolean insert(Inbox inbox);
    boolean updateStatus(Inbox inbox);
    boolean update(Inbox inbox);
    boolean delete(Inbox inbox);
    boolean deleteAll(String userid);
}
