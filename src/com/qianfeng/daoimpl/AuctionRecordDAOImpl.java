package com.qianfeng.daoimpl;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.qianfeng.dao.AuctionRecordDAO;
import com.qianfeng.entity.Auction;
import com.qianfeng.entity.AuctionRecord;
import com.qianfeng.entity.AuctionUser;
import com.qianfeng.util.JDBCUtil;

public class AuctionRecordDAOImpl implements AuctionRecordDAO {

	@Override
	public List<AuctionRecord> findAuctionRecordByAuctionId(int auctionID) {
		
		List<AuctionRecord> auctionRecords=new ArrayList<AuctionRecord>();
		SessionFactory sessionFactory=null;
		Session session=null;
		try {
			sessionFactory=new Configuration().configure()
					.buildSessionFactory();
			session=sessionFactory.openSession();
			auctionRecords=session
					.createQuery("from AuctionRecord where auction.auctionID=?")
					.setInteger(0, auctionID)
					.list();
					
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if (null !=session) {
				session.close();
			}
		}
		return auctionRecords;
		// TODO Auto-generated method stub
		
	}

	@Override
	public int auctionRecordAdd(AuctionRecord auctionRecord) {
		
		SessionFactory sessionFactory=null;
		Session session=null;
		Transaction transaction=null;
		try {
			sessionFactory=new Configuration().configure()
					.buildSessionFactory();
			session=sessionFactory.openSession();
			transaction=session.beginTransaction();
			session.save(auctionRecord);
			transaction.commit();
			return 1;
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if (null !=session) {
				session.close();
			}
		}
		return 0;
		
	}

}
