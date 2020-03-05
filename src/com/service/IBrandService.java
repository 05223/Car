package com.service;

import java.util.List;

import com.entity.Brand;

public interface IBrandService {
	
	//查找所有的brand
	public List<Brand> selectAllBrand();
}
