package com.qianfeng.biz;

import javax.servlet.http.HttpServletRequest;

public interface AuctionUserBIZ {
	//
	String auctionUserLogin(String userName, String passWord,
			String userInputCode, String sysCode,HttpServletRequest request);
}
