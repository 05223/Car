package com.service.impl;

import java.util.List;

import com.dao.IBrandDao;
import com.dao.impl.BrandDaoImpl;
import com.entity.Brand;
import com.service.IBrandService;

public class BrandServiceImpl implements IBrandService {
	IBrandDao brandDao=new BrandDaoImpl();
	@Override
	public List<Brand> selectAllBrand() {
		// TODO Auto-generated method stub
		try {
			return brandDao.selectAllBrand();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
