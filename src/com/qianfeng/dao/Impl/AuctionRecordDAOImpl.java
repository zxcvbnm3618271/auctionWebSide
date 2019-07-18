package com.qianfeng.dao.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.qianfeng.dao.AuctionRecordDAO;
import com.qianfeng.entity.AuctionRecord;
import com.qianfeng.entity.AuctionUser;
import com.qianfeng.util.JDBCUtil;

public class AuctionRecordDAOImpl implements AuctionRecordDAO {

	@Override
	public List<AuctionRecord> findAuctionRecordByAuctionId(int auctionid) {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<AuctionRecord> auctionRecords=new ArrayList<AuctionRecord>();
		try {
			connection = JDBCUtil.getConnection();
			//left join 的原则就是主表在前副表在后 不管数据符不符合ON的条件 主表数据都会展现出来
			preparedStatement = connection
					.prepareStatement("select * from auctionrecord acrd left join auctionuser acus on acrd.userid=acus.userid where acrd.auctionid=?");
		preparedStatement.setInt(1, auctionid);
		resultSet=preparedStatement.executeQuery();
		while(resultSet.next())
		{

			AuctionRecord auctionRecord=new AuctionRecord();
			auctionRecord.setAuctionPrice(resultSet.getInt("AUCTIONPRICE"));
			auctionRecord.setAuctionTime(resultSet.getTimestamp("AUCTIONTIME"));
			AuctionUser auctionUser=new AuctionUser();
			auctionUser.setUserName(resultSet.getString("USERNAME"));
			auctionRecord.setAuctionUser(auctionUser);
			auctionRecords.add(auctionRecord);
		}
		} catch (Exception e) {
			// TODO: handle exception
			
		}finally{
			JDBCUtil.close();
		}
		return auctionRecords;
	}

}
