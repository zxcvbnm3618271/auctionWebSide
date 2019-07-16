package com.qianfeng.dao.Impl;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;


import com.qianfeng.dao.AuctionDAO;
import com.qianfeng.entity.Auction;
import com.qianfeng.util.JDBCUtil;

public class AuctionDAOImpl implements AuctionDAO {

	@Override
	public List<Auction> AuctionListByPage(BigDecimal pageIndex,
			BigDecimal pageNum) {
		// TODO Auto-generated method stub
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		ResultSet resultSet=null;
		List<Auction> auctions=new ArrayList<Auction>();
		try {
			connection= JDBCUtil.getConnection();
			preparedStatement= connection.prepareStatement("select * from auction limit ?,?");
			preparedStatement.setBigDecimal(1,pageIndex);
			preparedStatement.setBigDecimal(2, pageNum);
			resultSet= preparedStatement.executeQuery();
			while(resultSet.next())
			{
				Auction auction=new Auction();
				auction.setAuctionID(resultSet.getInt("AUCTIONID"));
				auction.setAuctionName(resultSet.getString("AUCTIONNAME"));
				auction.setAuctionStartPrice(resultSet.getDouble("AUCTIONSTARTPRICE"));
				auction.setAuctionUpset(resultSet.getDouble("AUCTIONUPSET"));
				auction.setAuctionStartTime(resultSet.getTimestamp("AUCTIONSTARTTIME"));
				auction.setAuctionEndTime(resultSet.getTimestamp("AUCTIONENDTIME"));
				auction.setAuctionDESC(resultSet.getString("AUCTIONDESC"));
				auction.setAuctionPICPath(resultSet.getString("AUCTIONPICPATH"));
				auction.setCreateTime(new Timestamp(System.currentTimeMillis()));
				auction.setUpdateTime(new Timestamp(System.currentTimeMillis()));
				auctions.add(auction);
			}
		} catch (Exception e) {
			// TODO: handle exception
			
			
		}finally{
			JDBCUtil.close();
		}
		return auctions;
	}

	@Override
	public BigDecimal getAllCount() {
		// TODO Auto-generated method stub
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		ResultSet resultSet=null;
		BigDecimal totalCount=null;
		try {
			connection=JDBCUtil.getConnection();
			preparedStatement=connection.prepareStatement("select count(*) from auction");
			resultSet=preparedStatement.executeQuery();
			while (resultSet.next()) {
				//1指的是获取第一个字段 由于获取总条数只有一个字段所以采用这种方式
				totalCount=resultSet.getBigDecimal(1);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return totalCount;
	}

}
