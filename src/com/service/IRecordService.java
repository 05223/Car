package com.service;

import java.util.List;

import com.entity.Record;

public interface IRecordService {
	//查询所有用户记录
	public List<Record> selectAllRecord();
	//通过用户查询record
	public List<Record> selectRecordByUser(int userId);
	//通过用户查询record
	public List<Record> selectRecordByCarId(int carId);
}
