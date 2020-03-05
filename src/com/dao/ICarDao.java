package com.dao;

import java.sql.Connection;
import java.util.List;

import com.entity.Car;

public interface ICarDao {
	
	//查询车租赁记录,通过状态
	public List<Car> selectCarByState() throws Exception;
	
	//查询所有车辆
	public List<Car> selectAllCar() throws Exception;
	
	//查询车租赁记录
	public Car selectCarByCarId(int carId) throws Exception;
	
	//添加汽车
	public int insertCar(String name,String note,int brand,int type,double price,int state,int onSale);
	
	//修改汽车上下架状态,下架
	public int updateCarOnSale1(int carId)throws Exception;
	
	//修改汽车上下架状态,上架
	public int updateCarOnSale0(int carId)throws Exception;
	
	//修改汽车价格
	public int updateCarPrice(int carId,double price)throws Exception;
	
	//查询车租赁记录,价格升序
	public List<Car> selectCarByStateAndOrderByPrice() throws Exception;
	
	//查询车租赁记录,价格降序
	public List<Car> selectCarByStateAndOrderByPriceDesc() throws Exception;
	
	//品牌
	public List<Car> selectCarByBrand(int brand) throws Exception;
	
	//类型查询
	public List<Car> selectCarByType(int type) throws Exception;
	
	//租车,车辆状态改变
	public int updateCarState(Connection conn,int carId)throws Exception;
	
	//还车,租借状态改变
	public int updateCarStateReturn(Connection conn,int carId)throws Exception;
	
	
}
