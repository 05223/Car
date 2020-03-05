package com.dao;

import com.entity.User;

public interface IUserDao {
	
	//用户登录验证
	public User selectUserByNameAndPassword(String name,String password)throws Exception;
	//用户注册
	public int insertUser(String name,String password)throws Exception;
	//通过ID查找用户
	public User selecUserById(int id)throws Exception;
	
	//管理员登录验证
	public User selectAdminByNameAndPassword(String name,String password)throws Exception;
}
