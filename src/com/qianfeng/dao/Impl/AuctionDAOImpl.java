package com.qianfeng.dao.Impl;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
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
		List<Auction> auctions=new ArrayList<Auction>();
		SessionFactory sessionFactory=null;
		Session session=null;
		try {
			sessionFactory=new Configuration().configure().buildSessionFactory();
			session=sessionFactory.openSession();
			auctions=session.createQuery("from Auction").setFirstResult(pageIndex.intValue()).setMaxResults(pageNum.intValue()).list();
			
		} catch (Exception e) {
			// TODO: handle exception
		}finally{
			if (session!=null) {
				session.close();
			}
		}
		return auctions;
	}

	@Override
	public BigDecimal getAllCount() {
		
		ResultSet resultSet = null;
		BigDecimal totalCount = null;
		SessionFactory sessionFactory=null;
		Session session=null;
		
		try {
			sessionFactory=new Configuration().configure().buildSessionFactory();
			session=sessionFactory.openSession();
			long totalCount2=(Long)session.createQuery("select count(*) from Auction").uniqueResult();
			totalCount=new BigDecimal(totalCount2);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if (session!=null) {
				session.close();
			}
			
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
		List<Auction> auctions=new ArrayList<Auction>();
		SessionFactory sessionFactory=null;
		Session session=null;
		sessionFactory=new Configuration().configure().buildSessionFactory();
		session=sessionFactory.openSession();
		try {
			auctions=session.createQuery("from Auction where auctionEndTime < ?").setDate(0, new Date()).list();
			
		} catch (Exception e) {
			// TODO: handle exception
		}finally{
			if (session!=null) {
				session.close();
			}
		}
		return auctions;
	}

	@Override
	public List<Auction> searchNotEndAuctionList() {
		List<Auction> auctions=new ArrayList<Auction>();
		SessionFactory sessionFactory=null;
		Session session=null;
		sessionFactory=new Configuration().configure().buildSessionFactory();
		session=sessionFactory.openSession();
		try {
			auctions=session.createQuery("from Auction where auctionEndTime > ?").setDate(0, new Date()).list();
			
		} catch (Exception e) {
			// TODO: handle exception
		}finally{
			if (session!=null) {
				session.close();
			}
		}
		return auctions;
	}

	@Override
	public List<Auction> searchAuctionList(String hql,Auction auction){
		List<Auction> auctions=new ArrayList<Auction>();
		SessionFactory sessionFactory=null;
		Session session=null;
		sessionFactory=new Configuration().configure().buildSessionFactory();
		session=sessionFactory.openSession();
		try {
			auctions=session.createQuery(hql).setProperties(auction).list();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			if (session!=null) {
				session.close();
			}
		}
		return auctions;
	}

}
