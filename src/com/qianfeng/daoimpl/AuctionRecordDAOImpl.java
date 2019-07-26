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
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;

import com.qianfeng.dao.AuctionRecordDAO;
import com.qianfeng.entity.Auction;
import com.qianfeng.entity.AuctionRecord;
import com.qianfeng.entity.AuctionUser;
import com.qianfeng.util.JDBCUtil;

@Transactional
public class AuctionRecordDAOImpl  extends HibernateDaoSupport implements AuctionRecordDAO {

	@Override
	public List<AuctionRecord> findAuctionRecordByAuctionId(int auctionID) {
		
		List<AuctionRecord> auctionRecords=new ArrayList<AuctionRecord>();
		
		try {
			
			auctionRecords=getSessionFactory().getCurrentSession().createQuery("from AuctionRecord where auction.auctionID=?")
					.setInteger(0, auctionID)
					.list();
					
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return auctionRecords;
		// TODO Auto-generated method stub
		
	}

	@Override
	public int auctionRecordAdd(AuctionRecord auctionRecord) {
		
		
		
		try {
			
			
			getSessionFactory().getCurrentSession().save(auctionRecord);
			
			return 1;
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
		
	}

}
