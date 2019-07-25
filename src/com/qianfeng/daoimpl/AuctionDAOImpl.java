package com.qianfeng.daoimpl;

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
import com.qianfeng.entity.AuctionUser;
import com.qianfeng.util.JDBCUtil;
import com.sun.corba.se.impl.orb.ParserTable.TestAcceptor1;
import com.sun.org.apache.bcel.internal.generic.NEW;

public class AuctionDAOImpl implements AuctionDAO {

	@Override
	public List<Auction> auctionListByPage(BigDecimal pageIndex,
			BigDecimal pageNum) {
		List<Auction> auctions=new ArrayList<Auction>();
		SessionFactory sessionFactory=null;
		Session session=null;
		try {
			sessionFactory=new Configuration().configure()
					.buildSessionFactory();
			session=sessionFactory.openSession();
			auctions=session
					.createQuery("from Auction")
					.setFirstResult(pageIndex.intValue())
					.setMaxResults(pageNum.intValue()).list();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if (null !=session) {
				session.close();
			}
		}
		return auctions;
		// TODO Auto-generated method stub
	
	}

	@Override
	public BigDecimal getAllcount() {
		// TODO Auto-generated method stub
		BigDecimal totalCount=null;
		
		SessionFactory sessionFactory=null;
		Session session=null;
		try {
			sessionFactory=new Configuration().configure()
					.buildSessionFactory();
			session=sessionFactory.openSession();
			long totalCount2=(Long) session
					.createQuery("select count(*) from Auction")
					.uniqueResult();
			totalCount=new BigDecimal(totalCount2);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if (null !=session) {
				session.close();
			}
		}
		return totalCount;
	}

	@Override
	public int auctionAdd(Auction auction) throws Exception {
		// TODO Auto-generated method stub

		SessionFactory sessionFactory=null;
		Session session=null;
		Transaction transaction=null;
		try {
			sessionFactory=new Configuration().configure().buildSessionFactory();
			session=sessionFactory.openSession();
			transaction=session.beginTransaction();
			session.save(auction);
			transaction.commit();
			return 1;
		
			
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			transaction.rollback();
		}finally{
			if (null!=session) {
				session.close();
			}
		}
		
	
		return 0;
		
	}

	@Override
	public Auction auctionFindById(int auctionId) {
		// TODO Auto-generated method stub
		
		
		Auction auction=null;
		SessionFactory sessionFactory=null;
		Session session=null;
		try {
			sessionFactory=new Configuration().configure().buildSessionFactory();
			session=sessionFactory.openSession();
			auction=(Auction) session.get(Auction.class, auctionId);
			
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
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
		
			
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			transaction.rollback();
		}finally{
			if (null!=session) {
				session.close();
			}
		}
		
	
		return 0;
	}

	@Override
	public int auctionDelByID(int auctionID) {
		// TODO Auto-generated method stub
		SessionFactory sessionFactory=null;
		Session session=null;
		Transaction transaction=null;
		try {
			sessionFactory=new Configuration().configure().buildSessionFactory();
			session=sessionFactory.openSession();
			transaction=session.beginTransaction();
			session.delete(new Auction(auctionID));
			transaction.commit();
			return 1;
		
			
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			transaction.rollback();
		}finally{
			if (null!=session) {
				session.close();
			}
		}
		
	
		return 0;
	}

	@Override
	public List<Auction> serchEndAuctionList() {
		// TODO Auto-generated method stub
		List<Auction> auctions=new ArrayList<Auction>();
				
		SessionFactory sessionFactory=null;
		Session session=null;
		try {
			sessionFactory=new Configuration().configure()
					.buildSessionFactory();
			session=sessionFactory.openSession();
			auctions= session
					.createQuery("from Auction  where auctionEndTime < ?")
					.setDate(0, new Date()).list();
					
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if (null !=session) {
				session.close();
			}
		}
		return auctions;
	}

	@Override
	public List<Auction> serchNotEndAuctionList() {
		// TODO Auto-generated method stub
		List<Auction> auctions=new ArrayList<Auction>();
		
		SessionFactory sessionFactory=null;
		Session session=null;
		try {
			sessionFactory=new Configuration().configure()
					.buildSessionFactory();
			session=sessionFactory.openSession();
			auctions= session
					.createQuery("from Auction  where auctionEndTime > ?")
					.setDate(0, new Date()).list();
					
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if (null !=session) {
				session.close();
			}
		}
		return auctions;
	}

	@Override
	public List<Auction> searchAuctionList(String hql,Auction auction) {
		// TODO Auto-generated method stub
	List<Auction> auctions=new ArrayList<Auction>();
		
		SessionFactory sessionFactory=null;
		Session session=null;
		try {
			sessionFactory=new Configuration().configure()
					.buildSessionFactory();
			session=sessionFactory.openSession();
			auctions= session
					.createQuery(hql)
					.setProperties(auction).list();
					
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if (null !=session) {
				session.close();
			}
		}
		return auctions;
		
		
	}

}
