package com.qf.tesla.entity;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import com.sun.org.apache.bcel.internal.generic.NEW;


/**
 * Auctionuser entity. @author MyEclipse Persistence Tools
 */

public class AuctionUser  implements java.io.Serializable {


    // Fields    

     private Integer userId;
     private String userName;
     private String userPassWord;
     private Boolean userIsAdmin;
     private Set<AtRole> atRoles = new HashSet<AtRole>();


     public AuctionUser(Integer userId) {
		super();
		this.userId = userId;
	}


	// Constructors

    public AuctionUser(String userName, String userPassWord) {
		super();
		this.userName = userName;
		this.userPassWord = userPassWord;
	}


	public AuctionUser() {
		
	}





	public Integer getUserId() {
		return userId;
	}


	public void setUserId(Integer userId) {
		this.userId = userId;
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


	


	public Set<AtRole> getAtRoles() {
		return atRoles;
	}


	public void setAtRoles(Set<AtRole> atRoles) {
		this.atRoles = atRoles;
	}


	@Override
	public String toString() {
		return "AuctionUser [userId=" + userId + ", userName=" + userName
				+ ", userPassWord=" + userPassWord + ", userIsAdmin="
				+ userIsAdmin + "]";
	}






	

	/** minimal constructor */
   
   








}