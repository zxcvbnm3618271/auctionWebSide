package com.qianfeng.entity;

import java.sql.Timestamp;


/**
 * Auctionuser entity. @author MyEclipse Persistence Tools
 */
public class AuctionUser  implements java.io.Serializable {

  private Integer userID;
  private String userName;
  private String userPassWord;
  private boolean userIsAdmin;
  
  public AuctionUser() {
		
		
 	}
   
   
 public AuctionUser(Integer userID) {
 	
 	this.userID = userID;
 }
public Integer getUserID() {
	return userID;
}
public void setUserID(Integer userID) {
	this.userID = userID;
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
public boolean isUserIsAdmin() {
	return userIsAdmin;
}
public void setUserIsAdmin(boolean userIsAdmin) {
	this.userIsAdmin = userIsAdmin;
}
   
  
  
  
}
