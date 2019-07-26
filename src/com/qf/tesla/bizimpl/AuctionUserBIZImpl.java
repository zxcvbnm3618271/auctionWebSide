package com.qf.tesla.bizimpl;

import javax.persistence.Entity;
import javax.servlet.http.HttpServletRequest;

import com.qf.tesla.biz.AuctionUserBIZ;
import com.qf.tesla.dao.AuctionUserDao;
import com.qf.tesla.daoimpl.AuctionUserDaoImpl;

import com.qf.tesla.entity.AuctionUser;
import com.qf.tesla.enums.AuctionLoginStateEnum;
import com.qf.tesla.util.MD5;
import com.qf.tesla.util.StringUtil;

@Entity
public class AuctionUserBIZImpl implements AuctionUserBIZ {

	AuctionUserDao auctionUserDao ;
	
	@Override
	public String auctionUserLogin(String userNmae, String passWord,
			String userInputcode, String syscode,HttpServletRequest request) {
		// TODO Auto-generated method stub
		// 验证码出错
		if (StringUtil.isEmpty(syscode) || StringUtil.isEmpty(userInputcode)) {
			return AuctionLoginStateEnum.AUCTION_lOGIN_VALIDATACOMDE_ERROR
					.getValue();
		}
		if (!userInputcode.equals(syscode)){
			return AuctionLoginStateEnum.AUCTION_lOGIN_VALIDATACOMDE_ERROR
					.getValue();
		}
		// 判断数据库是否匹配
		//一般密码使用md5加密，散列次数越高，安全越高。
	
		AuctionUser auctionUser = auctionUserDao.auctionLogin(userNmae, MD5.MD5(passWord)) ;
		if (auctionUser== null) {
			
			return AuctionLoginStateEnum.AUCTION_lOGIN_FAIL.getValue();
		}
		// 成功
		request.getSession().setAttribute("user", auctionUser);
		return AuctionLoginStateEnum.AUCTION_lOGIN_SUCCESS.getValue();
	}

	public AuctionUserDao getAuctionUserDao() {
		return auctionUserDao;
	}

	public void setAuctionUserDao(AuctionUserDao auctionUserDao) {
		this.auctionUserDao = auctionUserDao;
	}

	@Override
	public AuctionUser findAuctionUserByName(String userName) {
		// TODO Auto-generated method stub
		return auctionUserDao.findAuctionUserByName(userName);
	}
	
	

}
