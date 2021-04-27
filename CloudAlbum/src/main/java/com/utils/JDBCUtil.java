package com.utils;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author x1yyy
 */
public class JDBCUtil {

	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	private static Connection conn;
	private static PreparedStatement s;

	private static void connection ()
	{
		try {
			conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/cloudalbum?serverTimezone=Asia/Shanghai", "cloudalbum", "aL6wtePrJFdnxX7j");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static <T> List<T> executeQry(Class<T> t,String sql,Object... params) {
		connection();
		List<T> arr = new ArrayList();
		try {
			s = conn.prepareStatement(sql);
			
			if (params != null) {
				for (int i=0; i<params.length; i++) {
					s.setObject(i+1, params[i]);
				}
			}
			ResultSet res = s.executeQuery();
			while ( res.next() )
			{
				T obj = t.newInstance();
				Field[] fs = t.getDeclaredFields();
				for (Field f:fs)
				{
					Object val = res.getObject(f.getName());
					f.setAccessible(true);
					f.set(obj, val);
				}
				arr.add(obj);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			close();
		}
		
		return arr;
	}
	
	public static <T> T executeQryOne(Class<T> t,String sql,Object... params) {
		List<T> arr = executeQry(t, sql, params);
		return arr.size()>0?arr.get(0):null;
	}

	public static Object executeQryOneFiled(String sql,String filed,Object... params) {
		connection();
		Object result = null;

		try {
			s = conn.prepareStatement(sql);

			if (params != null) {
				for (int i=0; i<params.length; i++) {
					s.setObject(i+1, params[i]);
				}
			}

			ResultSet resultSet = s.executeQuery();


			while ( resultSet.next() ) {
				result = resultSet.getObject(filed);
			}

		} catch (SQLException throwables) {
			throwables.printStackTrace();
		} finally {
			close();
		}

		return result;
	}

	public static int executeUpdate(String sql,Object... params) {
		
		connection();
		
		try {
			s = conn.prepareStatement(sql);
			
			if (params != null)
			{
				for (int i=0; i<params.length; i++)
				{
					s.setObject(i+1, params[i]);
				}
			}
			
			return s.executeUpdate();
			
			
		} catch (SQLException e) {
			return 0;
		}
		finally {
			close();
		}
	}

	public static List<String> executeQryOneFiledArray(String sql, String filed, Object... params) {
		connection();

		List<String> list = new ArrayList<>();

		try {
			s = conn.prepareStatement(sql);

			if (params != null) {
				for (int i=0; i<params.length; i++) {
					s.setObject(i+1, params[i]);
				}
			}

			ResultSet resultSet = s.executeQuery();

			while ( resultSet.next() ) {
				list.add((String) resultSet.getObject(filed));
			}

		} catch (SQLException throwables) {
			throwables.printStackTrace();
		} finally {
			close();
		}

		return list;
	}

	private static void close() {
		if (s != null)
		{
			try {
				s.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (conn != null)
		{
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
}
