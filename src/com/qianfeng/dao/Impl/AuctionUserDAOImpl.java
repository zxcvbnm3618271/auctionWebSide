package com.qianfeng.dao.Impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;

import com.qianfeng.dao.AuctionUserDAO;
import com.qianfeng.entity.AuctionUser;
import com.qianfeng.util.JDBCUtil;

public class AuctionUserDAOImpl implements AuctionUserDAO {

	@Override
	// public AuctionUser auctionLogin(String userName, String passWord) {
	// // TODO Auto-generated method stub
	// AuctionUser auctionUser=new AuctionUser();
	// if ("admin".equals(userName) && "admin".equals(passWord)) {
	// auctionUser.setUserName(userName);
	// auctionUser.setUserPassWord(passWord);
	// //auctionUser.setUserIsAdmin(userIsAdmin);
	// }
	// return auctionUser;
	// }
	public AuctionUser auctionLogin(String userName, String passWord) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		AuctionUser auctionUser = null;
		try {
			connection = JDBCUtil.getConnection();
			preparedStatement = connection
					.prepareStatement("select * from auctionuser where username=? and userpassword=?");
			preparedStatement.setString(1, userName);
			preparedStatement.setString(2, passWord);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				auctionUser = new AuctionUser();
				auctionUser.setUserName(resultSet.getString("USERNAME"));
				auctionUser
						.setUserPassWord(resultSet.getString("USERPASSWORD"));
				auctionUser.setUserIsAdmin(resultSet.getBoolean("USERISADMIN"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JDBCUtil.close();
		return auctionUser;
	}

}
