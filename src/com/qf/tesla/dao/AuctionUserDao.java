package com.qf.tesla.dao;

import com.qf.tesla.entity.AuctionUser;

public interface AuctionUserDao {
	AuctionUser auctionLogin(String userName,String passWord);

	AuctionUser findAuctionUserByName(String userName);
}
