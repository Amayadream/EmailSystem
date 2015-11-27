package com.amayadream.emailsystem.dao;

import com.amayadream.emailsystem.pojo.Setting;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * NAME   :  EmailSystem/com.amayadream.emailsystem.dao
 * Author :  Amayadream
 * Date   :  2015.11.27 18:24
 * TODO   :
 */
@Service("settingDao")
public interface ISettingDao {
    public Setting selectSettingByUserid(String userid);

    public boolean indexSetting(Setting setting);

    public boolean updateIsset(Setting setting);

    public boolean update(Setting setting);

    public boolean delete(String userid);
}
