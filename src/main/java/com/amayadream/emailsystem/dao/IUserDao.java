package com.amayadream.emailsystem.dao;

import com.amayadream.emailsystem.pojo.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * NAME   :  EmailSystem/com.amayadream.emailsystem.dao
 * Author :  Amayadream
 * Date   :  2015.11.27 15:22
 * TODO   :
 */
@Service("userDao")
public interface IUserDao {
    public List<User> selectAll(Map<String, Object> map);
    public User selectUserById(String id);
    public User selectUserByUsername(String username);
    public User count();
    public boolean insert(User user);
    public boolean update(User user);
    public boolean updateLasttime(Map<String, Object> map);
    public boolean updateAvailable(Map<String, Object> map);
    public boolean delete(int id);
}
