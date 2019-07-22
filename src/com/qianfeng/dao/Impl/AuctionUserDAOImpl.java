package com.qianfeng.dao.Impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.qianfeng.dao.AuctionUserDAO;
import com.qianfeng.entity.AuctionUser;

public class AuctionUserDAOImpl implements AuctionUserDAO {

	public AuctionUser auctionLogin(String userName, String passWord) {
		AuctionUser auctionUser = null;
		Session session = null;
		SessionFactory sessionFactory = null;
		try {
			sessionFactory = new Configuration().configure()
					.buildSessionFactory();
			session = sessionFactory.openSession();
			auctionUser = (AuctionUser) session
					.createQuery(
							"from AuctionUser as atus where atus.userName=? and atus.userPassWord=?")
					.setString(0, userName).setString(1, passWord)
					.uniqueResult();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}

		}
		return auctionUser;
	}

}
