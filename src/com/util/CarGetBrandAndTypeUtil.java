package com.util;

import java.util.List;

import com.dao.IBrandDao;
import com.dao.ITypeDao;
import com.dao.impl.BrandDaoImpl;
import com.dao.impl.TypeDaoImpl;
import com.entity.Car;

public class CarGetBrandAndTypeUtil {
	public static List<Car> getBrandAndType(List<Car> list){
		IBrandDao brandDao=new BrandDaoImpl();
		ITypeDao typeDao=new TypeDaoImpl();
		for (Car car : list) {
			try {
				car.setBra(brandDao.selectBrandById(car.getBrand()));
				car.setTyp(typeDao.selecTypeById(car.getType()));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return list;
	}
	public static Car getBrandAndType(Car car){
		IBrandDao brandDao=new BrandDaoImpl();
		ITypeDao typeDao=new TypeDaoImpl();
			try {
				car.setBra(brandDao.selectBrandById(car.getBrand()));
				car.setTyp(typeDao.selecTypeById(car.getType()));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return car;
	}
}
