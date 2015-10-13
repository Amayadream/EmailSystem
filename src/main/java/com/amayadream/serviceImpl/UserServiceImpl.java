package com.amayadream.serviceImpl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.amayadream.dao.IUserDao;
import com.amayadream.pojo.User;
import com.amayadream.service.IUserService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("userService")  
public class UserServiceImpl implements IUserService {  
    @Resource  
    private IUserDao userDao;  

    public List<User> queryAllUser(Map<String, Object> map) {
        return this.userDao.selectAllUser(map);
    }

    public User queryUserById(int id) {
        return this.userDao.selectUserById(id);
    }

    public User queryUserByUsername(String username) {
        return this.userDao.selectUserByUsername(username);
    }

    public User countUser(){
        return this.userDao.countUser();
    }

    public void insertUser(Map<String, Object> map) {
        this.userDao.insertUser(map);
    }

    public boolean updateLastTime(String lasttime, String username) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("lasttime",lasttime);
        map.put("username", username);
        return this.userDao.updateLastTime(map);
    }

    public boolean updateAvailable(int id, int available){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("id",id);
        map.put("available",available);
        return this.userDao.updateAvailable(map);
    }

    public boolean updateUser(Map<String, Object> map) {
        return this.userDao.updateUser(map);
    }

    public boolean deleteUser(int id) {
        return this.userDao.deleteUser(id);
    }

}  