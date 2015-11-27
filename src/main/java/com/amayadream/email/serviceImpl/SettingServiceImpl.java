package com.amayadream.email.serviceImpl;

import com.amayadream.email.dao.ISettingDao;
import com.amayadream.email.pojo.Setting;
import com.amayadream.email.service.ISettingService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * NAME   :  EmailSystem/com.amayadream.serviceImpl
 * Author :  Amayadream
 * Date   :  2015.10.09 09:46
 * TODO   :
 */

@Service("settingService")
public class SettingServiceImpl implements ISettingService {
    @Resource
    private ISettingDao settingDao;


    public Setting querySettingByUserid(int userid) {
        return this.settingDao.selectSettingByUserid(userid);
    }

    public void indexSetting(int userid, String sendmail, int isset) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("userid",userid);
        map.put("sendmail",sendmail);
        map.put("isset",isset);
        this.settingDao.indexSetting(map);
    }

    public boolean updateIsset(int userid, int isset) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("userid",userid);
        map.put("isset",isset);
        return this.settingDao.updateIsset(map);
    }


    public boolean updateSetting(Map<String, Object> map) {
        return this.settingDao.updateSetting(map);
    }

    public boolean deleteSetting(int userid) {
        return this.settingDao.deleteSetting(userid);
    }
}
