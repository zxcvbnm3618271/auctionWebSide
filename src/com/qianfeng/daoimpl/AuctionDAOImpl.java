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
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;

import com.qianfeng.dao.AuctionDAO;
import com.qianfeng.entity.Auction;
import com.qianfeng.entity.AuctionUser;
import com.qianfeng.util.JDBCUtil;
import com.sun.corba.se.impl.orb.ParserTable.TestAcceptor1;
import com.sun.org.apache.bcel.internal.generic.NEW;

@Transactional
public class AuctionDAOImpl extends HibernateDaoSupport implements AuctionDAO {

	@Override
	public List<Auction> auctionListByPage(BigDecimal pageIndex,
			BigDecimal pageNum) {
		List<Auction> auctions=new ArrayList<Auction>();
		//SessionFactory sessionFactory=null;
		//Session session=null;
		try {
			
			auctions=getSessionFactory().getCurrentSession().createQuery("from Auction")
					.setFirstResult(pageIndex.intValue())
					.setMaxResults(pageNum.intValue()).list();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return auctions;
		// TODO Auto-generated method stub
	
	}

	@Override
	public BigDecimal getAllcount() {
		// TODO Auto-generated method stub
		BigDecimal totalCount=null;
		
		
		try {
			
			long totalCount2=(Long) getSessionFactory().getCurrentSession().createQuery("select count(*) from Auction")
					.uniqueResult();
			totalCount=new BigDecimal(totalCount2);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return totalCount;
	}

	@Override
	public int auctionAdd(Auction auction) throws Exception {
		// TODO Auto-generated method stub

		
		
		try {
			
		
			getSessionFactory().getCurrentSession().save(auction);
			
			return 1;
		
			
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			
		}
		
	
		return 0;
		
	}

	@Override
	public Auction auctionFindById(int auctionId) {
		// TODO Auto-generated method stub
		
		
		Auction auction=null;
		
		try {
			
			auction=(Auction) getSessionFactory().getCurrentSession().get(Auction.class, auctionId);
			
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	
		return auction;
		
	
	}

	@Override
	public int auctionUpdate(Auction auction) throws Exception {
		// TODO Auto-generated method stub
		
	
	
		try {
			
			
			getSessionFactory().getCurrentSession().update(auction);
			
			return 1;
		
			
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		
		}
		
	
		return 0;
	}

	@Override
	public int auctionDelByID(int auctionID) {
		// TODO Auto-generated method stub
		
		
		try {
			
			
			getSessionFactory().getCurrentSession().delete(new Auction(auctionID));
		
			return 1;
		
			
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			
		}
		
	
		return 0;
	}

	@Override
	public List<Auction> serchEndAuctionList() {
		// TODO Auto-generated method stub
		List<Auction> auctions=new ArrayList<Auction>();
				
		
		try {
			
			auctions= getSessionFactory().getCurrentSession().createQuery("from Auction  where auctionEndTime < ?")
					.setDate(0, new Date()).list();
					
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return auctions;
	}

	@Override
	public List<Auction> serchNotEndAuctionList() {
		// TODO Auto-generated method stub
		List<Auction> auctions=new ArrayList<Auction>();
		
		
		try {
			
			auctions= getSessionFactory().getCurrentSession().createQuery("from Auction  where auctionEndTime > ?")
					.setDate(0, new Date()).list();
					
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return auctions;
	}

	@Override
	public List<Auction> searchAuctionList(String hql,Auction auction) {
		// TODO Auto-generated method stub
	List<Auction> auctions=new ArrayList<Auction>();
		
		
		try {
			
			auctions= getSessionFactory().getCurrentSession().createQuery(hql)
					.setProperties(auction).list();
					
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return auctions;
		
		
	}

}
