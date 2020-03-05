package com.util;

import java.util.List;

import com.dao.IBrandDao;
import com.dao.ICarDao;
import com.dao.ITypeDao;
import com.dao.IUserDao;
import com.dao.impl.BrandDaoImpl;
import com.dao.impl.CarDaoImpl;
import com.dao.impl.TypeDaoImpl;
import com.dao.impl.UserDaoImpl;
import com.entity.Record;

public class RecordGetBrandAndType {
	//record持有user和car对象,car再持有brand和type对象
	public static List<Record> recordGetBrandAndType(List<Record> list){
		
		recordGetUserAndCar(list);
		
		IBrandDao brandDao=new BrandDaoImpl();
		ITypeDao typeDao=new TypeDaoImpl();
		if(list!=null) {
			for (Record record : list) {
				try {
					//获取品牌名
					record.getCar().setBra(
							brandDao.selectBrandById(
									record.getCar().getBrand()));
					
					record.getCar().setTyp(
							typeDao.selecTypeById(
									record.getCar().getType()));
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		return list;
	}
	public static Record recordGetBrandAndType(Record record){
		
		IBrandDao brandDao=new BrandDaoImpl();
		ITypeDao typeDao=new TypeDaoImpl();
		try {
					//获取品牌名
					record.getCar().setBra(
							brandDao.selectBrandById(
									record.getCar().getBrand()));
					
					record.getCar().setTyp(
							typeDao.selecTypeById(
									record.getCar().getType()));
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		return record;
	}
	//record持有user和car对象
	public static List<Record> recordGetUserAndCar(List<Record> list){
		IUserDao userDao=new UserDaoImpl();
		ICarDao carDao=new CarDaoImpl();
		if (list!=null) {
			for (Record record : list) {
				try {
					record.setUser(userDao.selecUserById(record.getUserId()));
					record.setCar(carDao.selectCarByCarId(record.getCarId()));
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			return list;
		} else {
			return null;
		}
	}
}
