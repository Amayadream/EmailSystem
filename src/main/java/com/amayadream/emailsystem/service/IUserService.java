package com.amayadream.emailsystem.service;

import com.amayadream.emailsystem.pojo.User;

import java.util.List;
import java.util.Map;

/**
 * NAME   :  EmailSystem/com.amayadream.emailsystem.service
 * Author :  Amayadream
 * Date   :  2015.11.27 15:33
 * TODO   :
 */
public interface IUserService {
    public List<User> selectAll(int pageNo, int pageSize);
    public User selectUserById(String id);
    public User selectUserByUsername(String username);
    public User count();
    public boolean insert(String username, String password, String email, String firsttime, String lasttime,int available);
    public boolean update(String id, String username, String password, String email);
    public boolean updateLasttime(String username, String lasttime);
    public boolean updateAvailable(String id, int available);
    public boolean delete(String id);
}
