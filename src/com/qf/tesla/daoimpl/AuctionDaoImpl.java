package com.qf.tesla.daoimpl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.TransactionManagement;
import javax.persistence.Entity;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;

import com.mysql.fabric.xmlrpc.base.Data;
import com.qf.tesla.dao.AuctionDao;
import com.qf.tesla.entity.Auction;
import com.sun.jmx.snmp.Timestamp;

@Transactional
public class AuctionDaoImpl extends HibernateDaoSupport implements AuctionDao {

	@Override
	public List<Auction> auctonListByPage(BigDecimal pageIndex,
			BigDecimal pageNum) {
		// TODO Auto-generated method stub
		List<Auction> auctionList = new ArrayList<Auction>();
		try {
			auctionList = getSessionFactory().getCurrentSession()
					.createQuery("from Auction")
					.setFirstResult(pageIndex.intValue())
					.setMaxResults(pageNum.intValue()).list();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		return auctionList;
	}

	@Override
	public BigDecimal getAllcount() {
		// TODO Auto-generated method stub
		long total = 0;

		try {
			total = (Long) getSessionFactory().getCurrentSession()
					.createQuery("select count(*) from Auction").uniqueResult();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		return new BigDecimal(total);
	}

	@Override
	public int auctionAdd(Auction auction) throws Exception {
		// TODO Auto-generated method stub

		try {
			getSessionFactory().getCurrentSession().save(auction);
			return 1;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public Auction auctionFindById(int auctionId) {
		// TODO Auto-generated method stub
		Auction auction = null;
		try {
			auction = (Auction) getSessionFactory().getCurrentSession().get(
					Auction.class, auctionId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return auction;

	}

	@Override
	public int auctionUpdate(Auction auction) throws Exception {
		// TODO Auto-generated method stub
		try {
			getSessionFactory().getCurrentSession().update(auction);
			return 1;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;

	}

	@Override
	public int deleteById(int auctionId) {
		// TODO Auto-generated method stub
		try {
			getSessionFactory().getCurrentSession().delete(
					new Auction(auctionId));
			return 1;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public List<Auction> searchNotEndAuctionList() {
		// TODO Auto-generated method stub
		List<Auction> auctions = null;
		try {
			auctions = getSessionFactory().getCurrentSession()
					.createQuery("from Auction where auctionEndTime >?")
					.setTimestamp(0, new Date()).list();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		return auctions;
	}

	@Override
	public List<Auction> searchEndAuctionList() {
		// TODO Auto-generated method stub
		//
		List<Auction> auctions = null;
		try {
			auctions = getSessionFactory().getCurrentSession()
					.createQuery("from Auction where auctionEndTime <?")
					.setTimestamp(0, new Date()).list();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		return auctions;
	}

	@Override
	public List<Auction> searchAuctionList(String hql, Auction auction) {
		// TODO Auto-generated method stub

		List<Auction> auctions = null;

		try {
			auctions = getSessionFactory().getCurrentSession().createQuery(hql)
					.setProperties(auction).list();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		return auctions;

		// return null;
	}

}
