package com.amayadream.emailsystem.serviceImpl;

import com.amayadream.emailsystem.dao.IEmailDao;
import com.amayadream.emailsystem.pojo.Email;
import com.amayadream.emailsystem.service.IEmailService;
import com.amayadream.emailsystem.util.FileUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * NAME   :  EmailSystem/com.amayadream.emailsystem.serviceImpl
 * Author :  Amayadream
 * Date   :  2015.12.04 21:27
 * TODO   :
 */
@Service("emailService")
public class EmailServiceImpl implements IEmailService {
    @Resource
    private IEmailDao emailDao;
    @Resource
    private Email email;

    public List<Email> selectAll(String userid, int startRow, int endRow) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("startRow",startRow);
        map.put("endRow",endRow);
        map.put("userid",userid);
        return emailDao.selectAll(map);
    }

    public Email selectEmailById(String userid, String eid) {
        email.setUserid(userid);
        email.setEid(eid);
        return emailDao.selectEmailById(email);
    }

    public Email count(String userid) {
        return emailDao.count(userid);
    }

    public boolean insert(String uesrid, String emails, String subject, String content, String sendtime, String files, int status) {
        email.setUserid(uesrid);
        email.setEmails(emails);
        email.setSubject(subject);
        email.setContent(content);
        email.setSendtime(sendtime);
        email.setFiles(files);
        email.setStatus(status);
        return emailDao.insert(email);
    }

    public boolean update(String userid, String eid,  int status) {
        email.setUserid(userid);
        email.setEid(eid);
        email.setStatus(status);
        return emailDao.update(email);
    }

    public boolean delete(String userid, String eid) {
        email.setUserid(userid);
        email.setEid(eid);
        return emailDao.delete(email);
    }

    public boolean deleteAll(String userid){
        return emailDao.deleteAll(userid);
    }

}
