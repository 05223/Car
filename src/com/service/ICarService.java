package com.service;

import java.util.List;

import com.entity.Car;
import com.entity.Record;

public interface ICarService {
	
	//查询上架车辆
	public List<Car> selectCarByState();
	
	//查询所有车辆
	public List<Car> selectAllCar();
	
	//查询指定汽车
	public Car selectCarByCarId(int carId);
	
	//添加汽车
	public int addCar(String name,String note,int brand,int type,double price,int state,int onSale);
	
	//查询上架车辆,按价格升序
	public List<Car> selectCarByStateAndOrderByPrice();
	
	//查询上架车辆,按价格降序
	public List<Car> selectCarByStateAndOrderByPriceDesc();
	
	//查询上架车辆,品牌查询
	public List<Car> selectCarByBrand(String name);
	
	//查询上架车辆,类型查询
	public List<Car> selectCarByType(String name);
	
	//租车服务
	public Record borrowCar(int carId) ;
	
	//还车服务
	public Record returnCar(int carId);
	
	//租车记录
	public List<Record> userRecords();
	
	//汽车上架
	public int updateCarOnSale0(int carId);
	//汽车下架
	public int updateCarOnSale1(int carId);
	//修改汽车价格
	public int updateCarPrice(int carId,double price);
}
