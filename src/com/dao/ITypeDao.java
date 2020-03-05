package com.dao;

import java.util.List;

import com.entity.Type;

public interface ITypeDao {
	
	//根据ID查找
	public Type selecTypeById(int id)throws Exception;
	//根据name查找
	public Type selecTypeByName(String name)throws Exception;
	
	//
	public List<Type> selecAllType()throws Exception;
}
