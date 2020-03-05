package com.view;

import java.util.List;

import com.entity.Car;
import com.entity.Record;
import com.entity.User;
import com.service.ICarService;
import com.service.IUserService;
import com.service.impl.CarServiceImpl;
import com.service.impl.UserServiceImpl;
import com.util.InputUtil;
import com.util.PrintCarUtil;

public class UserView {
	IUserService userService=new UserServiceImpl();
	ICarService carService=new CarServiceImpl();
	public void start() {
		System.out.println("================");
		System.out.println("欢迎访问二嗨租车");
		System.out.println("================");
		System.out.println("1.登录 2.注册 3.退出");
		String choose=InputUtil.next();
		switch (choose) {
		case "1":
			userLogOn();
			break;
		case "2":
			userRegister();
			break;
		case "3":
	
			break;
		default:
			break;
		}
		
		
	}
	//用户注册界面
	private void userRegister() {
		System.out.println("=====用户注册====>>>>");
		System.out.println("用户名:");
		String name=InputUtil.next();
		System.out.println("密码:");
		String password=InputUtil.next();
		System.out.println("================");
		int result = userService.userRegister(name, password);
		if (result>0) {
			System.out.println("注册成功!");
			userLogOn();
		} else {
			System.out.println("用户名已存在,请重新注册!");
			userRegister();
		}
	}
	public void userLogOn() {
		System.out.println("=====登录====>>>>");
		System.out.println("用户名:");
		String name=InputUtil.next();
		System.out.println("密码:");
		String password=InputUtil.next();
		System.out.println("================");
		User result = userService.userLogOn(name, password);
		if (result!=null) {
			System.out.println("欢迎"+result.getName()+"!");
			afterLogOn();
			
		} else {
			System.out.println("用户名或密码错误,请重新登录!");
			userLogOn();
		}
	}
	
	//登陆之后的界面
	public void afterLogOn() {
		boolean flag=true;
		while(flag) {
			//查看租车信息
			selectCarRecord();
			//面板
			System.out.println("输入0退出");
			System.out.println("输入1+汽车编号 进入租车订单");
			System.out.println("输入2+1 按价格降序排序 2+2 按价格升序排序");
			System.out.println("输入3+类型编号 按类型搜索");
			System.out.println("输入4+品牌编号 按品牌搜索");
			System.out.println("输入5 查看全部汽车");
			System.out.println("输入6 查看我的租车记录");
			System.out.println("输入7+汽车编号 还车");
			
			String choose=InputUtil.next();
			String[] split = choose.split("\\+");
			
			switch (split[0]) {
			case "0"://退出
				System.out.println("欢迎下次登录!");
				flag=false;
				break;
			case "1":
				Record record = carService.borrowCar(Integer.valueOf(split[1]));
				if (record!=null) {
					System.out.println("借车成功!车辆信息如下:");
					PrintCarUtil.printOneBorrowCar(record);
				} else {
					System.out.println("租车失败!");
				}
				
				break;
			case "2"://2+1 按价格降序排序
				switch (split[1]) {
				case "1":
					List<Car> list = carService.selectCarByStateAndOrderByPriceDesc();
					PrintCarUtil.printCar(list);
					break;
				case "2":
					List<Car> result = carService.selectCarByStateAndOrderByPrice();
					PrintCarUtil.printCar(result);
					break;
				default:
					break;
				}
				break;
			case "3":
				List<Car> type = carService.selectCarByType(split[1]);
				PrintCarUtil.printCar(type);
				break;
			case "4":
				List<Car> list = carService.selectCarByBrand(split[1]);
				PrintCarUtil.printCar(list);
				break;
			case "5":
				selectCarRecord();
				break;
			case "6":
				List<Record> userRecords = carService.userRecords();
				if (userRecords!=null) {
					PrintCarUtil.printCarRecords(userRecords);
				}
				break;
			case "7":
				Record returnRecord = carService.returnCar(Integer.valueOf(split[1]));
				if (returnRecord!=null) {
					System.out.println("还车成功!");
					PrintCarUtil.printOneReturnCar(returnRecord);
				} else {
					System.out.println("还车失败!");
				}
				
				break;
			default:
				System.out.println("输入有误!");
				break;
			}
		}
		
		
		
	}
	public static void main(String[] args) {
		UserView userView=new UserView();
		userView.start();
	}
	//查询租车信息
	public void selectCarRecord() {
		List<Car> result = carService.selectCarByState();
		PrintCarUtil.printCar(result);
		
	}
}
