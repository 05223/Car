package com.service.impl;

import java.util.List;

import com.dao.ITypeDao;
import com.dao.impl.TypeDaoImpl;
import com.entity.Type;
import com.service.ITypeService;

public class TypeServiceImpl implements ITypeService {
	ITypeDao typeDao=new TypeDaoImpl();
	@Override
	public List<Type> selectAllType() {
		
		try {
			return typeDao.selecAllType();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
