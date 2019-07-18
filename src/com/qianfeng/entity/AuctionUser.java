package com.qianfeng.entity;

public class AuctionUser {
	// 属性名必须使用驼峰命名法
	private String userName;
	private String userPassWord;
	private Boolean userIsAdmin;
	private int UserID;
	
	public int getUserID() {
		return UserID;
	}

	public void setUserID(int userID) {
		UserID = userID;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPassWord() {
		return userPassWord;
	}

	public void setUserPassWord(String userPassWord) {
		this.userPassWord = userPassWord;
	}

	public Boolean getUserIsAdmin() {
		return userIsAdmin;
	}

	public void setUserIsAdmin(Boolean userIsAdmin) {
		this.userIsAdmin = userIsAdmin;
	}

	public AuctionUser() {
		
	}
	public AuctionUser(int userID) {
		this.UserID=userID;
	}
	@Override
	public String toString() {
		return "AuctionUser [userName=" + userName + ", userPassWord="
				+ userPassWord + ", userIsAdmin=" + userIsAdmin + "]";
	}

}
