package com.dao;

import java.util.List;

import com.entity.Brand;

public interface IBrandDao {
	
	//通过ID查找品牌
	public Brand selectBrandById(int id) throws Exception;
	
	//通过姓名查询ID
	public Brand selectBrandByName(String name) throws Exception;
	
	//查找所有品牌
	public List<Brand> selectAllBrand() throws Exception;
	
}
