package com.view;

import com.util.InputUtil;

public class StartView {
	public static void main(String[] args) {
		StartView startView=new StartView();
		startView.start();
	}
	public void start() {
		//登录界面
		System.out.println("欢迎访问二嗨租车");
		System.out.println("1.用户登录 2.管理员登录");
		String choose = InputUtil.next();
		switch (choose) {
		case "1":
			new UserViewServer().start();
			break;
		case "2":
			new AdminViewServer().start();
			break;
		default:
			System.out.println("输入有误");
			break;
		}
		
	}
}
