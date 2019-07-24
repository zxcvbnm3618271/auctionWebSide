package com.qianfeng.action;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.qianfeng.biz.AuctionUserBIZ;
import com.qianfeng.bizimpl.AuctionUserBIZImpl;
<<<<<<< HEAD
import com.qianfeng.entity.AuctionUser;  
=======
import com.qianfeng.entity.AuctionUser;
>>>>>>> dcd0b10f3e30ae8e1705ce8eb3435da4cd175c04
import com.qianfeng.enums.AuctionLoginStateEnum;

public class AuctionUserAction extends ActionSupport implements
                 ModelDriven<AuctionUser> {

	private AuctionUser auctionUser = new AuctionUser();
	private String userInputCode;
	private String sysCode;
	private String msg;
	AuctionUserBIZ auctionUserBIZ = new AuctionUserBIZImpl();

	public String login() {
		msg = auctionUserBIZ.auctionUserLogin(getModel().getUserName(),
				getModel().getUserPassWord(), userInputCode,
				(String) ServletActionContext.getRequest().getSession()
						.getAttribute("numrand"),
				ServletActionContext.getRequest());
		if (msg.equals(AuctionLoginStateEnum.AUCTION_LOGIN_VALIDATACODE_ERROR.getValue())) {
			return INPUT;
		}else if (msg.equals(AuctionLoginStateEnum.AUCTION_LOGIN_FAIL.getValue())) {
			return INPUT;
		}else {
			return SUCCESS;
		}
		
	}

	@Override
	public AuctionUser getModel() {
		// TODO Auto-generated method stub
		return auctionUser;
	}

	public AuctionUser getAuctionUser() {
		return auctionUser;
	}

	public String getUserInputCode() {
		return userInputCode;
	}

	public void setUserInputCode(String userInputCode) {
		this.userInputCode = userInputCode;
	}

	public String getSysCode() {
		return sysCode;
	}

	public void setSysCode(String sysCode) {
		this.sysCode = sysCode;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public AuctionUserBIZ getAuctionUserBIZ() {
		return auctionUserBIZ;
	}

	public void setAuctionUserBIZ(AuctionUserBIZ auctionUserBIZ) {
		this.auctionUserBIZ = auctionUserBIZ;
	}

	public void setAuctionUser(AuctionUser auctionUser) {
		this.auctionUser = auctionUser;
	}

}
