package com.util;

import java.text.SimpleDateFormat;
import java.util.List;

import com.entity.Car;
import com.entity.Record;

public class PrintCarUtil {

	/**
	 * 打印租车信息表头
	 */
	public static void printCarTitle() {
		System.out.println("编号\t汽车名称\t备注\t品牌\t类型\t价格\t是否可租");
	}

	public static void printCar(Car car) {
		if (car != null) {
			printCarTitle();
			System.out.println(car.getId() + "\t" + car.getName() + "\t" + car.getNote() + "\t" + car.getBra().getName()
					+ "\t" + car.getTyp().getName() + "\t" + car.getPrice() + "\t"
					+ ((car.getState() == 0) ? "是" : "否"));
		}
	}

	/**
	 * 管理员看到的车辆信息界面,打印一辆
	 * 
	 * @param car
	 */
	public static void printCarA(Car car) {
		if (car != null) {
			System.out.println("编号\t汽车名称\t备注\t品牌\t类型\t价格\t是否可租\t是否上架");
			System.out.println(car.getId() + "\t" + car.getName() + "\t" + car.getNote() + "\t" + car.getBra().getName()
					+ "\t" + car.getTyp().getName() + "\t" + car.getPrice() + "/天" + "\t"
					+ ((car.getState() == 0) ? "可租" : "不可租") + "\t" + (car.getOnSale() == 0 ? "上架" : "下架"));
		}
	}

	/**
	 * 管理员看到的车辆信息界面,打印多辆
	 * 
	 * @param list
	 */
	public static void printCarA(List<Car> list) {
		if (list != null) {
			System.out.println("编号\t汽车名称\t备注\t品牌\t类型\t价格\t是否可租\t是否上架");
			for (Car car : list) {
				System.out.println(car.getId() + "\t" + car.getName() + "\t" + car.getNote() + "\t"
						+ car.getBra().getName() + "\t" + car.getTyp().getName() + "\t" + car.getPrice() + "/天" + "\t"
						+ ((car.getState() == 0) ? "可租" : "不可租") + "\t" + (car.getOnSale() == 0 ? "上架" : "下架"));
			}
		}
	}

	/**
	 * 打印集合中一组车的信息
	 * 
	 * @param list
	 */
	public static void printCar(List<Car> list) {
		if (list != null) {
			printCarTitle();
			for (Car car : list) {
				System.out.println(car.getId() + "\t" + car.getName() + "\t" + car.getNote() + "\t"
						+ car.getBra().getName() + "\t" + car.getTyp().getName() + "\t" + car.getPrice() + "\t"
						+ ((car.getState() == 0) ? "是" : "否"));
			}
		}
	}

	/**
	 * 一组记录
	 * 
	 * @param list
	 */
	public static void printBorrowCar(List<Record> list) {
		if (list != null) {
			System.out.println("编号\t汽车名称\t每日租金\t备注\t品牌\t类型\t借车时间");
			for (Record record : list) {
				System.out.println(record.getId() + "\t" + record.getCar().getName() + "\t" + record.getCar().getPrice()
						+ "\t" + record.getCar().getNote() + "\t" + record.getCar().getBrand() + "\t"
						+ record.getCar().getType() + "\t" + record.getBorrowTime());
			}
		}
	}

	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

	/**
	 * 用户看到的界面,打印一条借车记录
	 * 
	 * @param record
	 */
	public static void printOneBorrowCar(Record record) {
		if (record != null) {
			System.out.println("编号\t汽车名称\t每日租金\t备注\t品牌\t类型\t借车时间");
			System.out.println(record.getId() + "\t" + record.getCar().getName() + "\t" + record.getCar().getPrice()
					+ "\t" + record.getCar().getNote() + "\t" + record.getCar().getBra().getName() + "\t"
					+ record.getCar().getTyp().getName() + "\t" + sdf.format(record.getBorrowTime()));
		}
	}

	/**
	 * 用户看到的界面,打印一条还车记录信息
	 * 
	 * @param record
	 */
	public static void printOneReturnCar(Record record) {
		if (record != null) {
			System.out.println("编号\t汽车名称\t每日租金\t总租金\t备注\t品牌\t类型\t借车时间\t\t还车时间");
			System.out.println(record.getId() + "\t" + record.getCar().getName() + "\t" + record.getCar().getPrice()
					+ "\t" + record.getPayment() + "\t" + record.getCar().getNote() + "\t"
					+ record.getCar().getBra().getName() + "\t" + record.getCar().getTyp().getName() + "\t"
					+ sdf.format(record.getBorrowTime()) + "\t" + sdf.format(record.getReturnTime()));
		}
	}

	/**
	 * 用户看到的界面,租车记录信息,一条记录
	 * 
	 * @param record
	 */
	public static void printCarRecords(Record record) {
		if (record != null) {
			System.out.println("编号\t汽车编号\t汽车名称\t租金总额\t备注\t品牌\t类型\t借车时间\t还车时间");
			if (record.getReturnTime() != null) {
				System.out.println(record.getId() + "\t" + record.getCarId() + "\t" + record.getCar().getName() + "\t"
						+ record.getPayment() + "\t" + record.getCar().getNote() + "\t"
						+ record.getCar().getBra().getName() + "\t" + record.getCar().getTyp().getName() + "\t"
						+ sdf.format(record.getBorrowTime()) + "\t" + sdf.format(record.getReturnTime()));
			} else {
				System.out.println(record.getId() + "\t" + record.getCarId() + "\t" + record.getCar().getName() + "\t"
						+ 0 + "\t" + record.getCar().getNote() + "\t" + record.getCar().getBra().getName() + "\t"
						+ record.getCar().getTyp().getName() + "\t" + sdf.format(record.getBorrowTime()) + "\t" + "未还");
			}

		}
	}

	/**
	 * 用户看的界面,租车记录信息,打印一组数据
	 * 
	 * @param list
	 */
	public static void printCarRecords(List<Record> list) {
		if (list != null) {
			System.out.println("编号\t汽车编号\t汽车名称\t租金总额\t备注\t品牌\t类型\t借车时间\t\t还车时间");
			for (Record record : list) {
				if (record.getReturnTime() != null) {
					System.out.println(record.getId() + "\t" + record.getCarId() + "\t" + record.getCar().getName()
							+ "\t" + record.getPayment() + "\t" + record.getCar().getNote() + "\t"
							+ record.getCar().getBra().getName() + "\t" + record.getCar().getTyp().getName() + "\t"
							+ sdf.format(record.getBorrowTime()) + "\t" + sdf.format(record.getReturnTime()));
				} else {
					System.out.println(record.getId() + "\t" + record.getCarId() + "\t" + record.getCar().getName()
							+ "\t" + 0 + "\t" + record.getCar().getNote() + "\t" + record.getCar().getBra().getName()
							+ "\t" + record.getCar().getTyp().getName() + "\t" + sdf.format(record.getBorrowTime())
							+ "\t" + "未还");
				}

			}
		}
	}

	/**
	 * 管理员看的界面,打印租车记录信息,打印一组数据
	 * 
	 * @param list
	 */
	public static void printCarRecordsA(List<Record> list) {
		if (list != null && list.size() > 0) {
			System.out.println("编号\t汽车编号\t汽车名称\t用户编号\t用户名\t每日租金\t租金总额\t备注\t品牌\t类型\t借车时间\t\t还车时间");
			for (Record record : list) {
				if (record.getReturnTime() != null) {
					System.out.println(record.getId() + "\t" + record.getCarId() + "\t" + record.getCar().getName()
							+ "\t" + record.getUserId() + "\t" + record.getUser().getName() + "\t"
							+ record.getCar().getPrice() + "\t" + record.getPayment() + "\t" + record.getCar().getNote()
							+ "\t" + record.getCar().getBra().getName() + "\t" + record.getCar().getTyp().getName()
							+ "\t" + sdf.format(record.getBorrowTime()) + "\t" + sdf.format(record.getReturnTime()));
				} else {
					System.out.println(record.getId() + "\t" + record.getCarId() + "\t" + record.getCar().getName()
							+ "\t" + record.getUserId() + "\t" + record.getUser().getName() + "\t"
							+ record.getCar().getPrice() + "\t" + 0 + "\t" + record.getCar().getNote() + "\t"
							+ record.getCar().getBra().getName() + "\t" + record.getCar().getTyp().getName() + "\t"
							+ sdf.format(record.getBorrowTime()) + "\t" + "未还");
				}

			}
		}
	}
}
