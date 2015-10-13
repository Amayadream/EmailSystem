package com.amayadream.service;

import com.amayadream.pojo.Admin;

/**
 * NAME   :  EmailSystem/com.amayadream.service
 * Author :  Amayadream
 * Date   :  2015.10.12 10:59
 * TODO   :
 */
public interface IAdminService {
    public Admin queryAdminByUsername(String username);

    public String updateLastTime(int aid, String lasttime);
}
