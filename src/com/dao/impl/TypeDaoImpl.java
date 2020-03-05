package com.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.dao.ITypeDao;
import com.entity.Type;
import com.util.JdbcTemplate;
import com.util.ResultSetHandler;

public class TypeDaoImpl implements ITypeDao{

	@Override
	public Type selecTypeById(int id) throws Exception {
		String sql="SELECT *" + 
				" FROM t_type" + 
				" WHERE id=?";
		return JdbcTemplate.singleQuery(sql, new ResultSetHandler<Type>() {

			@Override
			public Type handleRow(ResultSet rs) throws SQLException {
				// TODO Auto-generated method stub
				return new Type(rs.getInt(1), rs.getString(2));
			}
		}, id);
	}

	@Override
	public Type selecTypeByName(String name) throws Exception {
		String sql="SELECT *" + 
				" FROM t_type" + 
				" WHERE type_name=?";
		return JdbcTemplate.singleQuery(sql, new ResultSetHandler<Type>() {

			@Override
			public Type handleRow(ResultSet rs) throws SQLException {
				// TODO Auto-generated method stub
				return new Type(rs.getInt(1), rs.getString(2));
			}
		}, name);
	}

	@Override
	public List<Type> selecAllType() throws Exception {
		String sql="SELECT *" + 
				" FROM t_type";
		return JdbcTemplate.query(sql, new ResultSetHandler<Type>() {

			@Override
			public Type handleRow(ResultSet rs) throws SQLException {
				// TODO Auto-generated method stub
				return new Type(rs.getInt(1), rs.getString(2));
			}
		});
	}

}
