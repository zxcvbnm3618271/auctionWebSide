package com.qianfeng.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;

;

//工具类的所有方法都是静态的
public class JDBCUtil {
	static PreparedStatement preparedStatement = null;
	static ResultSet resultSet = null;
	static Connection connection = null;
	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void query() throws SQLException {
		preparedStatement = connection
				.prepareStatement("select * from auctionuser");
		resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			String usernameString = resultSet.getString("USERNAME");
			System.out.println(usernameString);
		}
	}

	public static Connection getConnection() throws SQLException {

		connection = DriverManager.getConnection(
				"jdbc:mysql://39.108.55.167:3306/test?useSSL=false", "root",
				"2FF39aa8820f");
		return connection;
	}

	public static void close() {
		try {
			if (resultSet != null) {
				resultSet.close();
			}
			if (preparedStatement != null) {
				preparedStatement.close();
			}
			if (connection != null) {
				connection.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
