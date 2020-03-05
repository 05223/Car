package com.service;

import com.entity.User;

public interface IUserService {
	
	//用户登录
	public User userLogOn(String name,String password);
	//用户注册
	public int userRegister(String name,String password);
	
	//管理员登录
	public User adminLogOn(String name,String password);
}
