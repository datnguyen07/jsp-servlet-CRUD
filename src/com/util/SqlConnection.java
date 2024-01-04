package com.util;
import java.sql.*;

public class SqlConnection {
	public static Connection open() {
		Connection connection = null;
		try {

			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/address";
			String user = "root";
			String password = "1234567890";
			connection = DriverManager.getConnection(url, user, password);
			System.out.println("connected!");
			return connection; 
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	public static void close(Connection conn) {
		if(conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}



}
