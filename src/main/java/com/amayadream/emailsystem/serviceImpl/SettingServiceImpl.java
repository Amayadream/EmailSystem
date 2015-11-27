package com.amayadream.emailsystem.serviceImpl;

import com.amayadream.emailsystem.dao.ISettingDao;
import com.amayadream.emailsystem.pojo.Setting;
import com.amayadream.emailsystem.service.ISettingService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * NAME   :  EmailSystem/com.amayadream.emailsystem.serviceImpl
 * Author :  Amayadream
 * Date   :  2015.11.27 18:33
 * TODO   :
 */
@Service("settingService")
public class SettingServiceImpl implements ISettingService {
    @Resource
    private ISettingDao settingDao;
    @Resource
    private Setting setting;


    public Setting selectSettingByUserid(String userid) {
        return settingDao.selectSettingByUserid(userid);
    }

    public boolean indexSetting(String userid, String sendmail, int isset) {
        setting.setUserid(userid);
        setting.setSendmail(sendmail);
        setting.setIsset(isset);
        return settingDao.indexSetting(setting);
    }

    public boolean updateIsset(String userid, int isset) {
        setting.setUserid(userid);
        setting.setIsset(isset);
        return settingDao.updateIsset(setting);
    }

    public boolean update(String userid, String sendmail, String sendname, String sendpass, String server, String port) {
        setting.setUserid(userid);
        setting.setSendmail(sendmail);
        setting.setSendname(sendname);
        setting.setSendpass(sendpass);
        setting.setServer(server);
        setting.setPort(port);
        return settingDao.update(setting);
    }

    public boolean delete(String userid) {
        return settingDao.delete(userid);
    }
}
