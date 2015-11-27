package com.amayadream.email.dao;

import java.util.List;
import java.util.Map;

import com.amayadream.email.pojo.User;
import org.springframework.stereotype.Repository;

@Repository("userDao")
public interface IUserDao {
	
	public List<User> selectAllUser(Map<String, Object> map);
	
	public User selectUserById(int id);
	
	public User selectUserByUsername(String username);

	public User countUser();

	public void insertUser(Map<String, Object> map);

	public boolean updateLastTime(Map<String, Object> map);

	public boolean updateAvailable(Map<String, Object> map);

	public boolean updateUser(Map<String, Object> map);
	
	public boolean deleteUser(int id);
}