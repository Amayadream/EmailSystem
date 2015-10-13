package com.amayadream.pojo;

public class User {

	/**
	 * 用户编号
	 */
	public int id;

	/**
	 * 用户名
	 */
	public String username;

	/**
	 * 密码
	 */
	public String password;

	/**
	 * 邮箱
	 */
	public String email;

	/**
	 * 注册时间
	 */
	public String firsttime;

	/**
	 * 最后登录
	 */
	public String lasttime;

	/**
	 * 可用标记
	 */
	public int available;
	
	/**
	 * getter&setter
	 */

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirsttime() {
		return firsttime;
	}

	public void setFirsttime(String firsttime) {
		this.firsttime = firsttime;
	}

	public String getLasttime() {
		return lasttime;
	}

	public void setLasttime(String lasttime) {
		this.lasttime = lasttime;
	}

	public int getAvailable() {
		return available;
	}

	public void setAvailable(int available) {
		this.available = available;
	}
}
