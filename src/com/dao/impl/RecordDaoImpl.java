package com.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import com.dao.IRecordDao;
import com.entity.Record;
import com.util.JdbcTemplate;
import com.util.ResultSetHandler;

public class RecordDaoImpl implements IRecordDao {
	/**
	 * 增加租车记录
	 */
	@Override
	public int insertBorrowRecord(Connection conn,int userId, int carId,Timestamp borrowTime) throws Exception {
		String sql="INSERT INTO t_record(user_id,car_id,borrow_time)\r\n" + 
				"VALUES(?,?,?)";
		return JdbcTemplate.update(conn, sql,userId, carId,borrowTime);
	
	}

	/**
	 * 查询用户租车记录
	 */
	@Override
	public List<Record> selectRecordByUserId(int userId) throws Exception {
		String sql="SELECT *\r\n" + 
				"FROM t_record\r\n" + 
				"WHERE user_id=?";
		return JdbcTemplate.query(sql, new ResultSetHandler<Record>() {

			@Override
			public Record handleRow(ResultSet rs) throws SQLException {
				// TODO Auto-generated method stub
				return new Record(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getTimestamp(4), rs.getTimestamp(5),rs.getDouble(6));
			}
		}, userId);
	}
	/**
	 * 根据车辆ID查找信息
	 */
	@Override
	public List<Record> selectRecordByCarId(int carId) throws Exception {
		String sql="SELECT *\r\n" + 
				"FROM t_record\r\n" + 
				"WHERE car_id=?";
		return JdbcTemplate.query(sql, new ResultSetHandler<Record>() {

			@Override
			public Record handleRow(ResultSet rs) throws SQLException {
				// TODO Auto-generated method stub
				return new Record(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getTimestamp(4), rs.getTimestamp(5),rs.getDouble(6));
			}
		}, carId);
	}
	
	@Override
	public Record selectLastRecordByCarId(int carId) throws Exception {
		String sql="SELECT *\r\n" + 
				"FROM t_record\r\n" + 
				"WHERE car_id=?\r\n" + 
				"ORDER BY borrow_time DESC\r\n" + 
				"LIMIT 1";
		return JdbcTemplate.singleQuery(sql, new ResultSetHandler<Record>() {

			@Override
			public Record handleRow(ResultSet rs) throws SQLException {
				// TODO Auto-generated method stub
				return new Record(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getTimestamp(4), rs.getTimestamp(5),rs.getDouble(6));
			}
		}, carId);
	}
	/**
	 * 还车,record添加还车时间,租金总额
	 */
	@Override
	public int updateRecordReturnTimeByCarId(Connection conn, int carId,Timestamp returnTime,double payment) throws Exception {
		String sql="UPDATE t_record" + 
				" SET return_time=? , payment=?" + 
				" WHERE car_id=? order by borrow_time desc" + 
				" LIMIT 1";
		return JdbcTemplate.update(conn, sql,returnTime,payment,carId);
	}
	/**
	 * 查询所有交易记录
	 */
	@Override
	public List<Record> selectAllRecord() throws Exception {
		String sql="SELECT *" + 
				" FROM t_record";
		return JdbcTemplate.query(sql, new ResultSetHandler<Record>() {

			@Override
			public Record handleRow(ResultSet rs) throws SQLException {
				// TODO Auto-generated method stub
				return new Record(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getTimestamp(4), rs.getTimestamp(5),rs.getDouble(6));
			}
		});
	}

}
