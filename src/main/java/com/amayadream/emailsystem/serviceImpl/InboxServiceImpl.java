package com.amayadream.emailsystem.serviceImpl;

import com.amayadream.emailsystem.dao.IInboxDao;
import com.amayadream.emailsystem.pojo.Inbox;
import com.amayadream.emailsystem.service.IInboxService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * NAME   :  EmailSystem/com.amayadream.emailsystem.serviceImpl
 * Author :  Amayadream
 * Date   :  2015.12.13 16:16
 * TODO   :
 */
@Service("inboxService")
public class InboxServiceImpl implements IInboxService{
    @Resource
    private IInboxDao inboxDao;
    @Resource
    private Inbox inbox;


    public List<Inbox> selectAll() {
        return inboxDao.selectAll();
    }

    public Inbox selectInboxById(String userid, String id) {
        inbox.setUserid(userid);
        inbox.setId(id);
        return inboxDao.selectInboxById(inbox);
    }

    public Inbox count(String userid) {
        return inboxDao.count(userid);
    }

    public boolean insert(String userid, String subject, String content, String sendmail, String receivemail, String sendtime, int isread, String files) {
        inbox.setUserid(userid);
        inbox.setSubject(subject);
        inbox.setContent(content);
        inbox.setSendmail(sendmail);
        inbox.setReceivemail(receivemail);
        inbox.setSendtime(sendtime);
        inbox.setIsread(isread);
        inbox.setFiles(files);
        return inboxDao.insert(inbox);
    }

    public boolean updateStatus(String userid, String id, int isread) {
        inbox.setUserid(userid);
        inbox.setId(id);
        inbox.setIsread(isread);
        return inboxDao.updateStatus(inbox);
    }

    public boolean update(String id, String userid, String subject, String content, String sendmail, String receivemail, String sendtime, int isread, String files) {
        inbox.setId(id);
        inbox.setUserid(userid);
        inbox.setSubject(subject);
        inbox.setContent(content);
        inbox.setSendmail(sendmail);
        inbox.setReceivemail(receivemail);
        inbox.setSendtime(sendtime);
        inbox.setIsread(isread);
        inbox.setFiles(files);
        return inboxDao.update(inbox);
    }

    public boolean delete(String userid, String id) {
        inbox.setId(id);
        inbox.setUserid(userid);
        return inboxDao.delete(inbox);
    }

    public boolean deleteAll(String userid) {
        return inboxDao.deleteAll(userid);
    }
}
