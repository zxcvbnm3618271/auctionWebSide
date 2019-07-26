package com.qf.tesla.action;

import java.util.ArrayList;
import java.util.List;

import com.opensymphony.xwork2.ActionSupport;
import com.qf.tesla.entity.Auction;
import com.qf.tesla.servlet.auctionResultServlet;
import com.sun.org.apache.bcel.internal.generic.NEW;

public class Test extends ActionSupport{

	List<Auction> auctions = new ArrayList<Auction>();
	
	public String test01(){
		Auction auction= new Auction();
		auction.setAuctionName("第一");
		Auction auction2= new Auction();
		auction2.setAuctionName("第二");
		Auction auction3= new Auction();
		auction3.setAuctionName("第三");
		auctions.add(auction);
		auctions.add(auction2);
		auctions.add(auction3);
		
		return SUCCESS;
	}

	public List<Auction> getAuctions() {
		return auctions;
	}

	public void setAuctions(List<Auction> auctions) {
		this.auctions = auctions;
	}
	
}
