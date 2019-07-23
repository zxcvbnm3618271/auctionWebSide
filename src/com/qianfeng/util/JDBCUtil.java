package com.qianfeng.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.PreparedStatement;

public class JDBCUtil {

	static{
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection(){
		Connection connection=null;
		try {
			connection = DriverManager.getConnection(
					//解决乱码
					"jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=utf-8", "root", "root");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connection;
		
	}
	
	public static void close(ResultSet resultSet,java.sql.PreparedStatement preparedStatement,Connection connection){
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
			//catch一定不能写处理业务逻辑的代码
			e.printStackTrace();
		}
	}
}
