package com.qf.tesla.bizimpl;

import java.util.List;

import com.qf.tesla.biz.AuctionRecordBIZ;
import com.qf.tesla.dao.AuctionRecordDao;
import com.qf.tesla.daoimpl.AuctionRecordDaoImpl;
import com.qf.tesla.entity.AuctionRecord;
import com.sun.org.apache.bcel.internal.generic.NEW;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class AuctionRecordBIZImpl implements AuctionRecordBIZ{
	
	@ManyToOne
	AuctionRecordDao auctionRecordDao ;
	

	
	@Override
	public List<AuctionRecord> findAuctionRecordByAuctionId(int auctionId) {
		// TODO Auto-generated method stub
		return auctionRecordDao.findAuctionRecordByAuctionId(auctionId);
	}

	@Override
	public int auctionRecordAdd(AuctionRecord auctionRecord) {
		// TODO Auto-generated method stub
		return auctionRecordDao.auctionRecordAdd(auctionRecord);
	}

	public AuctionRecordDao getAuctionRecordDao() {
		return auctionRecordDao;
	}

	public void setAuctionRecordDao(AuctionRecordDao auctionRecordDao) {
		this.auctionRecordDao = auctionRecordDao;
	}


}
