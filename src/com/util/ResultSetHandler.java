package com.util;

import java.sql.ResultSet;
import java.sql.SQLException;
/**
 * 结果集处理接口
 * @author Administrator
 * @param <T> 要转换的对象的类型
 */
public interface ResultSetHandler<T> {
	/**
	 * 处理结果集的一行,转换成一个T类型对象
	 * @param rs 结果集
	 * @return 处理后的对象
	 * @throws SQLException
	 */
	public T handleRow(ResultSet rs) throws SQLException;
}
