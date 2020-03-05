package com.view;

import java.util.List;
import com.entity.Brand;
import com.entity.Car;
import com.entity.Record;
import com.entity.Type;
import com.entity.User;
import com.service.IBrandService;
import com.service.ICarService;
import com.service.IRecordService;
import com.service.ITypeService;
import com.service.IUserService;
import com.service.impl.BrandServiceImpl;
import com.service.impl.CarServiceImpl;
import com.service.impl.RecordServiceImpl;
import com.service.impl.TypeServiceImpl;
import com.service.impl.UserServiceImpl;
import com.util.InputUtil;
import com.util.PrintCarUtil;

public class AdminView {
	
	IUserService userService=new UserServiceImpl();
	ICarService carService=new CarServiceImpl();
	IRecordService recordService=new RecordServiceImpl();
	
	public void start() {
		System.out.println("==============");
		System.out.println("欢迎访问二嗨租车");
		System.out.println("==============");
		System.out.println("1.登录 2.退出");
		String choose=InputUtil.next();
		switch (choose) {
		case "1":
			System.out.println("用户名:");
			String name=InputUtil.next();
			System.out.println("密码:");
			String password=InputUtil.next();
			User admin = userService.adminLogOn(name, password);
			if (admin!=null) {
				System.out.println("欢迎"+admin.getId()+":"+admin.getName());
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
		boolean flag=true;
		while (flag) {
			PrintCarUtil.printCarA(carService.selectAllCar());
			
			System.out.println("输入0退出");
			System.out.println("输入1+汽车编号 查看指定汽车");
			System.out.println("输入2+1 按照价格降序 2+2 按照价格升序");
			System.out.println("输入3+类型编号 按类型搜索");
			System.out.println("输入4+品牌编号 按品牌搜索");
			System.out.println("输入5 查看全部汽车");
			System.out.println("输入6 添加汽车");
			System.out.println("输入7+汽车编号 修改汽车信息");
			System.out.println("输入8+用户编号 查看指定用户租赁记录");
			System.out.println("输入9+汽车编号 查看指定汽车租赁记录");
			String choose=InputUtil.next();
			String[] split = choose.split("\\+");
			switch (split[0]) {
			case "0":
				System.out.println("欢迎下次登录!");
				flag=false;
				break;
			case "1":
				Car car = carService.selectCarByCarId(Integer.valueOf(split[1]));
				PrintCarUtil.printCar(car);
				break;
			case "2":
	
				break;
			case "3":
	
				break;
			case "4":
	
				break;
			case "5"://查看全部汽车
				PrintCarUtil.printCar(carService.selectAllCar());
				break;
			case "6":
				addCar();
				break;
			case "7"://修改汽车信息
				updateCar(Integer.valueOf(split[1]));
				break;
			case "8":
				List<Record> selectRecordByUser = recordService.selectRecordByUser(Integer.valueOf(split[1]));
				PrintCarUtil.printCarRecordsA(selectRecordByUser);
				break;
			case "9":
				selectRecordByCar(Integer.valueOf(split[1]));
				break;
			default:
				break;
			}
		}
	}
	/**
	 * 根据车辆ID查找租车记录
	 */
	private void selectRecordByCar(int carId) {
		List<Record> list = recordService.selectRecordByCarId(carId);
		PrintCarUtil.printCarRecordsA(list);
	}
	IBrandService brandService=new BrandServiceImpl();
	/**
	 * 添加汽车功能
	 */
	public void addCar() {
		System.out.println("输入以下信息:");
		System.out.println("--------------");
		System.out.println("品牌如下:");
		List<Brand> list = brandService.selectAllBrand();
		System.out.println("品牌编号\t品牌名");
		for (Brand brand : list) {
			System.out.println(brand.getId()+"\t"+brand.getName());
		}
		System.out.print("请选择品牌编号:");
		int brand=InputUtil.nextInt();
		
		System.out.println("-------------");
		System.out.println("类型如下:");
		ITypeService typeService=new TypeServiceImpl();
		System.out.println("类型编号\t类型名");
		List<Type> selectAllType = typeService.selectAllType();
		for (Type type : selectAllType) {
			System.out.println(type.getId()+"\t"+type.getName());
		}
		System.out.print("请选择类型编号:");
		int type=InputUtil.nextInt();
		System.out.println("-------------");
		System.out.print("型号:");
		String name=InputUtil.next();
		System.out.println("-------------");
		System.out.print("备注:");
		String note=InputUtil.next();
		System.out.println("-------------");
		System.out.println("每日租金:");
		double price=InputUtil.nextDouble();
		System.out.println("-------------");
		System.out.println("是否可借(0:可借 1:不可借):");
		int state=InputUtil.nextInt();
		System.out.println("-------------");
		System.out.println("是否上架(0:上架 1:下架):");
		int onSale=InputUtil.nextInt();
		int addCar = carService.addCar(name, note, brand, type, price, state, onSale);
		if (addCar>0) {
			System.out.println("添加成功!");
		} else {
			System.out.println("添加失败!");
		}
		
	}
	/**
	 * 修改汽车信息
	 * @param carId
	 */
	public void updateCar(int carId) {
		Car car = carService.selectCarByCarId(carId);
		PrintCarUtil.printCarA(car);
		System.out.println("输入要修改的内容的编号:");
		System.out.println("1.租赁价格 2.上架 3.下架");
		String s=InputUtil.next();
		switch (s) {
		case "1":
			System.out.println("新的租赁价格:");
			double price=InputUtil.nextDouble();
			
			int updateCarPrice = carService.updateCarPrice(carId, price);
			if (updateCarPrice>0) {
				System.out.println("修改成功!");
			} else {
				System.out.println("修改失败!");
			}
			break;
		case "2":
			int updateCarOnSale0 = carService.updateCarOnSale0(carId);
			if (updateCarOnSale0>0) {
				System.out.println("上架成功!");
			} else {
				System.out.println("上架失败!");
			}
			break;
		case "3":
			int updateCarOnSale1 = carService.updateCarOnSale1(carId);
			if (updateCarOnSale1>0) {
				System.out.println("上架成功!");
			} else {
				System.out.println("上架失败!");
			}
			break;
		default:
			break;
		}
	}
	
	public static void main(String[] args) {
		AdminView admin=new AdminView();
		admin.start();
	}
}
