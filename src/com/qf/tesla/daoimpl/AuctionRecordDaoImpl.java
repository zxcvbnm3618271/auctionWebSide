package com.qf.tesla.daoimpl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.qf.tesla.dao.AuctionRecordDao;
import com.qf.tesla.entity.AuctionRecord;
import com.qf.tesla.entity.AuctionUser;
import com.qf.tesla.util.JDBCUtil;

import javax.ejb.TransactionManagement;
import javax.persistence.Entity;
import javax.persistence.criteria.From;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.hql.classic.WhereParser;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class AuctionRecordDaoImpl extends HibernateDaoSupport implements
		AuctionRecordDao {

	@Override
	public List<AuctionRecord> findAuctionRecordByAuctionId(int auctionId) {
		// TODO Auto-generated method stub
		List<AuctionRecord> auctionRecords = new ArrayList<AuctionRecord>();
		try {
			auctionRecords = getSessionFactory()
					.getCurrentSession()
					.createQuery(
							"From AuctionRecord where auction.auctionId = ?")
					.setInteger(0, auctionId).list();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return auctionRecords;
	}

	@Override
	public int auctionRecordAdd(AuctionRecord auctionRecord) {
		// TODO Auto-generated method stub
		List<AuctionRecord> auctionRecords = new ArrayList<AuctionRecord>();
		try {
			auctionRecord.setAuctionTime(new java.util.Date());
			getSessionFactory().getCurrentSession().save(auctionRecord);
			return 1;

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

}
