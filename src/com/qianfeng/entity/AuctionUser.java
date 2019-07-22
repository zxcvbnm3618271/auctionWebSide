package com.qianfeng.entity;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;


/**
 * Auctionuser entity. @author MyEclipse Persistence Tools
 */

public class AuctionUser  implements java.io.Serializable {


    // Fields    

     private Integer userID;
     private String userName;
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

	public String getUserCardNo() {
		return userCardNo;
	}

	public void setUserCardNo(String userCardNo) {
		this.userCardNo = userCardNo;
	}

	public String getUserTel() {
		return userTel;
	}

	public void setUserTel(String userTel) {
		this.userTel = userTel;
	}

	public String getUserAddress() {
		return userAddress;
	}

	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}

	public String getUserPostNumber() {
		return userPostNumber;
	}

	public void setUserPostNumber(String userPostNumber) {
		this.userPostNumber = userPostNumber;
	}

	public Boolean getUserIsAdmin() {
		return userIsAdmin;
	}

	public void setUserIsAdmin(Boolean userIsAdmin) {
		this.userIsAdmin = userIsAdmin;
	}

	public String getUserQuestion() {
		return userQuestion;
	}

	public void setUserQuestion(String userQuestion) {
		this.userQuestion = userQuestion;
	}

	public String getUserAnswer() {
		return userAnswer;
	}

	public void setUserAnswer(String userAnswer) {
		this.userAnswer = userAnswer;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public Timestamp getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}

	private String userPassWord;
     private String userCardNo;
     private String userTel;
     private String userAddress;
     private String userPostNumber;
     private Boolean userIsAdmin;
     private String userQuestion;
     private String userAnswer;
     private Timestamp createTime;
     private Timestamp updateTime;


    // Constructors

    /** default constructor */
    public AuctionUser() {
    }

    public AuctionUser(Integer userid)
    {
    	this.userID=userid;
    }
	/** minimal constructor */
    public AuctionUser(String username, String userpassword) {
        this.userName = username;
        this.userPassWord = userpassword;
    }
    
    /** full constructor */
    public AuctionUser(String username, String userpassword, String usercardno, String usertel, String useraddress, String userpostnumber, Boolean userisadmin, String userquestion, String useranswer, Timestamp createtime, Timestamp updatetime) {
        this.userName = username;
        this.userPassWord = userpassword;
        this.userCardNo = usercardno;
        this.userTel = usertel;
        this.userAddress = useraddress;
        this.userPostNumber = userpostnumber;
        this.userIsAdmin = userisadmin;
        this.userQuestion = userquestion;
        this.userAnswer = useranswer;
        this.createTime = createtime;
        this.updateTime = updatetime;
    }

   
    // Property accessors

    
}