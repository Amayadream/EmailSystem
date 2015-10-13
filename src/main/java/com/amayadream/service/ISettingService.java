package com.amayadream.service;

import com.amayadream.pojo.Setting;

import java.util.Map;

/**
 * NAME   :  EmailSystem/com.amayadream.service
 * Author :  Amayadream
 * Date   :  2015.10.09 09:45
 * TODO   :
 */
public interface ISettingService {

    /**
     * 根据userid查询设置信息,用于展示
     * @param userid
     * @return
     */
    public Setting querySettingByUserid(int userid);

    /**
     * 初始化设置表,在注册的同时将用户注册的邮箱和用户的id插入设置表作为初始设置
     * @param userid
     * @param sendmail
     */
    public void indexSetting(int userid, String sendmail, int isset);

    /**
     * 修改设置标记,初始为0,当修改设置之后,将isset置为1
     * @param userid
     * @return
     */
    public boolean updateIsset(int userid, int isset);

    /**
     * 修改设置
     * @param map
     * @return
     */
    public boolean updateSetting(Map<String, Object> map);

    /**
     * 删除用户设置,用于删除用户时调用
     * @param userid
     * @return
     */
    public boolean deleteSetting(int userid);
}
