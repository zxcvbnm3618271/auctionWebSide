package com.qianfeng.bizimpl;

import javax.servlet.http.HttpServletRequest;

import com.qianfeng.biz.AuctionUserBIZ;
import com.qianfeng.dao.AuctionUserDAO;
import com.qianfeng.daoimpl.AuctionUserDAOImpl;
import com.qianfeng.entity.AuctionUser;
import com.qianfeng.enums.AuctionLoginStateEnum;
import com.qianfeng.util.MD5;
import com.qianfeng.util.StringUtil;

public class AuctionUserBIZImpl implements AuctionUserBIZ {

	@Override
	public String auctionUserLogin(String userName, String passWord,
			String userInputCode, String sysCode,HttpServletRequest request) {
		// TODO Auto-generated method stub
		
		if (StringUtil.isEmpty(userInputCode)||StringUtil.isEmpty(sysCode)) {
			return AuctionLoginStateEnum.AUCTION_LOGIN_VALIDATACODE_ERROR.getValue();
		}
		
		if (!userInputCode.equals(sysCode)) {
			return AuctionLoginStateEnum.AUCTION_LOGIN_VALIDATACODE_ERROR.getValue();
		}
		
		AuctionUserDAO auctionUserDAO=new AuctionUserDAOImpl();
		AuctionUser auctionUser=auctionUserDAO.auctionLogin(userName,MD5.MD5(passWord));
		if(auctionUser==null){
			return AuctionLoginStateEnum.AUCTION_LOGIN_FAIL.getValue();
			
		}
		request.getSession().setAttribute("user", auctionUser);
	
		return AuctionLoginStateEnum.AUCTION_LOGIN_SUCCESS.getValue();
	}

}
