package com.qianfeng.dao.Impl;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.qianfeng.dao.AuctionDAO;
import com.qianfeng.entity.Auction;
import com.qianfeng.util.JDBCUtil;
import com.qianfeng.util.StringUtil;
import com.sun.org.apache.bcel.internal.generic.NEW;

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
		SessionFactory sessionFactory=null;
		Session session=null;
		Transaction transaction=null;
		try {
			sessionFactory=new Configuration().configure().buildSessionFactory();
			session=sessionFactory.openSession();
			transaction=session.beginTransaction();
			session.save(auction);
			transaction.commit();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			transaction.rollback();
			return 1;
		}finally{
			if (null != session) {
				session.close();
			}
		}
		
		return 0;
	}

	@SuppressWarnings("finally")
	@Override
	public Auction auctionFindById(int auctionid) {
		Auction auction=null;
		SessionFactory sessionFactory=null;
		Session session=null;
		try {
			sessionFactory=new org.hibernate.cfg.Configuration().configure().buildSessionFactory();
			session=sessionFactory.openSession();
			auction=(Auction)session.get(Auction.class, auctionid);
			
		} catch (Exception e) {
			// TODO: handle exception
		}finally{
			if (null!=session) {
				session.close();
			}
		}
		return auction;
	}

	@Override
	public int auctionUpdate(Auction auction) throws Exception {
		// TODO Auto-generated method stub
		SessionFactory sessionFactory=null;
		Session session=null;
		Transaction transaction=null;
		try {
			sessionFactory=new Configuration().configure().buildSessionFactory();
			session=sessionFactory.openSession();
			transaction=session.beginTransaction();
			session.update(auction);
			transaction.commit();
			return 1;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			transaction.rollback();
		}finally{
			if (null != session) {
				session.close();
			}
		}
		
		return 0;
	}

	@Override
	public int auctionDelByID(int auctionid) {
		SessionFactory sessionFactory=null;
		Session session=null;
		Transaction transaction=null;
		try {
			sessionFactory=new Configuration().configure().buildSessionFactory();
			session=sessionFactory.openSession();
			transaction=session.beginTransaction();
			session.delete(new Auction(auctionid));
			transaction.commit();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			transaction.rollback();
			return 1;
		}finally{
			if (null != session) {
				session.close();
			}
		}
		
		return 0;
	}

	@Override
	public List<Auction> searchEndAuctionList() {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<Auction> auctions = new ArrayList<Auction>();
		try {

			connection = JDBCUtil.getConnection();
			preparedStatement = connection
					.prepareStatement("select * from auction where auctionendtime < ?");
			preparedStatement.setTimestamp(1,
					new Timestamp(System.currentTimeMillis()));
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
				auction.setCreateTime(resultSet.getTimestamp("CREATETIME"));
				auction.setUpdateTime(resultSet.getTimestamp("UPDATETIME"));
				auctions.add(auction);
			}

		} catch (Exception e) {
			// TODO: handle exception
			JDBCUtil.close();
		}
		return auctions;
	}

	@Override
	public List<Auction> searchNotEndAuctionList() {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<Auction> auctions = new ArrayList<Auction>();
		try {

			connection = JDBCUtil.getConnection();
			preparedStatement = connection
					.prepareStatement("select * from auction where auctionendtime > ?");
			preparedStatement.setTimestamp(1,
					new Timestamp(System.currentTimeMillis()));
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
				auction.setCreateTime(resultSet.getTimestamp("CREATETIME"));
				auction.setUpdateTime(resultSet.getTimestamp("UPDATETIME"));
				auctions.add(auction);
			}

		} catch (Exception e) {
			// TODO: handle exception
			JDBCUtil.close();
		}
		return auctions;
	}

	@Override
	public List<Auction> searchAuctionList(String sql){
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<Auction> auctionList = new ArrayList<Auction>();
		try {
			connection = JDBCUtil.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			Auction auction = null;
			while(resultSet.next()) {
				// set的拓展性好，虽然构造器时效高，但没有其他任何好处
				auction = new Auction();
				auction.setAuctionID(resultSet.getInt("AUCTIONID"));
				auction.setAuctionName(resultSet.getString("AUCTIONNAME"));
				auction.setAuctionStartPrice(resultSet
						.getDouble("AUCTIONSTARTPRICE"));
				auction.setAuctionUpset(resultSet.getDouble("AUCTIONUPSET"));
				auction.setAuctionStartTime(resultSet
						.getTimestamp("auctionStartTime"));
				auction.setAuctionEndTime(resultSet
						.getTimestamp("auctionEndTime"));
				auction.setAuctionDESC(resultSet.getString("AUCTIONDESC"));
				auction.setAuctionPICPath(resultSet.getString("AUCTIONPICPATH"));
				auction.setCreateTime(resultSet.getTimestamp("CREATETIME"));
				auction.setUpdateTime(resultSet.getTimestamp("UPDATETIME"));
				
				auctionList.add(auction);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			JDBCUtil.close();
		}
		return auctionList;
	}

}
