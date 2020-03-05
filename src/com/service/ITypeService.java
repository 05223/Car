package com.service;

import java.util.List;

import com.entity.Type;

public interface ITypeService {
	
	//查找所有的type
	public List<Type> selectAllType();
}
