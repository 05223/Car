package com.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.dao.ICarDao;
import com.entity.Car;
import com.util.JdbcTemplate;
import com.util.ResultSetHandler;

public class CarDaoImpl implements ICarDao {
	/**
	 * 用户,查询车辆,车辆上架状态
	 */
	@Override
	public List<Car> selectCarByState() throws Exception{
		String sql="SELECT *" + 
				" FROM t_car" + 
				" WHERE state=0 and on_sale=0";
		return JdbcTemplate.query(sql, new ResultSetHandler<Car>() {

			@Override
			public Car handleRow(ResultSet rs) throws SQLException {
				return new Car(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt(4),rs.getInt(5),rs.getDouble(6),rs.getInt(7),rs.getInt(8));
			}
		});
	}
	/**
	 * 查询所有车辆
	 */
	@Override
	public List<Car> selectAllCar() throws Exception{
		String sql="SELECT *" + 
				" FROM t_car";
		return JdbcTemplate.query(sql, new ResultSetHandler<Car>() {
			@Override
			public Car handleRow(ResultSet rs) throws SQLException {
				return new Car(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt(4),rs.getInt(5),rs.getDouble(6),rs.getInt(7),rs.getInt(8));
			}
		});
	}
	/**
	 * 用户,查询车辆,根据价格升序
	 */
	@Override
	public List<Car> selectCarByStateAndOrderByPrice() throws Exception {
		String sql="SELECT *" + 
				" FROM t_car" + 
				" WHERE state=0 and on_sale=0" + 
				" ORDER BY price";
		return JdbcTemplate.query(sql, new ResultSetHandler<Car>() {

			@Override
			public Car handleRow(ResultSet rs) throws SQLException {
				return new Car(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt(4),rs.getInt(5),rs.getDouble(6),rs.getInt(7),rs.getInt(8));
			}
		});
	}
	/**
	 * 用户,查询车辆,根据价格降序
	 */
	@Override
	public List<Car> selectCarByStateAndOrderByPriceDesc() throws Exception {
		String sql="SELECT *" + 
				" FROM t_car" + 
				" WHERE state=0 and on_sale=0" + 
				" ORDER BY price DESC";
		return JdbcTemplate.query(sql, new ResultSetHandler<Car>() {

			@Override
			public Car handleRow(ResultSet rs) throws SQLException {
				
				return new Car(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt(4),rs.getInt(5),rs.getDouble(6),rs.getInt(7),rs.getInt(8));
			}
		});
	}
	/**
	 * 借车更新车辆状态
	 */
	@Override
	public int updateCarState(Connection conn,int carId) throws Exception {
		String sql="UPDATE t_car\r\n" + 
				"SET state=1\r\n" + 
				"WHERE id=?";
		return JdbcTemplate.update(conn, sql, carId);
	}
	/**
	 * 还车更新车辆状态
	 */
	@Override
	public int updateCarStateReturn(Connection conn, int carId) throws Exception {
		String sql="UPDATE t_car\r\n" + 
				"SET state=0\r\n" + 
				"WHERE id=?";
		return JdbcTemplate.update(conn, sql, carId);
	}


	/**
	 * 通过车辆ID查询
	 */
	@Override
	public Car selectCarByCarId(int carId) throws Exception {
		String sql="SELECT *" + 
				" FROM t_car" + 
				" WHERE id=?";
		return JdbcTemplate.singleQuery(sql, new ResultSetHandler<Car>() {

			@Override
			public Car handleRow(ResultSet rs) throws SQLException {
				// TODO Auto-generated method stub
				return new Car(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt(4),rs.getInt(5),rs.getDouble(6),rs.getInt(7),rs.getInt(8));
			}
			
		}, carId);
		
	}
	/**
	 * 用户,通过品查询车辆牌
	 */
	public List<Car> selectCarByBrand(int brand) throws Exception{
		String sql="SELECT *" + 
				" FROM t_car" + 
				" WHERE brand=? and state=0 and on_sale=0";
		return JdbcTemplate.query(sql, new ResultSetHandler<Car>() {

			@Override
			public Car handleRow(ResultSet rs) throws SQLException {
				// TODO Auto-generated method stub
				return new Car(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5), rs.getDouble(6), rs.getInt(7),rs.getInt(8));
			}
		}, brand);
	}
	/**
	  * 用户,通过类查询车辆型
	 */
	@Override
	public List<Car> selectCarByType(int type) throws Exception {
		String sql="SELECT *" + 
				" FROM t_car" + 
				" WHERE type=? and state=0 and on_sale=0";
		return JdbcTemplate.query(sql, new ResultSetHandler<Car>() {

			@Override
			public Car handleRow(ResultSet rs) throws SQLException {
				// TODO Auto-generated method stub
				return new Car(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5), rs.getDouble(6), rs.getInt(7),rs.getInt(8));
			}
		}, type);
	}
	
	/**
	 * 添加车辆
	 */
	@Override
	public int insertCar(String name, String note, int brand, int type, double price, int state,int onSale) {
		String sql="INSERT INTO t_car(name,note,brand,type,price,state,on_sale)\r\n" + 
				"VALUES(?,?,?,?,?,?,?)";
		try {
			return JdbcTemplate.update(sql, name,note,brand,type,price,state,onSale);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	/**
	 * 管理员,修改汽车状态,下架
	 */
	@Override
	public int updateCarOnSale1(int carId) throws Exception {
		String sql="UPDATE t_car" + 
				" SET on_sale=1" + 
				" WHERE id=?";
		return JdbcTemplate.update(sql, carId);
	}
	/**
	 * 管理员,修改汽车状态,上架
	 */
	@Override
	public int updateCarOnSale0(int carId) throws Exception {
		String sql="UPDATE t_car" + 
				" SET on_sale=0" + 
				" WHERE id=?";
		return JdbcTemplate.update(sql, carId);
	}
	/**
	 * 管理员,修改汽车价格
	 */
	@Override
	public int updateCarPrice(int carId,double price) throws Exception {
		String sql="UPDATE t_car" + 
				" SET price=?" + 
				" WHERE id=?";
		return JdbcTemplate.update(sql, price,carId);
	}

}
