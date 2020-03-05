package com.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
/**
 * JDBC模板类,提供通用的增删改查方法
 * @author Administrator
 */
public class JdbcTemplate {
	/**
	 * 增删改方法
	 * @param sql 要执行的sql语句
	 * @param params 参数值,要和sql对应
	 * @return
	 */
	public static int update(String sql,Object... params) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = ConnectionFactory.getConnection();	
			pstmt = conn.prepareStatement(sql);
			//设置占位符的值
			if(params!=null) {
				for(int i=0; i<params.length; i++) {
					pstmt.setObject(i+1, params[i]);
				}
			}
			return pstmt.executeUpdate();
		}finally {
			ConnectionFactory.close(pstmt, conn);
		}
	}
	
	/**
	 * 增删改方法
	 * @param conn 连接对象
	 * @param sql 要执行的sql语句
	 * @param params 参数值,要和sql对应
	 * @return
	 */
	public static int update(Connection conn,String sql,Object... params) throws Exception{
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			//设置占位符的值
			if(params!=null) {
				for(int i=0; i<params.length; i++) {
					pstmt.setObject(i+1, params[i]);
				}
			}
			return pstmt.executeUpdate();
		}finally {
			ConnectionFactory.close(pstmt);
		}
	}
	
	
	/**
	 * 单行查询
	 * @param sql 要执行的SQL语句
	 * @param handler 结果集处理对象
	 * @param params 参数的值
	 * @return 处理后的对象
	 * @throws Exception
	 */
	public static <T> T singleQuery(String sql,ResultSetHandler<T> handler,Object...params) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt =null;
		ResultSet rs = null;
		try {
			conn = ConnectionFactory.getConnection();
			pstmt = conn.prepareStatement(sql);
			if(params!=null) {
				for(int i=0; i<params.length; i++) {
					pstmt.setObject(i+1, params[i]);
				}
			}
			rs = pstmt.executeQuery();
			T t = null;
			if(handler!=null) {
				if(rs.next()) {
					//把结果集中的一行转换成一个T类型的对象
					t = handler.handleRow(rs);
				}
			}
			return t;
		}finally {
			ConnectionFactory.close(rs, pstmt, conn);
		}
	}
	
	/**
	 * 单行查询
	 * @param conn 连接对象
	 * @param sql 要执行的SQL语句
	 * @param handler 结果集处理对象
	 * @param params 参数的值
	 * @return 处理后的对象
	 * @throws Exception
	 */
	public static <T> T singleQuery(Connection conn,String sql,ResultSetHandler<T> handler,Object...params) throws Exception{
		PreparedStatement pstmt =null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			if(params!=null) {
				for(int i=0; i<params.length; i++) {
					pstmt.setObject(i+1, params[i]);
				}
			}
			rs = pstmt.executeQuery();
			T t = null;
			if(handler!=null) {
				if(rs.next()) {
					//把结果集中的一行转换成一个T类型的对象
					t = handler.handleRow(rs);
				}
			}
			return t;
		}finally {
			ConnectionFactory.close(rs, pstmt);
		}
	}
	
	
	/**
	 * 多行查询
	 * @param sql 要执行的SQL语句
	 * @param handler 结果集处理对象
	 * @param params 参数的值
	 * @return 处理后的对象集合
	 * @throws Exception
	 */
	public static <T> List<T> query(String sql,ResultSetHandler<T> handler,Object...params) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt =null;
		ResultSet rs = null;
		ArrayList<T> list = new ArrayList<T>();
		try {
			conn = ConnectionFactory.getConnection();
			pstmt = conn.prepareStatement(sql);
			if(params!=null) {
				for(int i=0; i<params.length; i++) {
					pstmt.setObject(i+1, params[i]);
				}
			}
			rs = pstmt.executeQuery();
			if(handler!=null) {
				while(rs.next()) {
					//把结果集中的一行转换成一个T类型的对象
					list.add(handler.handleRow(rs));
				}
			}
			return list;
		}finally {
			ConnectionFactory.close(rs, pstmt, conn);
		}
	}
	
	/**
	 * 多行查询
	 * @param conn 连接对象
	 * @param sql 要执行的SQL语句
	 * @param handler 结果集处理对象
	 * @param params 参数的值
	 * @return 处理后的对象集合
	 * @throws Exception
	 */
	public static <T> List<T> query(Connection conn,String sql,ResultSetHandler<T> handler,Object...params) throws Exception{
		PreparedStatement pstmt =null;
		ResultSet rs = null;
		ArrayList<T> list = new ArrayList<T>();
		try {
			pstmt = conn.prepareStatement(sql);
			if(params!=null) {
				for(int i=0; i<params.length; i++) {
					pstmt.setObject(i+1, params[i]);
				}
			}
			rs = pstmt.executeQuery();
			if(handler!=null) {
				while(rs.next()) {
					//把结果集中的一行转换成一个T类型的对象
					list.add(handler.handleRow(rs));
				}
			}
			return list;
		}finally {
			ConnectionFactory.close(rs, pstmt);
		}
	}
	
	
}



