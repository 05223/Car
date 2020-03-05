package com.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.dao.IBrandDao;
import com.entity.Brand;
import com.util.JdbcTemplate;
import com.util.ResultSetHandler;

public class BrandDaoImpl implements IBrandDao {

	@Override
	public Brand selectBrandById(int id) throws Exception {
		
		String sql="SELECT *" + 
				" FROM t_brand" + 
				" WHERE id=?";
		
		return JdbcTemplate.singleQuery(sql, new ResultSetHandler<Brand>() {

			@Override
			public Brand handleRow(ResultSet rs) throws SQLException {
				// TODO Auto-generated method stub
				return new Brand(rs.getInt(1), rs.getString(2));
			}
		}, id);
	}

	@Override
	public Brand selectBrandByName(String name) throws Exception {
		String sql="SELECT *" + 
				" FROM t_brand" + 
				" WHERE brand_name=?";
		
		return JdbcTemplate.singleQuery(sql, new ResultSetHandler<Brand>() {

			@Override
			public Brand handleRow(ResultSet rs) throws SQLException {
				// TODO Auto-generated method stub
				return new Brand(rs.getInt(1), rs.getString(2));
			}
		}, name);
	}

	@Override
	public List<Brand> selectAllBrand() throws Exception {
		String sql="SELECT *" + 
				" FROM t_brand";
		
		return JdbcTemplate.query(sql, new ResultSetHandler<Brand>() {

			@Override
			public Brand handleRow(ResultSet rs) throws SQLException {
				// TODO Auto-generated method stub
				return new Brand(rs.getInt(1), rs.getString(2));
			}
		});
	}

}
