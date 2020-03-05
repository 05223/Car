package com.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.dao.IUserDao;
import com.entity.User;
import com.util.JdbcTemplate;
import com.util.ResultSetHandler;

public class UserDaoImpl implements IUserDao {
	/**
	 * 用户登录验证
	 */
	@Override
	public User selectUserByNameAndPassword(String name, String password) throws Exception {
		String sql = "SELECT *" + 
				" FROM t_user" + 
				" WHERE user_name=? AND PASSWORD=? and status=0";
		return JdbcTemplate.singleQuery(sql, new ResultSetHandler<User>() {

			@Override
			public User handleRow(ResultSet rs) throws SQLException {
				if (rs!=null) {
					return new User(rs.getInt(1), rs.getString(2), rs.getString(3));
				}
				return null;
			}
			
		}, name,password);
	}
	/**
	 * 注册用户
	 */
	@Override
	public int insertUser(String name, String password) throws Exception {
		String sql = "INSERT INTO t_user(user_name,PASSWORD,status)" + 
				" VALUES(?,?,0)";
		return JdbcTemplate.update(sql, name,password);
	}

	@Override
	public User selecUserById(int id) throws Exception {
		String sql = "SELECT *\r\n" + 
				"FROM t_user\r\n" + 
				"WHERE id=?";
		return JdbcTemplate.singleQuery(sql, new ResultSetHandler<User>() {

			@Override
			public User handleRow(ResultSet rs) throws SQLException {
				// TODO Auto-generated method stub
				return new User(rs.getInt(1), rs.getString(2), rs.getString(3));
			}
		}, id);
	}
	/**
	 * 管理员登录验证
	 */
	@Override
	public User selectAdminByNameAndPassword(String name, String password) throws Exception {
		String sql="SELECT * FROM t_user" + 
				" WHERE user_name=? AND password=? and status=1";
		return JdbcTemplate.singleQuery(sql, new ResultSetHandler<User>() {

			@Override
			public User handleRow(ResultSet rs) throws SQLException {
				// TODO Auto-generated method stub
				return new User(rs.getInt(1), rs.getString(2), rs.getString(3));
			}
		}, name,password);
	}
}
