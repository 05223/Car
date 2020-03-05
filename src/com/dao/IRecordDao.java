package com.dao;

import java.sql.Connection;
import java.sql.Timestamp;
import java.util.List;

import com.entity.Record;

public interface IRecordDao {
	
	//添加租车数据
	public int insertBorrowRecord(Connection conn,int userId,int carId,Timestamp borrowTime)throws Exception;
	
	//查询所有交易记录
	public List<Record> selectAllRecord()throws Exception;
	
	//查询交易记录,用户
	public List<Record> selectRecordByUserId(int userId)throws Exception;
	
	//查询交易记录,车辆
	public List<Record> selectRecordByCarId(int carId)throws Exception;
	
	//查询最近交易记录
	public Record selectLastRecordByCarId(int carId)throws Exception;
	
	//交易记录,添加还车时间
	public int updateRecordReturnTimeByCarId(Connection conn,int carId,Timestamp returnTime,double payment)throws Exception;
}
