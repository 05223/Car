package com.view;

import java.util.List;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.client.TCPClient;
import com.entity.Brand;
import com.entity.Car;
import com.entity.Record;
import com.entity.Type;
import com.entity.User;
import com.service.ICarService;
import com.service.IRecordService;
import com.service.IUserService;
import com.service.impl.CarServiceImpl;
import com.service.impl.RecordServiceImpl;
import com.service.impl.UserServiceImpl;
import com.util.InputUtil;
import com.util.PrintCarUtil;

public class AdminViewServer {

	TCPClient tcpClient = new TCPClient();
	IUserService userService = new UserServiceImpl();
	ICarService carService = new CarServiceImpl();
	IRecordService recordService = new RecordServiceImpl();

	// 开始界面
	public void start() {
		System.out.println("==============");
		System.out.println("欢迎访问二嗨租车");
		System.out.println("==============");
		System.out.println("1.登录 2.退出");
		String choose = InputUtil.next();
		switch (choose) {
		case "1":
			// 登录验证
			System.out.println("用户名:");
			String name = InputUtil.next();
			System.out.println("密码:");
			String password = InputUtil.next();

			User admin = JSON.parseObject(tcpClient.connectAndSendMsg("10+" + name + "+" + password), User.class);

			if (admin != null) {
				System.out.println("欢迎管理员" + admin.getId() + ":" + admin.getName());
				afterLogOn();
			} else {
				System.out.println("登录失败!");
				start();
			}
			break;
		case "2":
			System.out.println("欢迎下次登录!");
			break;
		default:
			System.out.println("输入有误!");
			start();
			break;
		}
	}

	public void afterLogOn() {
		boolean flag = true;
		while (flag) {
			System.out.println("============================================================");
			PrintCarUtil.printCarA(carService.selectAllCar());
			System.out.println();
			System.out.println("输入0退出");
			System.out.println("输入1+汽车编号 查看指定汽车");
			System.out.println("输入5 查看全部汽车");
			System.out.println("输入6 添加汽车");
			System.out.println("输入7+汽车编号 修改汽车信息");
			System.out.println("输入8+用户编号 查看指定用户租赁记录");
			System.out.println("输入9+汽车编号 查看指定汽车租赁记录");
			System.out.println("输入10 查看汽车记录");

			String choose = InputUtil.next();
			String[] split = choose.split("\\+");
			switch (split[0]) {
			case "0":// 输入0退出
				System.out.println("欢迎下次登录!");
				flag = false;
				break;
			case "1":// 输入1+汽车编号 查看指定汽车
				if (JSON.parseObject(
						tcpClient.connectAndSendMsg("11+" + split[1]), Car.class)!=null) {
					PrintCarUtil.printCarA(JSON.parseObject(
							tcpClient.connectAndSendMsg("11+" + split[1]), Car.class));
				} else {
					System.out.println("查无此车");
				}
				
				break;
			case "5":// 查看全部汽车
				PrintCarUtil.printCar(JSONObject.parseArray(
						tcpClient.connectAndSendMsg("15"), Car.class));
				break;
			case "6":// 输入6 添加汽车
				addCar();
				break;
			case "7":// 输入7+汽车编号 修改汽车信息
				updateCar(Integer.valueOf(split[1]));
				break;
			case "8":// 输入8+用户编号 查看指定用户租赁记录
				List<Record> list = JSONObject.parseArray(
						tcpClient.connectAndSendMsg("18+" + split[1]), Record.class);
				if (list != null) {
					PrintCarUtil.printCarRecordsA(list);
				} else {
					System.out.println("无此人的租赁记录!");
				}
				break;
			case "9":// 输入9+汽车编号 查看指定汽车租赁记录
				List<Record> array = JSONObject.parseArray(
						tcpClient.connectAndSendMsg("19+" + split[1]), Record.class);
				if (array != null) {
					PrintCarUtil.printCarRecordsA(array);
				} else {
					System.out.println("暂无此车的租赁记录!");
				}
				break;
			case "10":
				List<Record> parseArray = JSONObject.parseArray(
						tcpClient.connectAndSendMsg("20"),Record.class);
				if (parseArray != null) {
					PrintCarUtil.printCarRecordsA(parseArray);
				}
				break;
			default:
				System.out.println("输入有误,请重新输入!");
				break;
			}
		}
	}

	/**
	 * 添加汽车功能
	 */
	public void addCar() {
		System.out.println("请分别输入以下信息:");
		System.out.println("--------------");
		System.out.println("品牌如下:");
		System.out.println("品牌编号\t品牌名");
		List<Brand> list = JSONObject.parseArray(tcpClient.connectAndSendMsg("16+1"), Brand.class);

		for (Brand brand : list) {
			System.out.println(brand.getId() + "\t" + brand.getName());
		}
		System.out.print("请选择品牌编号:");
		int brand = InputUtil.nextInt();

		System.out.println("-------------");
		System.out.println("类型如下:");

		System.out.println("类型编号\t类型名");

		List<Type> array = JSONObject.parseArray(tcpClient.connectAndSendMsg("16+2"), Type.class);

		for (Type type : array) {
			System.out.println(type.getId() + "\t" + type.getName());
		}

		System.out.print("请选择类型编号:");
		int type = InputUtil.nextInt();

		System.out.println("-------------");
		System.out.print("型号:");
		String name = InputUtil.next();
		System.out.println("-------------");
		System.out.print("备注:");
		String note = InputUtil.next();
		System.out.println("-------------");
		System.out.println("每日租金:");
		double price = InputUtil.nextDouble();
		System.out.println("-------------");
		System.out.println("是否可借(0:可借 1:不可借):");
		int state = InputUtil.nextInt();
		System.out.println("-------------");
		System.out.println("是否上架(0:上架 1:下架):");
		int onSale = InputUtil.nextInt();

		String result = tcpClient.connectAndSendMsg(
				"16+3+" + name + "+" + note + "+" + brand + "+" + type + "+" + price + "+" + state + "+" + onSale);

		if (Integer.valueOf(result) > 0) {
			System.out.println("添加成功!");
		} else {
			System.out.println("添加失败!");
		}

	}

	/**
	 * 修改汽车信息
	 * 
	 * @param carId
	 */
	public void updateCar(int carId) {

		// 打印要修改汽车的信息
		Car car = JSON.parseObject(tcpClient.connectAndSendMsg("17+1+" + carId), Car.class);
		PrintCarUtil.printCarA(car);

		System.out.println("输入要修改的内容的编号:");
		System.out.println("1.租赁价格 2.上架 3.下架");
		String s = InputUtil.next();
		switch (s) {
		case "1":
			System.out.println("请输入新的租赁价格:");
			double price = InputUtil.nextDouble();

			String updateCarPrice = tcpClient.connectAndSendMsg("17+2+" + carId + "+" + price);
			// 判断结果
			if (Integer.valueOf(updateCarPrice) > 0) {
				System.out.println("修改成功!");
			} else {
				System.out.println("修改失败!");
			}
			break;
		case "2":

			String updateCarOnSale0 = tcpClient.connectAndSendMsg("17+3+" + carId);
			// 判断结果
			if (Integer.valueOf(updateCarOnSale0) > 0) {
				System.out.println("上架成功!");
			} else {
				System.out.println("上架失败!");
			}
			break;
		case "3":

			String updateCarOnSale1 = tcpClient.connectAndSendMsg("17+4+" + carId);
			// 判断结果
			if (Integer.valueOf(updateCarOnSale1) > 0) {
				System.out.println("下架成功!");
			} else {
				System.out.println("下架失败!");
			}
			break;
		default:
			break;
		}
	}

	public static void main(String[] args) {
		AdminViewServer admin = new AdminViewServer();
		admin.start();
	}
}
