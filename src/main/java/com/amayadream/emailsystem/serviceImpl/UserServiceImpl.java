package com.amayadream.emailsystem.serviceImpl;

import com.amayadream.emailsystem.dao.IUserDao;
import com.amayadream.emailsystem.pojo.User;
import com.amayadream.emailsystem.service.IUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * NAME   :  EmailSystem/com.amayadream.emailsystem.serviceImpl
 * Author :  Amayadream
 * Date   :  2015.11.27 15:34
 * TODO   :
 */
@Service("userService")
public class UserServiceImpl implements IUserService {
    @Resource
    private IUserDao userDao;

    @Resource
    private User user;

    public List<User> selectAll(int pageNo, int pageSize) {
        Map<String, Object> map = new HashMap<String, Object>();
        if(pageNo==1){
            map.put("startRow", 1);
            map.put("endRow", pageSize);
        }
        else{
            map.put("startRow", pageSize*(pageNo-1)+1);
            map.put("endRow", pageSize*pageNo);
        }
        return userDao.selectAll(map);
    }

    public User selectUserById(String id) {
        return userDao.selectUserById(id);
    }

    public User selectUserByUsername(String username) {
        return userDao.selectUserByUsername(username);
    }

    public User count() {
        return userDao.count();
    }

    public boolean insert(String username, String password, String email, String firsttime, String lasttime, int available) {
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
        user.setFirsttime(firsttime);
        user.setLasttime(lasttime);
        user.setAvailable(available);
        return userDao.insert(user);
    }

    public boolean update(String id, String username, String password, String email, String lasttime, int available) {
        user.setId(id);
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
        user.setLasttime(lasttime);
        user.setAvailable(available);
        return userDao.update(user);
    }

    public boolean updateLasttime(String id, String lasttime) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("id",id);
        map.put("lasttime",lasttime);
        return userDao.updateLasttime(map);
    }

    public boolean updateAvailable(String id, int available) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("id",id);
        map.put("available",available);
        return userDao.updateAvailable(map);
    }

    public boolean delete(int id) {
        return userDao.delete(id);
    }
}
