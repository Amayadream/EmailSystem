package com.amayadream.email.service;

import com.amayadream.email.pojo.User;

import java.util.List;
import java.util.Map;

public interface IUserService {
    public List<User> queryAllUser(Map<String, Object> map);

    public User queryUserById(int id);

    public User queryUserByUsername(String username);

    public User countUser();

    public void insertUser(Map<String, Object> map);

    public boolean updateLastTime(String lasttime, String username);

    public boolean updateAvailable(int id, int available);

    public boolean updateUser(Map<String, Object> map);

    public boolean deleteUser(int id);
}