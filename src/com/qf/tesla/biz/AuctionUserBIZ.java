package com.qf.tesla.biz;

import javax.servlet.http.HttpServletRequest;

import com.qf.tesla.entity.AuctionUser;

public interface AuctionUserBIZ {
	String auctionUserLogin(String userNmaeString, String passWord,
			String userInputcode, String syscode,HttpServletRequest request);
	
	AuctionUser findAuctionUserByName(String userName);
}
