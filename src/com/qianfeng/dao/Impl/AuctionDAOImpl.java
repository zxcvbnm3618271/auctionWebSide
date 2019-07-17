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
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<Auction> auctions = new ArrayList<Auction>();
		try {
			connection = JDBCUtil.getConnection();
			preparedStatement = connection
					.prepareStatement("select * from auction limit ?,?");
			preparedStatement.setBigDecimal(1, pageIndex);
			preparedStatement.setBigDecimal(2, pageNum);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Auction auction = new Auction();
				auction.setAuctionID(resultSet.getInt("AUCTIONID"));
				auction.setAuctionName(resultSet.getString("AUCTIONNAME"));
				auction.setAuctionStartPrice(resultSet
						.getDouble("AUCTIONSTARTPRICE"));
				auction.setAuctionUpset(resultSet.getDouble("AUCTIONUPSET"));
				auction.setAuctionStartTime(resultSet
						.getTimestamp("AUCTIONSTARTTIME"));
				auction.setAuctionEndTime(resultSet
						.getTimestamp("AUCTIONENDTIME"));
				auction.setAuctionDESC(resultSet.getString("AUCTIONDESC"));
				auction.setAuctionPICPath(resultSet.getString("AUCTIONPICPATH"));
				auction.setCreateTime(new Timestamp(System.currentTimeMillis()));
				auction.setUpdateTime(new Timestamp(System.currentTimeMillis()));
				auctions.add(auction);
			}
		} catch (Exception e) {
			// TODO: handle exception

		} finally {
			JDBCUtil.close();
		}
		return auctions;
	}

	@Override
	public BigDecimal getAllCount() {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		BigDecimal totalCount = null;
		try {
			connection = JDBCUtil.getConnection();
			preparedStatement = connection
					.prepareStatement("select count(*) from auction");
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				// 1指的是获取第一个字段 由于获取总条数只有一个字段所以采用这种方式
				totalCount = resultSet.getBigDecimal(1);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return totalCount;
	}

	@Override
	public int auctionAdd(Auction auction) throws Exception {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		int resultCount = 0;
		connection = JDBCUtil.getConnection();
		// id不需要插入 这个由数据自增长来实现
		preparedStatement = connection
				.prepareStatement("insert into auction(AUCTIONNAME,AUCTIONSTARTPRICE,AUCTIONUPSET,AUCTIONSTARTTIME,AUCTIONENDTIME,AUCTIONDESC,AUCTIONPICPATH,CREATETIME,UPDATETIME) values(?,?,?,?,?,?,?,?,?)");
		preparedStatement.setString(1, auction.getAuctionName());
		preparedStatement.setDouble(2, auction.getAuctionStartPrice());
		preparedStatement.setDouble(3, auction.getAuctionUpset());
		preparedStatement.setTimestamp(4, auction.getAuctionStartTime());
		preparedStatement.setTimestamp(5, auction.getAuctionEndTime());
		preparedStatement.setString(6, auction.getAuctionDESC());
		preparedStatement.setString(7, auction.getAuctionPICPath());
		preparedStatement.setTimestamp(8, auction.getCreateTime());
		preparedStatement.setTimestamp(9, auction.getUpdateTime());
		resultCount = preparedStatement.executeUpdate();
		System.out.println(resultCount);
		preparedStatement.close();
		JDBCUtil.close();

		return resultCount;
	}

	@SuppressWarnings("finally")
	@Override
	public Auction auctionFindById(int auctionid) {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Auction auction = new Auction();
		try {
			connection = JDBCUtil.getConnection();
			preparedStatement = connection
					.prepareStatement("select * from auction where auctionid=?");
			preparedStatement.setInt(1, auctionid);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {

				auction.setAuctionID(resultSet.getInt("AUCTIONID"));
				auction.setAuctionName(resultSet.getString("AUCTIONNAME"));
				auction.setAuctionStartPrice(resultSet
						.getDouble("AUCTIONSTARTPRICE"));
				auction.setAuctionUpset(resultSet.getDouble("AUCTIONUPSET"));
				auction.setAuctionStartTime(resultSet
						.getTimestamp("AUCTIONSTARTTIME"));
				auction.setAuctionEndTime(resultSet
						.getTimestamp("AUCTIONENDTIME"));
				auction.setAuctionDESC(resultSet.getString("AUCTIONDESC"));
				auction.setAuctionPICPath(resultSet.getString("AUCTIONPICPATH"));
				auction.setCreateTime(resultSet.getTimestamp("CREATETIME"));
				auction.setUpdateTime(resultSet.getTimestamp("UPDATETIME"));

			}
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			return auction;
		}

	}

	@Override
	public int auctionUpdate(Auction auction) throws Exception {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		int resultCount = 0;
		connection = JDBCUtil.getConnection();
		// id不需要插入 这个由数据自增长来实现
		preparedStatement = connection
				.prepareStatement(" update auction set AUCTIONNAME=?,AUCTIONSTARTPRICE=?,AUCTIONUPSET=?,AUCTIONSTARTTIME=?,AUCTIONENDTIME=?,AUCTIONDESC=?,AUCTIONPICPATH=?,CREATETIME=?,UPDATETIME=? where AUCTIONID=?");
		preparedStatement.setString(1, auction.getAuctionName());
		preparedStatement.setDouble(2, auction.getAuctionStartPrice());
		preparedStatement.setDouble(3, auction.getAuctionUpset());
		preparedStatement.setTimestamp(4, auction.getAuctionStartTime());
		preparedStatement.setTimestamp(5, auction.getAuctionEndTime());
		preparedStatement.setString(6, auction.getAuctionDESC());
		preparedStatement.setString(7, auction.getAuctionPICPath());
		preparedStatement.setTimestamp(8, auction.getCreateTime());
		preparedStatement.setTimestamp(9, auction.getUpdateTime());
		preparedStatement.setInt(10, auction.getAuctionID());
		resultCount = preparedStatement.executeUpdate();
		System.out.println(resultCount);
		preparedStatement.close();
		JDBCUtil.close();

		return resultCount;

	}

}
