package com.qianfeng.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.qianfeng.dao.AuctionUserDAO;
import com.qianfeng.entity.AuctionUser;
import com.qianfeng.util.JDBCUtil;
import com.sun.corba.se.impl.orb.ParserTable.TestAcceptor1;


public class AuctionUserDAOImpl implements AuctionUserDAO {

	@Override
	public AuctionUser auctionLogin(String userName, String passWord) {
		// TODO Auto-generated method stub
		AuctionUser auctionUser=null;
		SessionFactory sessionFactory=null;
		Session session=null;
		try {
			sessionFactory=new Configuration().configure()
					.buildSessionFactory();
			session=sessionFactory.openSession();
			auctionUser=(AuctionUser) session
					.createQuery("from AuctionUser as atus where atus.userName=? and atus.userPassWord=? ")
					.setString(0,userName).setString(1,passWord)
					.uniqueResult();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if (null !=session) {
				session.close();
			}
		}
		return auctionUser;
	}

	
}
