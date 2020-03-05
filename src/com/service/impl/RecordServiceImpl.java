package com.service.impl;

import java.util.List;

import com.dao.IRecordDao;
import com.dao.impl.RecordDaoImpl;
import com.entity.Record;
import com.service.IRecordService;
import com.util.RecordGetBrandAndType;

public class RecordServiceImpl implements IRecordService {
	IRecordDao recordDao=new RecordDaoImpl();
	@Override
	public List<Record> selectRecordByUser(int userId) {
		try {
			return RecordGetBrandAndType.recordGetBrandAndType(
					recordDao.selectRecordByUserId(userId));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	@Override
	public List<Record> selectRecordByCarId(int carId) {
		try {
			return RecordGetBrandAndType.recordGetBrandAndType(
					recordDao.selectRecordByCarId(carId));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 查询所有用户交易记录
	 */
	@Override
	public List<Record> selectAllRecord() {
		try {
			return RecordGetBrandAndType.recordGetBrandAndType(
					recordDao.selectAllRecord());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
