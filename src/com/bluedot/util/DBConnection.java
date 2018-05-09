package com.bluedot.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnection {
	 
	
	public static Connection getConn() {
		JdbcInfo jdbcInfo=DbConfig.getInstance().getJdbcInfo();
		Connection conn;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn=DriverManager.getConnection(jdbcInfo.getUrl(), jdbcInfo.getUsername(), jdbcInfo.getPassword());
		 if(conn!=null)
		 {
			 return conn;
		 }
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
		
	}
	public static void close(Statement stmt,Connection conn,ResultSet rs)
	{
		
			try {
				if(rs!=null)
				{
				rs.close();
				}
				if(stmt!=null)
				{
					stmt.close();
				}
				if(conn!=null)
				{
		          conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		
	}

}
