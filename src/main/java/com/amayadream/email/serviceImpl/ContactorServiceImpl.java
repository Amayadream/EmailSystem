package com.amayadream.email.serviceImpl;

import com.amayadream.email.dao.IContactorDao;
import com.amayadream.email.dao.IContactorDao;
import com.amayadream.email.pojo.Contactor;
import com.amayadream.email.service.IContactorService;
import com.amayadream.email.pojo.Contactor;
import com.amayadream.email.service.IContactorService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * NAME   :  EmailSystem/com.amayadream.serviceImpl
 * Author :  Amayadream
 * Date   :  2015.10.07 22:54
 * TODO   :
 */

@Service("contactorService")
public class ContactorServiceImpl implements IContactorService {

    @Resource
    private IContactorDao contactorDao;

    public List<Contactor> queryAllContactor(Map<String, Object> map) {
        return this.contactorDao.selectAllContactor(map);
    }

    public Contactor queryContactorByCid(Map<String, Object> map) {
        return this.contactorDao.selectContactorByCid(map);
    }

    public Contactor queryContactorByCname(Map<String, Object> map) {
        return this.contactorDao.selectContactorByCname(map);
    }

    public Contactor countCount(int userid) {
        return this.contactorDao.countContactor(userid);
    }

    public void insertContactor(Map<String, Object> map) {
        this.contactorDao.insertContactor(map);
    }

    public boolean updateContactor(Map<String, Object> map) {
        return this.contactorDao.updateContactor(map);
    }

    public boolean deleteContactor(Map<String, Object> map) {
        return this.contactorDao.deleteContactor(map);
    }
}
