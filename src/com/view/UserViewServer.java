package com.view;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.client.TCPClient;
import com.entity.Car;
import com.entity.Record;
import com.entity.User;
import com.util.InputUtil;
import com.util.PrintCarUtil;

public class UserViewServer {
	// socket套接字
	TCPClient tcpClient = new TCPClient();

	// 开始界面
	public void start() {
		System.out.println("================");
		System.out.println("欢迎访问二嗨租车");
		System.out.println("================");
		System.out.println("1.登录 2.注册 3.退出");
		String choose = InputUtil.next();
		switch (choose) {
		case "1":
			userLogOn();
			break;
		case "2":
			userRegister();
			break;
		case "3":
			System.out.println("欢迎下次登录!");
			break;
		default:
			break;
		}

	}

	/**
	 * 用户注册界面
	 */
	private void userRegister() {
		System.out.println("=====用户注册====>>>>");
		System.out.println("用户名:");
		String name = InputUtil.next();
		System.out.println("密码:");
		String password = InputUtil.next();
		System.out.println("================");

		String result = tcpClient.connectAndSendMsg("0+2+" + name + "+" + password);

		if (Integer.valueOf(result) > 0) {
			System.out.println("注册成功!");
			userLogOn();
		} else {
			System.out.println("用户名已存在,请重新注册!");
			userRegister();
		}
	}

	/**
	 * 用户登录
	 */
	public void userLogOn() {
		System.out.println("=====登录====>>>>");
		System.out.println("用户名:");
		String name = InputUtil.next();
		System.out.println("密码:");
		String password = InputUtil.next();
		System.out.println("================");

		String result = tcpClient.connectAndSendMsg("0+1+" + name + "+" + password);

		User user = JSON.parseObject(result, User.class);
		if (user != null) {
			System.out.println("欢迎" + user.getName() + "!");
			afterLogOn();
		} else {
			System.out.println("用户名或密码错误,请重新登录!");
			userLogOn();
		}
	}

	/**
	 * 登录之后的界面
	 */
	public void afterLogOn() {
		boolean flag = true;
		while (flag) {
			// 查看租车信息
			PrintCarUtil.printCar(JSONObject.parseArray(tcpClient.connectAndSendMsg("5"), Car.class));
			// 面板
			System.out.println("输入0退出");
			System.out.println("输入1+汽车编号 进入租车订单");
			System.out.println("输入2+1 按价格降序排序 2+2 按价格升序排序");
			System.out.println("输入3+类型编号 按类型搜索");
			System.out.println("输入4+品牌编号 按品牌搜索");
			System.out.println("输入5 查看全部汽车");
			System.out.println("输入6 查看我的租车记录");
			System.out.println("输入7+汽车编号 还车");

			String choose = InputUtil.next();
			String[] split = choose.split("\\+");

			switch (split[0]) {
			case "0":// 退出
				System.out.println("欢迎下次登录!");
				flag = false;
				break;
			case "1":// 输入1+汽车编号 进入租车订单
				String string = tcpClient.connectAndSendMsg("1+" + split[1]);
				Record record = JSON.parseObject(string, Record.class);

				if (record != null) {
					System.out.println("借车成功!车辆信息如下:");
					PrintCarUtil.printOneBorrowCar(record);
				} else {
					System.out.println("租车失败!");
				}

				break;
			case "2":// 2+1 按价格降序排序
				switch (split[1]) {
				case "1":
					PrintCarUtil.printCar(JSONObject.parseArray(tcpClient.connectAndSendMsg("2+1"), Car.class));
					break;
				case "2":
					PrintCarUtil.printCar(JSONObject.parseArray(tcpClient.connectAndSendMsg("2+2"), Car.class));
					break;
				default:
					System.out.println("输入有误,请重新输入!");
					break;
				}
				break;
			case "3":// 3+类型编号 按类型搜索
				if (JSONObject.parseArray(tcpClient.connectAndSendMsg("3+" + split[1]), Car.class) != null) {
					PrintCarUtil
							.printCar(JSONObject.parseArray(tcpClient.connectAndSendMsg("3+" + split[1]), Car.class));
				} else {
					System.out.println("无此类型!");
				}

				break;
			case "4":// 输入4+品牌编号 按品牌搜索
				if (JSONObject.parseArray(tcpClient.connectAndSendMsg("4+" + split[1]), Car.class) != null) {
					PrintCarUtil
							.printCar(JSONObject.parseArray(tcpClient.connectAndSendMsg("4+" + split[1]), Car.class));
				} else {
					System.out.println("无此品牌!");
				}

				break;
			case "5":// 输入5 查看全部汽车
				PrintCarUtil.printCar(JSONObject.parseArray(tcpClient.connectAndSendMsg("5"), Car.class));
				break;
			case "6":// 输入6 查看我的租车记录
				if (JSONObject.parseArray(tcpClient.connectAndSendMsg("6"), Record.class) != null) {
					PrintCarUtil.printCarRecords(JSONObject.parseArray(tcpClient.connectAndSendMsg("6"), Record.class));
				} else {
					System.out.println("暂无租车记录!");
				}

				break;
			case "7":// 输入7+汽车编号 还车

				Record parseObject = JSON.parseObject(tcpClient.connectAndSendMsg("7+" + split[1]), Record.class);
				if (parseObject != null) {
					System.out.println("还车成功!");
					PrintCarUtil.printOneReturnCar(parseObject);
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
		UserViewServer userView = new UserViewServer();
		userView.start();
	}

}
