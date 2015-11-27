package com.amayadream.emailsystem.service;

import com.amayadream.emailsystem.pojo.Setting;

/**
 * NAME   :  EmailSystem/com.amayadream.emailsystem.service
 * Author :  Amayadream
 * Date   :  2015.11.27 18:30
 * TODO   :
 */
public interface ISettingService {
    public Setting selectSettingByUserid(String userid);

    public boolean indexSetting(String userid, String sendmail, int isset);

    public boolean updateIsset(String userid, int isset);

    public boolean update(String userid, String sendmail, String sendname, String sendpass, String server, String port);

    public boolean delete(String userid);
}
