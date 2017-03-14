package com.Library.jdbc.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcConnectionFactory {
private static Connection connection = null;
	
	static {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "manager");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("class notfound");
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static Connection getDBConnection()
	{
		if (connection != null) {
			return connection;
		}
		return null;
	}
	
	public static void main(String[] args) {
		JdbcConnectionFactory.getDBConnection();
	}
}
