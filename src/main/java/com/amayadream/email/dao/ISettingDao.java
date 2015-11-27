package com.amayadream.email.dao;

import com.amayadream.email.pojo.Setting;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * NAME   :  EmailSystem/com.amayadream.dao
 * Author :  Amayadream
 * Date   :  2015.10.09 09:35
 * TODO   :
 */

@Repository("settingDao")
public interface ISettingDao {
    public Setting selectSettingByUserid(int userid);

    public void indexSetting(Map<String, Object> map);

    public boolean updateIsset(Map<String, Object> map);

    public boolean updateSetting(Map<String, Object> map);

    public boolean deleteSetting(int userid);
}
