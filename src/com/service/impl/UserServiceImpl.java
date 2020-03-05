package com.service.impl;

import com.dao.IUserDao;
import com.dao.impl.UserDaoImpl;
import com.entity.User;
import com.service.IUserService;

public class UserServiceImpl implements IUserService {
	IUserDao userDao=new UserDaoImpl();
	private static User user=null;
	public User getUser() {
		return user;
	}
	@Override
	//登录
	public User userLogOn(String name, String password) {
		try {
			user = userDao.selectUserByNameAndPassword(name, password);
			return user;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int userRegister(String name, String password) {
		
		try {
			return userDao.insertUser(name, password);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	/**
	 * 管理员登录服务
	 */
	@Override
	public User adminLogOn(String name, String password) {
		try {
			return userDao.selectAdminByNameAndPassword(name, password);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
